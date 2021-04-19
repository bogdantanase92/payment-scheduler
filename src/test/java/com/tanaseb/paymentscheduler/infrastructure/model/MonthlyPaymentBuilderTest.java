package com.tanaseb.paymentscheduler.infrastructure.model;

import com.tanaseb.paymentscheduler.domain.model.MonthlyPayment;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Collections;
import java.util.List;

public class MonthlyPaymentBuilderTest {

    private static MonthlyPayment aMonthlyPayment() {
        return MonthlyPayment.builder()
                .start(DateTime.now())
                .end(DateTime.now())
                .amount(100d)
                .billedNoOfDays(13)
                .dailyAmount(0.1)
                .billedAmount(13.13)
                .build();
    }

    public static List<MonthlyPayment> monthlyPayments() {
        return Collections.singletonList(aMonthlyPayment());
    }

    public static MonthlyPaymentDto aMonthlyPaymentDto() {
        return MonthlyPaymentDto.builder()
                .start(DateTime.now().toString(DateTimeFormat.shortDate()))
                .end(DateTime.now().toString(DateTimeFormat.shortDate()))
                .amount(100d)
                .billedNoOfDays(13)
                .dailyAmount(0.1)
                .billedAmount(13.13)
                .build();
    }

    public static List<MonthlyPaymentDto> monthlyPaymentDtos() {
        return Collections.singletonList(aMonthlyPaymentDto());
    }
}
