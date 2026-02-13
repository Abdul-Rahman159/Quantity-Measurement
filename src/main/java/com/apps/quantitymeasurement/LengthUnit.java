package com.apps.quantitymeasurement;

public enum LengthUnit {

    FEET(1.0),                 // Base unit
    INCHES(1.0 / 12.0),        // 1 inch = 1/12 feet
    YARDS(3.0),                // 1 yard = 3 feet
    CENTIMETERS(1.0 / 30.48);  // 1 cm = 1/30.48 feet

    private final double conversionFactorToBase;

    LengthUnit(double conversionFactorToBase) {
        this.conversionFactorToBase = conversionFactorToBase;
    }

    public double convertToBaseUnit(double value) {
        return value * conversionFactorToBase;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToBase;
    }

    public double getConversionFactorToBase() {
        return conversionFactorToBase;
    }
}