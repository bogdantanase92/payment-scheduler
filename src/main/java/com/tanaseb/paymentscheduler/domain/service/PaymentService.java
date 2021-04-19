package com.tanaseb.paymentscheduler.domain.service;

import com.tanaseb.paymentscheduler.domain.model.Frequency;
import com.tanaseb.paymentscheduler.domain.model.MonthlyPayment;
import com.tanaseb.paymentscheduler.domain.model.Payment;
import com.tanaseb.paymentscheduler.domain.model.Period;
import com.tanaseb.paymentscheduler.domain.model.Rate;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private static final String FREQUENCY_CAN_BE_ONLY_WEEKLY_OR_MONTHLY
            = "Frequency can be only weekly or monthly";

    private final PeriodService periodService;

    public Payment calculate(Rate rate, Period period) {
        List<Period> periods = periodService.schedule(period);

        List<MonthlyPayment> monthlyPayments = calculateMonthlyPayments(rate, periods);
        Double billedAmount = calculateBilledAmount(monthlyPayments);
        Integer billedNoOfDays = calculateBilledNoOfDays(monthlyPayments);

        return Payment.builder()
                .start(period.getStart())
                .end(period.getEnd())
                .billedNoOfDays(billedNoOfDays)
                .billedAmount(billedAmount)
                .monthlyPayments(monthlyPayments)
                .build();
    }

    public List<MonthlyPayment> calculateMonthlyPayments(Rate rate, List<Period> periods) {
        return periods.stream()
                .map(scheduledPeriod -> buildMonthlyPayment(rate, scheduledPeriod))
                .collect(Collectors.toList());
    }

    private MonthlyPayment buildMonthlyPayment(Rate rate, Period scheduledPeriod) {
        Double amount = calculateMonthlyAmount(rate);
        Integer billedNoOfDays = scheduledPeriod.getNumberOfDays();
        Double dailyAmount = calculateDailyAmount(amount, scheduledPeriod.getEnd());
        Double billedAmount = calculateMonthlyBilledAmount(billedNoOfDays, dailyAmount);

        return MonthlyPayment
                .builder()
                .start(scheduledPeriod.getStart())
                .end(scheduledPeriod.getEnd())
                .amount(amount)
                .billedNoOfDays(billedNoOfDays)
                .dailyAmount(dailyAmount)
                .billedAmount(billedAmount)
                .build();
    }

    public Double calculateMonthlyAmount(Rate rate) {
        double amount = (double) 0;
        if (rate.isAmountGreaterThanZero()) {
            if (rate.getFrequency().equals(Frequency.WEEKLY)) {
                amount = rate.getAmount() * Frequency.WEEKLY.multiplierForWeekly();
            } else if (rate.getFrequency().equals(Frequency.MONTHLY)) {
                amount = rate.getAmount() * Frequency.MONTHLY.multiplierForMonthly();
            } else {
                throw new IllegalArgumentException(FREQUENCY_CAN_BE_ONLY_WEEKLY_OR_MONTHLY);
            }
        }
        return amount;
    }

    public Double calculateDailyAmount(Double amount, DateTime date) {
        return amount / noOfDays(date);
    }

    private int noOfDays(DateTime date) {
        return date.dayOfMonth().getMaximumValue();
    }

    public double calculateMonthlyBilledAmount(Integer billedNoOfDays, Double dailyAmount) {
        return billedNoOfDays * dailyAmount;
    }

    private Double calculateBilledAmount(List<MonthlyPayment> monthlyPayments) {
        return monthlyPayments.stream()
                .map(MonthlyPayment::getBilledAmount)
                .reduce(Double::sum)
                .orElse((double) 0);
    }

    private Integer calculateBilledNoOfDays(List<MonthlyPayment> monthlyPayments) {
        return monthlyPayments.stream()
                .map(MonthlyPayment::getBilledNoOfDays)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
