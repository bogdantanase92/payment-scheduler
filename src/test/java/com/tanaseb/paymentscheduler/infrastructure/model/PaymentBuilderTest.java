package com.tanaseb.paymentscheduler.infrastructure.model;

import com.tanaseb.paymentscheduler.domain.model.Frequency;
import com.tanaseb.paymentscheduler.domain.model.Payment;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class PaymentBuilderTest {

    public static Payment aPayment() {
        return Payment.builder()
                .start(DateTime.now())
                .end(DateTime.now())
                .billedNoOfDays(13)
                .billedAmount(13.13)
                .monthlyPayments(MonthlyPaymentBuilderTest.monthlyPayments())
                .build();
    }

    public static PaymentDto aPaymentDto() {
        return PaymentDto.builder()
                .start(DateTime.now().toString(DateTimeFormat.shortDate()))
                .end(DateTime.now().toString(DateTimeFormat.shortDate()))
                .billingFrequency(Frequency.MONTHLY.name())
                .totalBilledNoOfDays(13)
                .totalBilledAmount(13.13)
                .monthlyPayments(MonthlyPaymentBuilderTest.monthlyPaymentDtos())
                .build();
    }
}
