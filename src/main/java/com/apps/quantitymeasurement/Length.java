package com.apps.quantitymeasurement;

public class Length {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    private double round(double value) {
        return Math.round(value * 10000.0) / 10000.0;
    }

//    coonvert to base unit
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }


    // UC5 - Conversion
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (this.unit == targetUnit) {
            return new Length(this.value, targetUnit);
        }

        double valueInBase = this.toBase();
        double converted = targetUnit.convertFromBaseUnit(valueInBase);

        return new Length(round(converted), targetUnit);
    }

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        return new Length(value, from).convertTo(to).getValue();
    }

    // UC6 - Addition (result in first operand unit)
    public Length add(Length other) {
        if (other == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }

        double sumBase = this.toBase() + other.toBase();
        double sumInThisUnit = unit.convertFromBaseUnit(sumBase);

        return new Length(round(sumInThisUnit), unit);
    }


    // UC7 - Addition with explicit target unit
    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        double sumBase = this.toBase() + other.toBase();
        double sumInTarget = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(round(sumInTarget), targetUnit);
    }

    public static Length add(Length l1, Length l2, LengthUnit targetUnit) {
        if (l1 == null || l2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        return l1.add(l2, targetUnit);
    }

    public static double add(double v1, LengthUnit u1,
                             double v2, LengthUnit u2,
                             LengthUnit targetUnit) {

        return add(new Length(v1, u1),
                new Length(v2, u2),
                targetUnit).getValue();
    }


    // UC1–UC3 - Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Length)) return false;

        Length other = (Length) obj;

        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseRounded = round(this.toBase());
        return Double.hashCode(baseRounded);
    }

    // Getters
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
