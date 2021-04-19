package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.domain.model.Frequency;
import com.tanaseb.paymentscheduler.domain.model.Payment;
import com.tanaseb.paymentscheduler.infrastructure.model.MonthlyPaymentBuilderTest;
import com.tanaseb.paymentscheduler.infrastructure.model.MonthlyPaymentDto;
import com.tanaseb.paymentscheduler.infrastructure.model.PaymentBuilderTest;
import com.tanaseb.paymentscheduler.infrastructure.model.PaymentDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PaymentMapperTest {

    private static final String MONTHLY = "monthly";

    @InjectMocks
    private PaymentMapper paymentMapper;
    @Mock
    private MonthlyPaymentMapper monthlyPaymentMapper;
    @Mock
    private FrequencyMapper frequencyMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toPaymentDto() {
        Payment payment = PaymentBuilderTest.aPayment();
        List<MonthlyPaymentDto> monthlyPaymentDtos = MonthlyPaymentBuilderTest.monthlyPaymentDtos();

        when(frequencyMapper.toFrequency(MONTHLY)).thenReturn(Frequency.MONTHLY);
        when(monthlyPaymentMapper.toMonthlyPaymentDtos(payment.getMonthlyPayments()))
                .thenReturn(monthlyPaymentDtos);

        PaymentDto expected = PaymentBuilderTest.aPaymentDto();
        PaymentDto actual = paymentMapper.toPaymentDto(payment, MONTHLY);

        assertEquals(expected, actual);
    }
}