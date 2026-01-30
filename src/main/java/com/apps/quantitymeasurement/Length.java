package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Convert value to base unit (inches)
    private double convertToBaseUnit() {
        return value * unit.getConversionFactorToInch();
    }

    // Compare two Length objects
    public boolean compare(Length otherLength) {
        if (otherLength == null) return false;
        double thisValueInInches = this.convertToBaseUnit();
        double otherValueInInches = otherLength.convertToBaseUnit();
        return Math.abs(thisValueInInches - otherValueInInches) < 0.0001; // For floating point precision
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Length length = (Length) obj;
        return this.compare(length);
    }

    @Override
    public String toString() {
        return value + " " + unit.name().toLowerCase();
    }
}
