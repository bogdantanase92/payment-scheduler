package com.tanaseb.paymentscheduler.infrastructure.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PaymentDto {
    private String start;
    private String end;
    private String billingFrequency;
    private Integer totalBilledNoOfDays;
    private Double totalBilledAmount;
    private List<MonthlyPaymentDto> monthlyPayments;
}
