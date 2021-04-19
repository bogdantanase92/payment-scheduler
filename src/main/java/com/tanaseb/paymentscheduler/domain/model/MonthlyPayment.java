package com.tanaseb.paymentscheduler.domain.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

@Builder
@Data
public class MonthlyPayment {
    private DateTime start;
    private DateTime end;
    private Double amount;
    private Integer billedNoOfDays;
    private Double dailyAmount;
    private Double billedAmount;
}
