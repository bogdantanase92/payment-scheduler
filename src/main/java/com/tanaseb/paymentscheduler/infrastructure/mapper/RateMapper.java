package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.domain.model.Rate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateMapper {

    private final FrequencyMapper frequencyMapper;

    public Rate toRate(Double amount, String frequency) {
        return Rate.builder()
                .amount(amount)
                .frequency(frequencyMapper.toFrequency(frequency))
                .build();
    }
}
