package com.tanaseb.paymentscheduler.infrastructure.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleUtilsTest {

    @Test
    public void formatToFourDecimals() {
        Double actual = DoubleUtils.formatToFourDecimals(2.2313123123);

        assertEquals(Double.valueOf(2.2313), actual);
    }
}