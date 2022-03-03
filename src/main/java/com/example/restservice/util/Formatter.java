package com.example.restservice.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Formatter {

    private static final DecimalFormat twoDecimalsFormat = new DecimalFormat("0.00");
    private static final DecimalFormat threeDecimalsFormat = new DecimalFormat("0.000");

    static {
        twoDecimalsFormat.setRoundingMode(RoundingMode.DOWN);
        threeDecimalsFormat.setRoundingMode(RoundingMode.DOWN);
    }

    private Formatter() {
    }

    public static double formatToTwoDecimals(double value) {
        return Double.parseDouble(twoDecimalsFormat.format(value));
    }

    public static double formatToThreeDecimals(double value) {
        return Double.parseDouble(threeDecimalsFormat.format(value));
    }
}
