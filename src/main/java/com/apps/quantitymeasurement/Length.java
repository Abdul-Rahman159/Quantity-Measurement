package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    // Constructor
    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Getter methods
    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit() {
        return value * unit.getConversionFactorToInch();
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // If same unit, return new instance with same value
        if (this.unit == targetUnit) {
            return new Length(value, targetUnit);
        }

        // Convert to base unit (inches)
        double valueInInches = convertToBaseUnit();

        // Convert from inches to target unit
        double convertedValue = valueInInches / targetUnit.getConversionFactorToInch();

        // Round to 4 decimal places for consistency
        convertedValue = Math.round(convertedValue * 10000.0) / 10000.0;

        return new Length(convertedValue, targetUnit);
    }

    public static double convert(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a finite number");
        }

        // Create temporary Length object and convert
        Length length = new Length(value, fromUnit);
        Length convertedLength = length.convertTo(toUnit);
        return convertedLength.getValue();
    }

    /**
     * Compare two Length objects for equality
     */
    private boolean compare(Length thatLength) {
        if (thatLength == null) return false;

        double thisValueInInches = this.convertToBaseUnit();
        double otherValueInInches = thatLength.convertToBaseUnit();

        // Using tolerance for floating point comparison
        return Math.abs(thisValueInInches - otherValueInInches) < 0.01;
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
        return value+" " +unit.name().toLowerCase();
    }


    public static void main(String[] args) {
        System.out.println("=== Length Conversion Tests ===\n");

        // Test 1: Feet to Inches
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = feet.convertTo(LengthUnit.INCHES);
        System.out.println("3.0 feet = " + inches.getValue() + " inches"); // 36.0

        // Test 2: Static conversion method
        double result = Length.convert(1.0, LengthUnit.YARDS, LengthUnit.FEET);
        System.out.println("1.0 yard = " + result + " feet");

        // Test 3: Centimeters to Inches
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inches2 = cm.convertTo(LengthUnit.INCHES);
        System.out.println("2.54 cm = " + inches2.getValue() + " inches");
    }
}