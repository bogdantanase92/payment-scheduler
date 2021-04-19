package com.tanaseb.paymentscheduler.domain.model;

public class RateBuilderTest {

    public static Rate aMonthlyRate() {
        return Rate.builder()
                .amount(400d)
                .frequency(Frequency.MONTHLY)
                .build();
    }

    public static Rate aWeeklyRate() {
        return Rate.builder()
                .amount(100d)
                .frequency(Frequency.WEEKLY)
                .build();
    }

    public static Rate aYearlyRate() {
        return Rate.builder()
                .amount(400d)
                .frequency(Frequency.YEARLY)
                .build();
    }

}
