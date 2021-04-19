package com.tanaseb.paymentscheduler.domain.service;

import com.tanaseb.paymentscheduler.domain.model.MonthlyPayment;
import com.tanaseb.paymentscheduler.domain.model.Payment;
import com.tanaseb.paymentscheduler.domain.model.Period;
import com.tanaseb.paymentscheduler.domain.model.PeriodBuilderTest;
import com.tanaseb.paymentscheduler.domain.model.Rate;
import com.tanaseb.paymentscheduler.domain.model.RateBuilderTest;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;
    @Mock
    private PeriodService periodService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void calculateAmountForWeeklyFrequency() {
        Rate rate = RateBuilderTest.aWeeklyRate();
        Double amount = paymentService.calculateMonthlyAmount(rate);

        assertEquals(Double.valueOf(400), amount);
    }

    @Test
    public void calculateAmountForMonthlyFrequency() {
        Rate rate = RateBuilderTest.aMonthlyRate();
        Double amount = paymentService.calculateMonthlyAmount(rate);

        assertEquals(Double.valueOf(400), amount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateAmountForOtherFrequency() {
        Rate rate = RateBuilderTest.aYearlyRate();

        paymentService.calculateMonthlyAmount(rate);
    }

    @Test
    public void calculateDailyAmount() {
        Double amount = 1000d;
        DateTime date = DateTime.parse("2019-07-20");

        Double actualDailyAmount = paymentService.calculateDailyAmount(amount, date);
        Double expectedDailyAmount = 32.25806451612903;

        assertEquals(expectedDailyAmount, actualDailyAmount);
    }

    @Test
    public void calculateMonthlyBilledAmount() {
        Double actual = paymentService.calculateMonthlyBilledAmount(2, 2.3);

        assertEquals(Double.valueOf(4.6), actual);
    }

    @Test
    public void calculateMonthlyPayments() {
        Rate rate = RateBuilderTest.aWeeklyRate();
        List<Period> periods = PeriodBuilderTest.periods();

        List<MonthlyPayment> monthlyPayments = paymentService.calculateMonthlyPayments(rate, periods);

        MonthlyPayment firstMonthlyPayment = monthlyPayments.get(0);
        MonthlyPayment secondMonthlyPayment = monthlyPayments.get(1);

        assertEquals(Double.valueOf(400), firstMonthlyPayment.getAmount());
        assertEquals(Integer.valueOf(1), firstMonthlyPayment.getBilledNoOfDays());
        assertEquals(Double.valueOf(12.903225806451612), firstMonthlyPayment.getDailyAmount());
        assertEquals(Double.valueOf(12.903225806451612), firstMonthlyPayment.getBilledAmount());

        assertEquals(Double.valueOf(400), secondMonthlyPayment.getAmount());
        assertEquals(Integer.valueOf(2), secondMonthlyPayment.getBilledNoOfDays());
        assertEquals(Double.valueOf(12.903225806451612), secondMonthlyPayment.getDailyAmount());
        assertEquals(Double.valueOf(25.806451612903224), secondMonthlyPayment.getBilledAmount());
    }

    @Test
    public void calculate() {
        Rate rate = RateBuilderTest.aMonthlyRate();
        Period period = PeriodBuilderTest.anEndGreaterThanStart();
        List<Period> periods = PeriodBuilderTest.periods();

        when(periodService.schedule(period)).thenReturn(periods);

        Payment payment = paymentService.calculate(rate, period);
        assertEquals(Integer.valueOf(3), payment.getBilledNoOfDays());
        assertEquals(Double.valueOf(38.70967741935483), payment.getBilledAmount());
        assertEquals(2, payment.getMonthlyPayments().size());
    }
}