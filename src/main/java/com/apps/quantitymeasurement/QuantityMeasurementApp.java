package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        boolean result = l1.equals(l2);
        System.out.println(l1 + " equals " + l2 + " : " + result);
        return result;
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit targetUnit) {
        Length converted = length.convertTo(targetUnit);
        System.out.println(length + " -> " + converted);
        return converted;
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        Length result = l1.add(l2);
        System.out.println(l1 + " + " + l2 + " = " + result);
        return result;
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, LengthUnit targetUnit) {
        Length result = l1.add(l2, targetUnit);
        System.out.println(l1 + " + " + l2 + " in " + targetUnit + " = " + result);
        return result;
    }

    public static void main(String[] args) {

        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        demonstrateLengthEquality(feet, inches);
        demonstrateLengthConversion(feet, LengthUnit.INCHES);
        demonstrateLengthAddition(feet, inches);
        demonstrateLengthAddition(feet, inches, LengthUnit.YARDS);
    }
}
