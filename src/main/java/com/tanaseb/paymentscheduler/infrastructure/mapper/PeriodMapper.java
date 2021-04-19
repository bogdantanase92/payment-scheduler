package com.tanaseb.paymentscheduler.infrastructure.mapper;

import com.tanaseb.paymentscheduler.domain.model.Period;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class PeriodMapper {

    public Period toPeriod(String start, String end) {
        return Period.builder()
                .start(DateTime.parse(start))
                .end(DateTime.parse(end))
                .build();
    }
}
