package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println(length1 + " equals " + length2 + ": " + result);
        return result;
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1,
                                                      double value2, LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return demonstrateLengthEquality(length1, length2);
    }

    /**
     * Method Overloading - Version 1:
     * Convert a value from one unit to another
     */
    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit,
                                                     LengthUnit toUnit) {
        System.out.print("Converting " + value + " " + fromUnit + " to " + toUnit + ": ");

        Length length = new Length(value, fromUnit);
        Length convertedLength = length.convertTo(toUnit);

        System.out.println(convertedLength.getValue() + " " + toUnit);
        return convertedLength;
    }

    /**
     * Method Overloading - Version 2:
     * Convert an existing Length object to a different unit
     */
    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        System.out.print("Converting " + length + " to " + toUnit + ": ");

        Length convertedLength = length.convertTo(toUnit);

        System.out.println(convertedLength.getValue() + " " + toUnit);
        return convertedLength;
    }


//     Main method to demonstrate all conversion features
    public static void main(String[] args) {
        System.out.println("=== UC5: Unit-to-Unit Conversion ===\n");

        // Demonstrate basic conversions using method overloading
        System.out.println("1. Basic Conversions:");
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        System.out.println("\n2. Method Overloading Examples:");
        // Using existing Length object
        Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);

        Length lengthInFeet = new Length(5.0, LengthUnit.FEET);
        demonstrateLengthConversion(lengthInFeet, LengthUnit.CENTIMETERS);

        System.out.println("\n3. Round-trip Conversions:");
        // Convert feet to inches and back
        Length original = new Length(10.0, LengthUnit.FEET);
        Length inInches = demonstrateLengthConversion(original, LengthUnit.INCHES);
        Length backToFeet = demonstrateLengthConversion(inInches, LengthUnit.FEET);

        System.out.println("Original: " + original.getValue() + " ft");
        System.out.println("After round-trip: " + backToFeet.getValue() + " ft");
        System.out.println("Preserved: " + original.equals(backToFeet));

        System.out.println("\n4. Negative Value Conversion:");
        demonstrateLengthConversion(-2.0, LengthUnit.FEET, LengthUnit.INCHES);

        System.out.println("\n5. Same Unit Conversion:");
        demonstrateLengthConversion(5.0, LengthUnit.FEET, LengthUnit.FEET);

        System.out.println("\n6. Complex Conversions:");
        demonstrateLengthConversion(1.0, LengthUnit.YARDS, LengthUnit.CENTIMETERS);
        demonstrateLengthConversion(100.0, LengthUnit.CENTIMETERS, LengthUnit.YARDS);

        // Demonstrate static conversion method from Length class
        System.out.println("\n7. Static Method Conversion:");
        double staticResult = Length.convert(2.0, LengthUnit.YARDS, LengthUnit.INCHES);
        System.out.println("Static conversion: 2.0 yards = " + staticResult + " inches");
    }
}
