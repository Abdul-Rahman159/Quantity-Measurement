package com.apps.quantitymeasurement;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertThrows;

public class QuantityMeasurementAppTest {

    @Test
    public void yardToinch(){
        Length yard = new Length(1,LengthUnit.YARDS);
        Length inches = new Length(36,LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    @Test
    public void convertFeetToInches() {
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(
                3.0, LengthUnit.FEET, LengthUnit.INCHES);
        Length expectedLength = new Length(36.0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedLength));
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length lengthInYards = new Length(2.0, LengthUnit.YARDS);
        Length lengthInInches = QuantityMeasurementApp.demonstrateLengthConversion(
                lengthInYards, LengthUnit.INCHES);
        Length expectedLength = new Length(72.0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(lengthInInches, expectedLength));
    }

    @Test
    public void testConversion_FeetToInches() {
        double result = Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result, 0.0001);
    }

    @Test
    public void testConversion_InchesToFeet() {
        double result = Length.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testConversion_YardsToInches() {
        double result = Length.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        assertEquals(36.0, result, 0.0001);
    }

    @Test
    public void testConversion_InchesToYards() {
        double result = Length.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testConversion_CentimetersToInches() {
        double result = Length.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testConversion_FeetToYard() {
        double result = Length.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        double original = 10.5;
        // Feet -> Inches -> Feet
        double inInches = Length.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double backToFeet = Length.convert(inInches, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(original, backToFeet, 0.0001);
    }

    @Test
    public void testConversion_ZeroValue() {
        double result = Length.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testConversion_NegativeValue() {
        double result = Length.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(-12.0, result, 0.0001);
    }

    @Test
    public void testConversion_SameUnit() {
        double result = Length.convert(5.0, LengthUnit.FEET, LengthUnit.FEET);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void testConversion_LargeValue() {
        double result = Length.convert(1000.0, LengthUnit.YARDS, LengthUnit.INCHES);
        assertEquals(36000.0, result, 0.0001);
    }

    @Test
    public void testConversion_SmallValue() {
        double result = Length.convert(0.001, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(0.012, result, 0.0001);
    }

    @Test
    public void testConversion_InvalidUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(1.0, null, LengthUnit.FEET);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(1.0, LengthUnit.FEET, null);
        });
    }

    @Test
    public void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }

    @Test
    public void testConversion_Infinite_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Length.convert(Double.NEGATIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }

    @Test
    public void testConvertToMethod() {
        Length feet = new Length(2.0, LengthUnit.FEET);
        Length inches = feet.convertTo(LengthUnit.INCHES);
        assertEquals(24.0, inches.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, inches.getUnit());
    }

    @Test
    public void testConvertTo_SameUnit() {
        Length original = new Length(5.0, LengthUnit.YARDS);
        Length converted = original.convertTo(LengthUnit.YARDS);
        assertEquals(5.0, converted.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, converted.getUnit());
    }

    @Test
    public void testComplexConversionChain() {
        // Yards -> Feet -> Inches -> Centimeters -> Back to Yards
        Length yards = new Length(1.0, LengthUnit.YARDS);
        Length feet = yards.convertTo(LengthUnit.FEET);
        Length inches = feet.convertTo(LengthUnit.INCHES);
        Length cm = inches.convertTo(LengthUnit.CENTIMETERS);
        Length backToYards = cm.convertTo(LengthUnit.YARDS);

        assertEquals(1.0, backToYards.getValue(), 0.01); // Allow 1% tolerance for cumulative errors
    }

    @Test
    public void testToStringMethod() {
        Length length = new Length(12.3456, LengthUnit.FEET);
        String str = length.toString();
        assertTrue(str.contains("12.3456"));
        assertTrue(str.contains("feet"));
    }
}