package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches, LengthUnit.INCHES);
        assertEquals(24.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        Length result = feet.add(inches, LengthUnit.YARDS);
        assertEquals(0.6667, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {
        Length inch1 = new Length(1.0, LengthUnit.INCHES);
        Length inch2 = new Length(1.0, LengthUnit.INCHES);
        Length result = inch1.add(inch2, LengthUnit.CENTIMETERS);
        assertEquals(5.08, result.getValue(), 0.01);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        Length yard = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length result = yard.add(feet, LengthUnit.YARDS);
        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        Length yard = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length result = yard.add(feet, LengthUnit.FEET);
        assertEquals(9.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        Length result1 = feet.add(inches, LengthUnit.YARDS);
        Length result2 = inches.add(feet, LengthUnit.YARDS);

        assertTrue(result1.equals(result2));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {
        Length feet = new Length(5.0, LengthUnit.FEET);
        Length zero = new Length(0.0, LengthUnit.INCHES);
        Length result = feet.add(zero, LengthUnit.YARDS);
        assertEquals(1.6667, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {
        Length pos = new Length(5.0, LengthUnit.FEET);
        Length neg = new Length(-2.0, LengthUnit.FEET);
        Length result = pos.add(neg, LengthUnit.INCHES);
        assertEquals(36.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            feet.add(inches, null);
        });
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        Length large1 = new Length(1000.0, LengthUnit.FEET);
        Length large2 = new Length(500.0, LengthUnit.FEET);
        Length result = large1.add(large2, LengthUnit.INCHES);
        assertEquals(18000.0, result.getValue(), 0.1);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        Length small1 = new Length(12.0, LengthUnit.INCHES);
        Length small2 = new Length(12.0, LengthUnit.INCHES);
        Length result = small1.add(small2, LengthUnit.YARDS);
        assertEquals(0.6667, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testMethodOverloadingWorks() {
        // Test that both UC6 and UC7 methods work together
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        // UC6 method (no target unit)
        Length uc6Result = feet.add(inches);
        assertEquals(2.0, uc6Result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, uc6Result.getUnit());

        // UC7 method (with target unit)
        Length uc7Result = feet.add(inches, LengthUnit.INCHES);
        assertEquals(24.0, uc7Result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, uc7Result.getUnit());

        // Both should represent the same physical length
        assertTrue(uc6Result.convertTo(LengthUnit.INCHES).equals(uc7Result));
    }

    @Test
    public void testAllUnitCombinations() {
        // Test various unit combinations
        Length[] lengths = {
                new Length(1.0, LengthUnit.FEET),
                new Length(12.0, LengthUnit.INCHES),
                new Length(1.0, LengthUnit.YARDS),
                new Length(2.54, LengthUnit.CENTIMETERS)
        };

        LengthUnit[] targetUnits = {
                LengthUnit.FEET,
                LengthUnit.INCHES,
                LengthUnit.YARDS,
                LengthUnit.CENTIMETERS
        };

        // Test some combinations
        for (Length l1 : lengths) {
            for (Length l2 : lengths) {
                for (LengthUnit target : targetUnits) {
                    Length result = l1.add(l2, target);
                    assertEquals(target, result.getUnit());

                    // Verify result is not null
                    assertNotNull(result);

                    // Verify value is finite
                    assertTrue(Double.isFinite(result.getValue()));
                }
            }
        }
    }

    @Test
    public void testAddition_ExplicitTargetUnit_PrecisionTolerance() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length cm = new Length(30.48, LengthUnit.CENTIMETERS); // = 1 foot

        Length result = feet.add(cm, LengthUnit.YARDS);

        assertEquals(0.6667, result.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }
}