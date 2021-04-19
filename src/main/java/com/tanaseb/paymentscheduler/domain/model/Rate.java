package com.tanaseb.paymentscheduler.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Rate {
    private Double amount;
    private Frequency frequency;

    public boolean isAmountGreaterThanZero() {
        return amount.compareTo((double) 0) > 0;
    }
}
