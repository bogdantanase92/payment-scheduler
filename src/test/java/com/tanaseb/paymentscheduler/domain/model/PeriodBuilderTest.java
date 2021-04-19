package com.tanaseb.paymentscheduler.domain.model;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

public class PeriodBuilderTest {

    public static Period anEndGreaterThanStart() {
        return Period.builder()
                .start(DateTime.parse("2019-07-19"))
                .end(DateTime.parse("2019-07-20"))
                .build();
    }

    static Period anEndEqualToStart() {
        return Period.builder()
                .start(DateTime.parse("2019-07-19"))
                .end(DateTime.parse("2019-07-19"))
                .build();
    }

    static Period aStartGreaterThanEnd() {
        return Period.builder()
                .start(DateTime.parse("2019-07-20"))
                .end(DateTime.parse("2019-07-19"))
                .build();
    }

    public static List<Period> periods() {
        Period firstPeriod = anEndEqualToStart();
        firstPeriod.calculateNumberOfDays();

        Period secondPeriod = anEndGreaterThanStart();
        secondPeriod.calculateNumberOfDays();

        return Arrays.asList(firstPeriod, secondPeriod);
    }
}
