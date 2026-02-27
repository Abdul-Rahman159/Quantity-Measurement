package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Generic equality demonstration
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        boolean result = q1.equals(q2);
        System.out.println(q1 + " equals " + q2 + ": " + result);
        return result;
    }

    // Generic conversion demonstration (value + units)
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(double value, U fromUnit, U toUnit) {
        System.out.print("Converting " + value + " " + fromUnit.getUnitName() + " to " + toUnit.getUnitName() + ": ");
        Quantity<U> converted = new Quantity<>(value, fromUnit).convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit.getUnitName());
        return converted;
    }

    // Generic conversion demonstration (Quantity instance)
    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U toUnit) {
        System.out.print("Converting " + quantity + " to " + toUnit.getUnitName() + ": ");
        Quantity<U> converted = quantity.convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit.getUnitName());
        return converted;
    }

    // Generic addition demonstration (result in first operand's unit)
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2) {
        System.out.print("Adding " + q1 + " + " + q2 + ": ");
        Quantity<U> result = q1.add(q2);
        System.out.println("= " + result);
        return result;
    }

    // Generic addition demonstration with target unit
    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.print("Adding " + q1 + " + " + q2 + " in " + targetUnit.getUnitName() + ": ");
        Quantity<U> result = q1.add(q2, targetUnit);
        System.out.println("= " + result);
        return result;
    }

    // Main method demonstrating both length and weight operations with generic methods
    public static void main(String[] args) {
        System.out.println("=== UC10: Generic Quantity Class ===\n");

        // Length operations
        Quantity<LengthUnit> len1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> len2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Operations:");
        demonstrateEquality(len1, len2);                         // true
        demonstrateConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateAddition(len1, len2);                         // 2.0 FEET
        demonstrateAddition(len1, len2, LengthUnit.YARDS);       // ~0.6667 YARDS

        // Weight operations
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> w3 = new Quantity<>(2.20462, WeightUnit.POUND);

        System.out.println("\nWeight Operations:");
        demonstrateEquality(w1, w2);                             // true
        demonstrateEquality(w1, w3);                             // true (with tolerance)
        demonstrateConversion(1.0, WeightUnit.KILOGRAM, WeightUnit.POUND);
        demonstrateAddition(w1, w2);                             // 2.0 KILOGRAM
        demonstrateAddition(w1, w3, WeightUnit.GRAM);            // ~2000.0 GRAM

        // Cross-category prevention
        System.out.println("\nCross-Category Check:");
        System.out.println("1 ft equals 1 kg? " + len1.equals(w1)); // false
    }
}