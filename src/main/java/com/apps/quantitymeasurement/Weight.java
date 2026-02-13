package com.apps.quantitymeasurement;

public final class Weight {

    private static final double EPSILON = 0.001;

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    //Getters
    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    // Private helper
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    //Conversion
    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (this.unit == targetUnit) {
            return this;
        }

        double baseValue = this.toBase();
        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Weight(round(converted), targetUnit);
    }

    public static double convert(double value,
                                 WeightUnit from,
                                 WeightUnit to) {
        return new Weight(value, from).convertTo(to).getValue();
    }

    //Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Weight)) return false;

        Weight other = (Weight) obj;
        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(round(toBase()));
    }

    // Addition
    // Default: result in first operand's unit
    public Weight add(Weight other) {
        if (other == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }

        double sumBase = this.toBase() + other.toBase();
        double result = this.unit.convertFromBaseUnit(sumBase);

        return new Weight(round(result), this.unit);
    }

    // Explicit target unit
    public Weight add(Weight other, WeightUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        double sumBase = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Weight(round(result), targetUnit);
    }

    private static double round(double value) {
        return Math.round(value * 10000.0) / 10000.0;
    }

    @Override
    public String toString() {
        return value + " " + unit.name().toLowerCase();
    }
}
