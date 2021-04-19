package com.tanaseb.paymentscheduler.domain.model;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Builder
@Data
public class Payment {
    private DateTime start;
    private DateTime end;
    private Integer billedNoOfDays;
    private Double billedAmount;
    private List<MonthlyPayment> monthlyPayments;
}
