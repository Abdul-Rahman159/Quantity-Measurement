package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ----- UC8: Standalone LengthUnit Tests -----

    @Test
    public void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.getConversionFactorToBase(), 0.0001);
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0/12.0, LengthUnit.INCHES.getConversionFactorToBase(), 0.0001);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactorToBase(), 0.0001);
    }

    @Test
    public void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0/30.48, LengthUnit.CENTIMETERS.getConversionFactorToBase(), 0.0001);
    }

    // ----- UC8: convertToBaseUnit() Tests -----

    @Test
    public void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.0001);
    }

    // ----- UC8: convertFromBaseUnit() Tests -----

    @Test
    public void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), 0.0001);
    }

    // ----- UC8: Refactored Length Delegation Tests -----

    @Test
    public void testQuantityLengthRefactored_Equality() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length result = feet.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_Add() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches);
        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_AddWithTargetUnit() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches, LengthUnit.YARDS);
        assertEquals(0.6667, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.NaN, LengthUnit.FEET);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Length(Double.POSITIVE_INFINITY, LengthUnit.FEET);
        });
    }

    // ----- UC8: Round-Trip Conversion Test -----

    @Test
    public void testRoundTripConversion_RefactoredDesign() {
        double originalValue = 5.0;
        LengthUnit fromUnit = LengthUnit.YARDS;
        LengthUnit toUnit = LengthUnit.INCHES;

        // Convert: yards -> inches
        Length length1 = new Length(originalValue, fromUnit);
        Length converted = length1.convertTo(toUnit);

        // Convert back: inches -> yards
        Length convertedBack = converted.convertTo(fromUnit);

        // Should get original value within epsilon
        assertEquals(originalValue, convertedBack.getValue(), 0.0001);
        assertEquals(fromUnit, convertedBack.getUnit());
    }

    @Test
    public void testBackwardCompatibility_StaticConvertMethod() {
        // UC5 static convert method should still work
        double result = Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result, 0.0001);
    }
}