package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Generic method to demonstrate length equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println(length1 + " equals " + length2 + ": " + result);
        return result;
    }

    // Method to demonstrate comparison with parameters
    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1,
                                                      double value2, LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return demonstrateLengthEquality(length1, length2);
    }

    // Main method to demonstrate all comparisons
    public static void main(String[] args) {
        System.out.println("=== UC4: Extended Unit Support (Yards & Centimeters) ===\n");

        // Demonstrate Feet and Inches comparison
        System.out.println("1. Feet and Inches Comparison:");
        demonstrateLengthComparison(2, LengthUnit.FEET, 24.0, LengthUnit.INCHES);

        // Demonstrate Yards and Inches comparison
        System.out.println("\n2. Yards and Inches Comparison:");
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 36.0, LengthUnit.INCHES);

        // Demonstrate Centimeters and Inches comparison
        System.out.println("\n3. Centimeters and Inches Comparison:");
        demonstrateLengthComparison(100.0, LengthUnit.CENTIMETERS, 39.3701, LengthUnit.INCHES);

        // Demonstrate Feet and Yards comparison
        System.out.println("\n4. Feet and Yards Comparison:");
        demonstrateLengthComparison(3.0, LengthUnit.FEET, 1.0, LengthUnit.YARDS);

        // Demonstrate Centimeters and Feet comparison
        System.out.println("\n5. Centimeters and Feet Comparison:");
        demonstrateLengthComparison(30.48, LengthUnit.CENTIMETERS, 1.0, LengthUnit.FEET);

        // Demonstrate complex multi-unit comparison
        System.out.println("\n6. Complex Multi-Unit Comparison:");
        Length length1 = new Length(2.0, LengthUnit.YARDS);
        Length length2 = new Length(6.0, LengthUnit.FEET);
        Length length3 = new Length(72.0, LengthUnit.INCHES);
        System.out.println("2.0 yards = 6.0 feet: " + length1.equals(length2));
        System.out.println("6.0 feet = 72.0 inches: " + length2.equals(length3));
        System.out.println("2.0 yards = 72.0 inches: " + length1.equals(length3));

        // Demonstrate non-equality
        System.out.println("\n7. Non-Equality Demonstrations:");
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 35.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.CENTIMETERS, 1.0, LengthUnit.FEET);
    }
}
