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

    // ----- Conversion (existing) -----

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (unit.equals(targetUnit)) {
            return new Quantity<>(value, targetUnit);
        }
        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        convertedValue = round(convertedValue);
        return new Quantity<>(convertedValue, targetUnit);
    }

    // ----- Addition (existing) -----

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateSameCategory(other);
        validateTargetUnit(targetUnit);

        double thisBase = unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double sumBase = thisBase + otherBase;
        double result = targetUnit.convertFromBaseUnit(sumBase);
        result = round(result);
        return new Quantity<>(result, targetUnit);
    }

    // ----- UC12: Subtraction -----
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }


    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateSameCategory(other);
        validateTargetUnit(targetUnit);

        double thisBase = unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        double diffBase = thisBase - otherBase;
        double result = targetUnit.convertFromBaseUnit(diffBase);
        result = round(result);
        return new Quantity<>(result, targetUnit);
    }

    // ----- UC12: Division -----

    public double divide(Quantity<?> other) {
        if (other == null) {
            throw new IllegalArgumentException("Divisor cannot be null");
        }
        if (!this.unit.getClass().equals(other.getUnit().getClass())) {
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
        }

        @SuppressWarnings("unchecked")
        Quantity<U> that = (Quantity<U>) other;

        double thisBase = unit.convertToBaseUnit(this.value);
        double otherBase = that.unit.convertToBaseUnit(that.value);

        if (Math.abs(otherBase) < 1e-12) {
            throw new ArithmeticException("Division by zero");
        }
        return thisBase / otherBase;
    }

    // ----- Equality & HashCode (existing, updated with epsilon) -----

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

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

    // ----- Private helpers -----

    private double round(double val) {
        return Math.round(val * 10000.0) / 10000.0;
    }

    private void validateSameCategory(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot operate on different measurement categories");
        }
    }

    private void validateTargetUnit(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    // ----- Static helper (optional) -----
    public static <U extends IMeasurable> double convert(double value, U fromUnit, U toUnit) {
        return new Quantity<>(value, fromUnit).convertTo(toUnit).getValue();
    }
}
