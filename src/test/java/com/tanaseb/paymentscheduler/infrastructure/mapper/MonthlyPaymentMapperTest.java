package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.infrastructure.model.MonthlyPaymentBuilderTest;
import com.tanaseb.paymentscheduler.infrastructure.model.MonthlyPaymentDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MonthlyPaymentMapperTest {

    @InjectMocks
    private MonthlyPaymentMapper monthlyPaymentMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toMonthlyPaymentDtos() {
        List<MonthlyPaymentDto> monthlyPaymentDtos = monthlyPaymentMapper.toMonthlyPaymentDtos(
                MonthlyPaymentBuilderTest.monthlyPayments());

        MonthlyPaymentDto expected = MonthlyPaymentBuilderTest.aMonthlyPaymentDto();
        MonthlyPaymentDto actual = monthlyPaymentDtos.get(0);

        assertEquals(expected, actual);
    }
}