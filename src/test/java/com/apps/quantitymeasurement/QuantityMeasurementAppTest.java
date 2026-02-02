package com.apps.quantitymeasurement;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuantityMeasurementAppTest {

    @Test
    public void yardEquals36Inches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);
        assertTrue(yard.equals(inches));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue(feet.equals(yard));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length cm = new Length(100.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(39.3701, LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length cm = new Length(30.48, LengthUnit.CENTIMETERS);
        Length foot = new Length(1.0, LengthUnit.FEET);
        assertTrue(cm.equals(foot));
    }

    @Test
    public void yardNotEqualToInches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(35.0, LengthUnit.INCHES);
        assertFalse(yard.equals(inches));
    }

    @Test
    public void yardToYardSameValue() {
        Length yard1 = new Length(2.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);
        assertTrue(yard1.equals(yard2));
    }

    @Test
    public void yardToYardDifferentValue() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    @Test
    public void centimeterToCentimeterSameValue() {
        Length cm1 = new Length(50.0, LengthUnit.CENTIMETERS);
        Length cm2 = new Length(50.0, LengthUnit.CENTIMETERS);
        assertTrue(cm1.equals(cm2));
    }

    @Test
    public void centimeterToCentimeterDifferentValue() {
        Length cm1 = new Length(10.0, LengthUnit.CENTIMETERS);
        Length cm2 = new Length(20.0, LengthUnit.CENTIMETERS);
        assertFalse(cm1.equals(cm2));
    }

    @Test
    public void yardToFeetEquivalentValue() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(yard)); // Symmetry
    }

    @Test
    public void centimeterToInchesEquivalentValue() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
        assertTrue(inches.equals(cm)); // Symmetry
    }

    @Test
    public void yardToFeetNonEquivalentValue() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(2.0, LengthUnit.FEET);
        assertFalse(yard.equals(feet));
    }

    @Test
    public void centimeterToFeetNonEquivalentValue() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, LengthUnit.FEET);
        assertFalse(cm.equals(feet));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertFalse(yard.equals(null));
    }

    @Test
    public void differentClassComparison() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        String str = "yard";
        assertFalse(yard.equals(str));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        // Reflexive: A = A
        Length yard = new Length(1.0, LengthUnit.YARDS);
        assertTrue(yard.equals(yard));

        // Symmetric: if A = B then B = A
        Length feet = new Length(3.0, LengthUnit.FEET);
        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(yard));

        // Transitive: if A = B and B = C then A = C
        Length inches = new Length(36.0, LengthUnit.INCHES);
        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length yard2 = new Length(2.0, LengthUnit.YARDS);
        assertFalse(yard1.equals(yard2));
    }

    @Test
    public void allUnitsComplexScenario() {
        // 2 yards = 6 feet = 72 inches = 182.88 cm
        Length yard = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(6.0, LengthUnit.FEET);
        Length inches = new Length(72.0, LengthUnit.INCHES);
        Length cm = new Length(182.88, LengthUnit.CENTIMETERS);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
        assertTrue(cm.equals(inches));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        boolean result = QuantityMeasurementApp.demonstrateLengthComparison(
                1.0, LengthUnit.YARDS,
                36.0, LengthUnit.INCHES
        );
        assertTrue(result);
    }
}
