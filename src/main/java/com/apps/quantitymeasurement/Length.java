package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Convert to base unit (inches)
    private double toInches() {
        return value * unit.getConversionFactorToInch();
    }

    // UC6: Basic addition (result in this object's unit) - KEPT for backward compatibility
    public Length add(Length other) {
        if (other == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        double sumInInches = this.toInches() + other.toInches();
        double sumInThisUnit = sumInInches / unit.getConversionFactorToInch();

        return new Length(Math.round(sumInThisUnit * 10000.0) / 10000.0, this.unit);

    }

    // UC7 NEW: Addition with explicit target unit (Method Overloading)
    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Length and target unit cannot be null");
        }

        double sumInInches = this.toInches() + other.toInches();
        double sumInTarget = sumInInches / targetUnit.getConversionFactorToInch();

        return new Length(Math.round(sumInTarget * 10000.0) / 10000.0, targetUnit);

    }

    // Existing conversion methods
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        if (unit == targetUnit) return new Length(value, targetUnit);

        double valueInInches = toInches();
        double convertedValue = valueInInches / targetUnit.getConversionFactorToInch();
        return new Length(Math.round(convertedValue * 10000.0) / 10000.0, targetUnit);

    }

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (from == null || to == null) throw new IllegalArgumentException("Units cannot be null");
        return new Length(value, from).convertTo(to).getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length other = (Length) obj;
        return Math.abs(this.toInches() - other.toInches()) < 0.0001;

    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return value + " " + unit.name().toLowerCase();
    }
}

