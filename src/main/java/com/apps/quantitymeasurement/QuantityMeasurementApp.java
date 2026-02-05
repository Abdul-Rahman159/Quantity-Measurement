package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC6 method (for backward compatibility)
    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        System.out.print("UC6 Method: " + length1 + " + " + length2 + " = ");
        Length result = length1.add(length2);
        System.out.println(result + " (in " + length1.getUnit() + ")");
        return result;
    }

    // UC7 NEW: Addition with target unit (Method Overloading)
    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {
        System.out.print("UC7 Method: " + length1 + " + " + length2 + " in " + targetUnit + " = ");
        Length result = length1.add(length2, targetUnit);
        System.out.println(result);
        return result;
    }

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println(length1 + " equals " + length2 + ": " + result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== UC7: Addition with Target Unit Specification ===\n");

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);

        System.out.println(" =============UC7 Examples==========:");
        System.out.println("----------------------------");

        // UC6 way
        demonstrateLengthAddition(feet, inches);  // Result: 2.0 FEET (always in first operand's unit)

        // UC7 ways (same addition, different target units)
        demonstrateLengthAddition(feet, inches, LengthUnit.FEET);      // 2.0 FEET
        demonstrateLengthAddition(feet, inches, LengthUnit.INCHES);    // 24.0 INCHES
        demonstrateLengthAddition(feet, inches, LengthUnit.YARDS);     // 0.6667 YARDS
        demonstrateLengthAddition(feet, inches, LengthUnit.CENTIMETERS); // 60.96 CM

        System.out.println("\n2. More UC7 Examples:");
        System.out.println("----------------------------");

        // Yards + Feet in different target units
        demonstrateLengthAddition(yard, new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);  // 2.0 YARDS
        demonstrateLengthAddition(yard, new Length(3.0, LengthUnit.FEET), LengthUnit.FEET);   // 6.0 FEET
        demonstrateLengthAddition(yard, new Length(3.0, LengthUnit.FEET), LengthUnit.INCHES); // 72.0 INCHES

        // CM + Inches
        demonstrateLengthAddition(cm, new Length(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS);  // 5.08 CM
        demonstrateLengthAddition(cm, new Length(1.0, LengthUnit.INCHES), LengthUnit.INCHES);       // 2.0 INCHES


        System.out.println("\n4. Edge Cases:");
        System.out.println("----------------------------");

        // Zero value
        demonstrateLengthAddition(feet, new Length(0.0, LengthUnit.INCHES), LengthUnit.YARDS);

        // Negative values
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET),
                new Length(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES);

    }
}

