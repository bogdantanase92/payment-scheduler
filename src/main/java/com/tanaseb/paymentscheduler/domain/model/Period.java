package com.tanaseb.paymentscheduler.domain.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.Days;

@Builder
@Data
public class Period {
    public static final String END_GREATER_THAN_OR_EQUAL_TO_START
            = "End date should be greater than or equal to start date.";
    public static final String START_AND_END_GREATER_THAN_TODAY
            = "Start date and end date should be greater than today date.";

    private DateTime start;
    private DateTime end;
    private Integer numberOfDays;

    public boolean isEndGreaterThanOrEqualToStart() {
        if (end.isAfter(start) || start.equals(end)) {
            return true;
        } else {
            throw new IllegalStateException(END_GREATER_THAN_OR_EQUAL_TO_START);
        }
    }

    public void calculateNumberOfDays() {
        setNumberOfDays(Days.daysBetween(start, end).getDays() + 1);
    }
}
