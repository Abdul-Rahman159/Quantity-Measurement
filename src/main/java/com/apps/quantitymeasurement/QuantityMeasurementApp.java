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
        System.out.println("=== UC11: Volume Measurement ===\n");

        // Length operations
        Quantity<VolumeUnit> litre1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> litre2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Volume Equality:");
        demonstrateEquality(litre1, litre2);               // false (1L != 2L)
        demonstrateEquality(litre1, milli);                // true  (1L == 1000mL)
        demonstrateEquality(litre1, gallon);               // true  (1L ≈ 0.264 gal)

        System.out.println("\nVolume Conversion:");
        demonstrateConversion(litre1, VolumeUnit.MILLILITRE);  // 1L → 1000 mL
        demonstrateConversion(gallon, VolumeUnit.LITRE);       // 1 gal → 3.78541 L

        System.out.println("\nVolume Addition:");
        demonstrateAddition(litre1, litre2);                    // 1L + 2L = 3L
        demonstrateAddition(litre1, milli);                     // 1L + 1000mL = 2L
        demonstrateAddition(litre1, gallon, VolumeUnit.MILLILITRE); // 1L + 1gal in mL
    }
}