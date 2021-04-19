package com.tanaseb.paymentscheduler.domain.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Builder
@Data
public class MonthlyPeriod {
    private DateTime startDate;
    private DateTime endDate;
    private Integer numberOfDaysPerMonth;
}
