package com.tanaseb.paymentscheduler.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PeriodTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void endIsGreaterThanStart() {
        Period period = PeriodBuilderTest.anEndGreaterThanStart();

        assertTrue(period.isEndGreaterThanOrEqualToStart());
    }

    @Test
    public void endIsEqualToStart() {
        Period period = PeriodBuilderTest.anEndEqualToStart();

        assertTrue(period.isEndGreaterThanOrEqualToStart());
    }

    @Test
    public void endIsNotGreaterThanStart() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(Period.END_GREATER_THAN_OR_EQUAL_TO_START);

        Period period = PeriodBuilderTest.aStartGreaterThanEnd();
        period.isEndGreaterThanOrEqualToStart();
    }

    @Test
    public void calculateNumberOfDays() {
        Period period = PeriodBuilderTest.anEndEqualToStart();

        period.calculateNumberOfDays();
        assertEquals(Integer.valueOf(1), period.getNumberOfDays());
    }
}