package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void addFeetAndInches() {
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(length1, length2);
        Length expectedLength = new Length(2.0, LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(2.0, LengthUnit.FEET);
        Length result = feet1.add(feet2);
        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {
        Length inch1 = new Length(6.0, LengthUnit.INCHES);
        Length inch2 = new Length(6.0, LengthUnit.INCHES);
        Length result = inch1.add(inch2);
        assertEquals(12.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches);
        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet() {
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length result = inches.add(feet);
        assertEquals(24.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length result = yard.add(feet);
        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);
        Length result = cm.add(inch);
        assertEquals(5.08, result.getValue(), 0.01); // Allow 1% tolerance
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length result1 = feet.add(inches);
        Length result2 = inches.add(feet);

        // They should be equal when converted to same unit
        Length result1InInches = result1.convertTo(LengthUnit.INCHES);
        assertTrue(result2.equals(result1InInches));
    }

    @Test
    public void testAddition_WithZero() {
        Length feet = new Length(5.0, LengthUnit.FEET);
        Length zero = new Length(0.0, LengthUnit.INCHES);
        Length result = feet.add(zero);
        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_NegativeValues() {
        Length pos = new Length(5.0, LengthUnit.FEET);
        Length neg = new Length(-2.0, LengthUnit.FEET);
        Length result = pos.add(neg);
        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_NullSecondOperand() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> {
            feet.add(null);
        });
    }

    @Test
    public void testAddition_LargeValues() {
        Length large1 = new Length(1e6, LengthUnit.FEET);
        Length large2 = new Length(1e6, LengthUnit.FEET);
        Length result = large1.add(large2);
        assertEquals(2e6, result.getValue(), 0.1); // Allow small tolerance
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_SmallValues() {
        Length small1 = new Length(0.001, LengthUnit.FEET);
        Length small2 = new Length(0.002, LengthUnit.FEET);
        Length result = small1.add(small2);
        assertEquals(0.003, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testStaticAddMethod() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = Length.add(feet, inches, LengthUnit.YARDS);

        assertEquals(0.6667, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testStaticAddMethodWithTargetUnit() {
        double result = Length.add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES, LengthUnit.YARDS);
        assertEquals(0.6667, result, 0.0001);
    }

    @Test
    public void testAddition_IdentityElement() {
        // Adding zero should return same value
        Length length = new Length(10.0, LengthUnit.YARDS);
        Length zero = new Length(0.0, LengthUnit.FEET);
        Length result = length.add(zero);
        assertTrue(length.equals(result));
    }

    @Test
    public void testAddition_Associativity() {
        // (a + b) + c = a + (b + c)
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);
        Length c = new Length(1.0, LengthUnit.YARDS);

        Length left = a.add(b).add(c);
        Length right = a.add(b.add(c));

        assertTrue(left.equals(right));
    }
}