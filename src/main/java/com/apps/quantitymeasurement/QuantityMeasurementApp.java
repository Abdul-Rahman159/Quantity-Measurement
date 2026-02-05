package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        return length1.add(length2);
    }

    // REQUIRED for test: addFeetAndInches()
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }


    public static void main(String[] args) {
        System.out.println("=== Quantity Measurement App (UC1-UC6) ===\n");

        // Create sample lengths
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);

        // UC1-UC4: Equality demonstrations
        System.out.println("1. Equality Checks:");
        System.out.println("   1 ft = 12 inches? " + feet.equals(inches));
        System.out.println("   1 yd = 3 ft? " + yard.equals(new Length(3.0, LengthUnit.FEET)));
        System.out.println("   2.54 cm = 1 inches? " + cm.equals(new Length(1.0, LengthUnit.INCHES)));

        // UC5: Conversion demonstrations
        System.out.println("\n2. Conversion Checks:");
        System.out.println("   1 ft = " + feet.convertTo(LengthUnit.INCHES));
        System.out.println("   1 yd = " + yard.convertTo(LengthUnit.CENTIMETERS));

        // UC6: Addition demonstrations
        System.out.println("\n3. Addition Checks:");
        System.out.println("   1 ft + 12 inches = " + feet.add(inches) + " ft");
        System.out.println("   1 yd + 3 ft = " + yard.add(new Length(3.0, LengthUnit.FEET)) + " yd");
    }
}
