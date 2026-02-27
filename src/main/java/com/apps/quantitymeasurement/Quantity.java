package com.apps.quantitymeasurement;

import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ----- Conversion -----
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (unit.equals(targetUnit)) {
            return new Quantity<>(value, targetUnit);
        }
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        // Round to 4 decimal places
        convertedValue = Math.round(convertedValue * 10000.0) / 10000.0;
        return new Quantity<>(convertedValue, targetUnit);
    }

    // ----- Addition -----
    public Quantity<U> add(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity to add cannot be null");
        }
        // Both quantities are of the same generic type U, so categories match by design.
        double thisBase = unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double sumBase = thisBase + otherBase;
        double sumInThisUnit = unit.convertFromBaseUnit(sumBase);
        sumInThisUnit = Math.round(sumInThisUnit * 10000.0) / 10000.0;
        return new Quantity<>(sumInThisUnit, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Quantity and target unit cannot be null");
        }
        double thisBase = unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double sumBase = thisBase + otherBase;
        double sumInTarget = targetUnit.convertFromBaseUnit(sumBase);
        sumInTarget = Math.round(sumInTarget * 10000.0) / 10000.0;
        return new Quantity<>(sumInTarget, targetUnit);
    }

    // ----- Equality & HashCode -----

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        // Prevent cross-category comparison: units must be of the same class
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        // Now it's safe to cast to the same unit type
        @SuppressWarnings("unchecked")
        Quantity<U> that = (Quantity<U>) other;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = that.unit.convertToBaseUnit(that.value);
        return Math.abs(thisBase - thatBase) < 0.001;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Objects.hash(base, unit.getClass());
    }

    @Override
    public String toString() {
        return String.format("%.4f %s", value, unit.getUnitName().toLowerCase());
    }

    // ----- Static helper for conversion (optional, for backward compatibility) -----
    public static <U extends IMeasurable> double convert(double value, U fromUnit, U toUnit) {
        return new Quantity<>(value, fromUnit).convertTo(toUnit).getValue();
    }
}
