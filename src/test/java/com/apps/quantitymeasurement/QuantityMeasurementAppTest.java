package com.apps.quantitymeasurement;

import org.junit.Test;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    // -------- FEET TESTS --------

    @Test
    public void testFeetEquality_SameValue() {
        assertTrue(new Feet(1.0).equals(new Feet(1.0)));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        assertFalse(new Feet(1.0).equals(new Feet(2.0)));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        assertFalse(new Feet(1.0).equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        assertFalse(new Feet(1.0).equals("1.0"));
    }

    @Test
    public void testFeetEquality_SameReference() {
        Feet f = new Feet(1.0);
        assertTrue(f.equals(f));
    }

    // -------- INCHES TESTS --------

    @Test
    public void testInchesEquality_SameValue() {
        assertTrue(new Inches(12.0).equals(new Inches(12.0)));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        assertFalse(new Inches(12.0).equals(new Inches(13.0)));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        assertFalse(new Inches(12.0).equals(null));
    }

    @Test
    public void testInchesEquality_DifferentClass() {
        assertFalse(new Inches(12.0).equals(12));
    }

    @Test
    public void testInchesEquality_SameReference() {
        Inches i = new Inches(12.0);
        assertTrue(i.equals(i));
    }
}
