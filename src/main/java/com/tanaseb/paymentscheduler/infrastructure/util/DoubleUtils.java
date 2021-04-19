package com.tanaseb.paymentscheduler.infrastructure.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtils {

    private static final int SCALE = 4;

    public static Double formatToFourDecimals(Double number) {
        BigDecimal bigDecimal = BigDecimal.valueOf(number).setScale(SCALE, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
