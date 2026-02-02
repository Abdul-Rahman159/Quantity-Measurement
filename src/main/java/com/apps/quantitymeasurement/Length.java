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
        // Using tolerance for floating point comparison
        return Math.abs(thisValueInInches - otherValueInInches) < 0.001;
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


    public static void main(String[] args) {
        //  Feet to Inches
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("1.0 ft equals 12.0 inches: " + length1.equals(length2)); // true

        // Yards to Inches
        Length length3 = new Length(1.0, LengthUnit.YARDS);
        Length length4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("1.0 yard equals 36.0 inches: " + length3.equals(length4)); // true

        // Centimeters to Inches
        Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length6 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("100.0 cm equals 39.3701 inches: " + length5.equals(length6)); // true

        // Yards to Feet
        Length length7 = new Length(1.0, LengthUnit.YARDS);
        Length length8 = new Length(3.0, LengthUnit.FEET);
        System.out.println("1.0 yard equals 3.0 feet: " + length7.equals(length8)); // true

        // Complex scenario
        Length length9 = new Length(2.0, LengthUnit.YARDS);
        Length length10 = new Length(6.0, LengthUnit.FEET);
        Length length11 = new Length(72.0, LengthUnit.INCHES);
        System.out.println("2.0 yards = 6.0 feet = 72.0 inches: " +
                (length9.equals(length10) && length10.equals(length11))); // true
    }
}
