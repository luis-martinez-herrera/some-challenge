package com.example.restservice.metrics.impl;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public abstract class AbstractMetricCalculator {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    protected AbstractMetricCalculator() {
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
    }

    protected double format (double value){
        return Double.parseDouble(decimalFormat.format(value));
    }
}
