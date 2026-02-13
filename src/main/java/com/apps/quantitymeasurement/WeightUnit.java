package com.apps.quantitymeasurement;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),        // 1 g = 0.001 kg
    POUND(0.453592);    // 1 lb = 0.453592 kg

    private final double conversionFactorToBase;

    WeightUnit(double conversionFactorToBase) {
        this.conversionFactorToBase = conversionFactorToBase;
    }

//  Convert value in this unit to base unit (kilogram)
    public double convertToBaseUnit(double value) {
        return value * conversionFactorToBase;
    }

//  Convert value from base unit (kilogram) to this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToBase;
    }

    public double getConversionFactorToBase() {
        return conversionFactorToBase;
    }
}