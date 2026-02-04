package com.apps.quantitymeasurement;

public enum LengthUnit {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactorToInch;

    LengthUnit(double conversionFactorToInch) {
        this.conversionFactorToInch = conversionFactorToInch;
    }

    public double getConversionFactorToInch() {
        return conversionFactorToInch;
    }
}
