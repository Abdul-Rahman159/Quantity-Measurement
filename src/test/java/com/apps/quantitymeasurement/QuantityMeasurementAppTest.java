package com.apps.quantitymeasurement;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);
        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(2.0, LengthUnit.FEET);
        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testInchesEquality_SameValue() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(1.0, LengthUnit.INCHES);
        assertTrue(inches1.equals(inches2));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        Length inches1 = new Length(1.0, LengthUnit.INCHES);
        Length inches2 = new Length(2.0, LengthUnit.INCHES);
        assertFalse(inches1.equals(inches2));
    }

    @Test
    public void testFeetInchesComparison_Equivalent() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test
    public void testFeetInchesComparison_NotEquivalent() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(13.0, LengthUnit.INCHES);
        assertFalse(feet.equals(inches));
    }

    @Test
    public void testInchesFeetComparison_Equivalent() {
        Length inches = new Length(24.0, LengthUnit.INCHES);
        Length feet = new Length(2.0, LengthUnit.FEET);
        assertTrue(inches.equals(feet));
    }

    @Test
    public void testNullComparison() {
        Length length = new Length(1.0, LengthUnit.FEET);
        assertFalse(length.equals(null));
    }

    @Test
    public void testDifferentClass() {
        Length length = new Length(1.0, LengthUnit.FEET);
        String str = "test";
        assertFalse(length.equals(str));
    }

    @Test
    public void testSameReference() {
        Length length = new Length(1.0, LengthUnit.FEET);
        assertTrue(length.equals(length));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length length1 = new Length(3.0, LengthUnit.FEET);
        Length length2 = new Length(36.0, LengthUnit.INCHES);
        Length length3 = new Length(2.0, LengthUnit.FEET);

        assertTrue(length1.equals(length2));  // 3 ft = 36 inches
        assertFalse(length1.equals(length3)); // 3 ft ≠ 2 ft
        assertFalse(length2.equals(length3)); // 36 inches ≠ 2 ft
    }

    @Test
    public void testSymmetryProperty() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        // Symmetric: if a.equals(b) then b.equals(a)
        assertTrue(feet.equals(inches));
        assertTrue(inches.equals(feet));
    }

    @Test
    public void testTransitivityProperty() {
        Length length1 = new Length(1.0, LengthUnit.FEET);    // 1 ft
        Length length2 = new Length(12.0, LengthUnit.INCHES); // 12 inches
//        Length length3 = new Length(0.3048, LengthUnit.METER); // If add METER later

        // Currently only testing with available units
        assertTrue(length1.equals(length2));
        // When METER is added: assertTrue(length1.equals(length3));
    }
}
