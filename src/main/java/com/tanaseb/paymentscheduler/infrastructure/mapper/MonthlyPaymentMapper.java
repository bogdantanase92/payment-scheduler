package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.domain.model.MonthlyPayment;
import com.tanaseb.paymentscheduler.infrastructure.model.MonthlyPaymentDto;
import com.tanaseb.paymentscheduler.infrastructure.util.DoubleUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class MonthlyPaymentMapper {

    public List<MonthlyPaymentDto> toMonthlyPaymentDtos(List<MonthlyPayment> monthlyPayments) {
        return monthlyPayments.stream()
                .map(this::toMonthlyPaymentDto)
                .collect(Collectors.toList());
    }

    private MonthlyPaymentDto toMonthlyPaymentDto(MonthlyPayment monthlyPayment) {
        return MonthlyPaymentDto.builder()
                .start(monthlyPayment.getStart().toString(DateTimeFormat.shortDate()))
                .end(monthlyPayment.getEnd().toString(DateTimeFormat.shortDate()))
                .amount(DoubleUtils.formatToFourDecimals(monthlyPayment.getAmount()))
                .billedNoOfDays(monthlyPayment.getBilledNoOfDays())
                .dailyAmount(DoubleUtils.formatToFourDecimals(monthlyPayment.getDailyAmount()))
                .billedAmount(DoubleUtils.formatToFourDecimals(monthlyPayment.getBilledAmount()))
                .build();
    }
}
