package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.domain.model.Frequency;
import org.springframework.stereotype.Service;

@Service
public class FrequencyMapper {

    private static final String FREQUENCY_NOT_SUPPORTED = "Frequency not supported.";

    public Frequency toFrequency(String frequency) {
        if (frequency.toUpperCase().equals(Frequency.WEEKLY.name())) {
            return Frequency.WEEKLY;
        } else if (frequency.toUpperCase().equals(Frequency.MONTHLY.name())) {
            return Frequency.MONTHLY;
        } else if (frequency.toUpperCase().equals(Frequency.QUARTERLY.name())) {
            return Frequency.QUARTERLY;
        } else {
            throw new IllegalArgumentException(FREQUENCY_NOT_SUPPORTED);
        }
    }
}
