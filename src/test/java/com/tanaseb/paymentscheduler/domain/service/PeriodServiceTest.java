package com.tanaseb.paymentscheduler.domain.service;

import com.tanaseb.paymentscheduler.domain.model.Period;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PeriodServiceTest {

    @InjectMocks
    private PeriodService periodService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void calculateBilledNoOfDays() {
        DateTime start = DateTime.parse("2019-07-30");
        DateTime end = DateTime.parse("2019-08-02");
        Period period = Period.builder()
                .start(start)
                .end(end)
                .build();
        List<Period> periods = periodService.schedule(period);

        Integer actualNoOfDays = getActualNoOfDays(periods);
        Integer expectedNoOfDays = Days.daysBetween(start, end).plus(1).getDays();

        assertEquals(expectedNoOfDays, actualNoOfDays);
    }

    private Integer getActualNoOfDays(List<Period> periods) {
        return periods.stream()
                .map(Period::getNumberOfDays)
                .reduce(Integer::sum)
                .orElse(0);
    }
}