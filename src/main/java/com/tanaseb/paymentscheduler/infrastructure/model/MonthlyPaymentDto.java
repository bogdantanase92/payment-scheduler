package com.tanaseb.paymentscheduler.infrastructure.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MonthlyPaymentDto {
    private String start;
    private String end;
    private Double amount;
    private Integer billedNoOfDays;
    private Double dailyAmount;
    private Double billedAmount;
}
