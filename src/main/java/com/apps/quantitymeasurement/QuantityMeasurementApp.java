package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // ----- Existing generic methods -----

    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        boolean result = q1.equals(q2);
        System.out.println(q1 + " equals " + q2 + ": " + result);
        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(double value, U fromUnit, U toUnit) {
        System.out.print("Converting " + value + " " + fromUnit.getUnitName() + " to " + toUnit.getUnitName() + ": ");
        Quantity<U> converted = new Quantity<>(value, fromUnit).convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit.getUnitName());
        return converted;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U toUnit) {
        System.out.print("Converting " + quantity + " to " + toUnit.getUnitName() + ": ");
        Quantity<U> converted = quantity.convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit.getUnitName());
        return converted;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2) {
        System.out.print("Adding " + q1 + " + " + q2 + ": ");
        Quantity<U> result = q1.add(q2);
        System.out.println("= " + result);
        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.print("Adding " + q1 + " + " + q2 + " in " + targetUnit.getUnitName() + ": ");
        Quantity<U> result = q1.add(q2, targetUnit);
        System.out.println("= " + result);
        return result;
    }

    // ----- UC12: Subtraction demonstrations -----

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2) {
        System.out.print("Subtracting " + q2 + " from " + q1 + ": ");
        Quantity<U> result = q1.subtract(q2);
        System.out.println("= " + result);
        return result;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> q1, Quantity<U> q2, U targetUnit) {
        System.out.print("Subtracting " + q2 + " from " + q1 + " in " + targetUnit.getUnitName() + ": ");
        Quantity<U> result = q1.subtract(q2, targetUnit);
        System.out.println("= " + result);
        return result;
    }

    // ----- UC12: Division demonstrations -----

    public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> q1, Quantity<U> q2) {
        System.out.print("Dividing " + q1 + " by " + q2 + ": ");
        double result = q1.divide(q2);
        System.out.println("= " + result);
        return result;
    }

    // ----- Main method with examples -----

    public static void main(String[] args) {
        System.out.println("=== UC12: Subtraction and Division ===\n");

        // Length examples
        Quantity<LengthUnit> feet10 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> feet5 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches6 = new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Length Subtraction:");
        demonstrateSubtraction(feet10, feet5);               // 5.0 FEET
        demonstrateSubtraction(feet10, inches6);             // 9.5 FEET
        demonstrateSubtraction(feet10, inches6, LengthUnit.INCHES); // 114.0 INCHES

        System.out.println("\nLength Division:");
        demonstrateDivision(feet10, feet5);                  // 2.0
        demonstrateDivision(new Quantity<>(24.0, LengthUnit.INCHES), feet5);
        Quantity<LengthUnit> inches24 = new Quantity<>(24.0, LengthUnit.INCHES);
        demonstrateDivision(inches24, feet5);                // 24 / 60 = 0.4

        // Weight examples
        Quantity<WeightUnit> kg10 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg5 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g2000 = new Quantity<>(2000.0, WeightUnit.GRAM);

        System.out.println("\nWeight Subtraction:");
        demonstrateSubtraction(kg10, kg5);                   // 5.0 KILOGRAM
        demonstrateSubtraction(kg10, g2000);                 // 8.0 KILOGRAM

        System.out.println("\nWeight Division:");
        demonstrateDivision(kg10, kg5);
        demonstrateDivision(g2000, kg5);

        // Volume examples
        Quantity<VolumeUnit> litre5 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> litre2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli500 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);

        System.out.println("\nVolume Subtraction:");
        demonstrateSubtraction(litre5, litre2);              // 3.0 LITRE
        demonstrateSubtraction(litre5, milli500);            // 4.5 LITRE
        demonstrateSubtraction(litre5, milli500, VolumeUnit.MILLILITRE); // 4500.0 MILLILITRE

        System.out.println("\nVolume Division:");
        demonstrateDivision(litre5, litre2);
        demonstrateDivision(milli500, litre2);
    }
}