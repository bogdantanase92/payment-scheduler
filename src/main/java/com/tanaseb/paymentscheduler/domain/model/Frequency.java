package com.tanaseb.paymentscheduler.domain.model;

public enum Frequency {
    WEEKLY,
    MONTHLY,
    QUARTERLY,
    YEARLY;

    public Integer multiplierForWeekly() {
        return 4;
    }

    public Integer multiplierForMonthly() {
        return 1;
    }
}
