package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Feet equality using Length class
    public static void demonstrateFeetEquality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);
        Length feet3 = new Length(2.0, LengthUnit.FEET);

        System.out.println("\n=== Feet Equality ===");
        System.out.println("1.0 ft equals 1.0 ft: " + feet1.equals(feet2)); // true
        System.out.println("1.0 ft equals 2.0 ft: " + feet1.equals(feet3)); // false
    }

    // Inches equality using Length class
    public static void demonstrateInchesEquality() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);
        Length inches3 = new Length(2.0, LengthUnit.INCHES);

        System.out.println("\n=== Inches Equality ===");
        System.out.println("1.0 inch equals 1.0 inch: " + inches1.equals(inches2)); // true
        System.out.println("1.0 inch equals 2.0 inch: " + inches1.equals(inches3)); // false
    }

    // Cross-unit comparison
    public static void demonstrateFeetInchesComparison() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length inches2 = new Length(13.0, LengthUnit.INCHES);

        System.out.println("\n=== Feet-Inches Comparison ===");
        System.out.println("1.0 ft equals 12.0 inches: " + feet.equals(inches)); // true
        System.out.println("1.0 ft equals 13.0 inches: " + feet.equals(inches2)); // false
        System.out.println("12.0 inches equals 1.0 ft: " + inches.equals(feet)); // true (symmetric)
    }

    //  Generic equality check method
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println(length1 + " equals " + length2 + ": " + result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Quantity Measurement Application ===");
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();

        System.out.println("\n=== Generic Method Demo ===");
        Length length1 = new Length(3.0, LengthUnit.FEET);
        Length length2 = new Length(36.0, LengthUnit.INCHES);
        Length length3 = new Length(2.0, LengthUnit.FEET);

        demonstrateLengthEquality(length1, length2); // true
        demonstrateLengthEquality(length1, length3); // false
    }
}
