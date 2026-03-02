package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ============ UC12: SUBTRACTION TESTS ============

    @Test
    public void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_SameUnit_KilogramMinusKilogram() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(3.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = q1.subtract(q2);
        assertEquals(7.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testSubtraction_SameUnit_LitreMinusLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q1.subtract(q2);
        assertEquals(7.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(9.5, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_CrossUnit_InchesMinusFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(60.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testSubtraction_CrossUnit_KilogramMinusGram() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(2000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = q1.subtract(q2);
        assertEquals(8.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testSubtraction_CrossUnit_LitreMinusMillilitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = q1.subtract(q2);
        assertEquals(4.5, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_FeetToInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);
        assertEquals(114.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_KilogramToGram() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = q1.subtract(q2, WeightUnit.GRAM);
        assertEquals(8000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit_LitreToMillilitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q1.subtract(q2, VolumeUnit.MILLILITRE);
        assertEquals(3000.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(-5.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(0.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_WithNegativeValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(-2.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(7.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result1 = q1.subtract(q2);
        Quantity<LengthUnit> result2 = q2.subtract(q1);
        assertEquals(5.0, result1.getValue(), 0.0001);
        assertEquals(-5.0, result2.getValue(), 0.0001);
    }

    @Test
    public void testSubtraction_LargeValues() {
        Quantity<WeightUnit> q1 = new Quantity<>(1_000_000.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(500_000.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = q1.subtract(q2);
        assertEquals(500_000.0, result.getValue(), 0.1);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testSubtraction_SmallValues() {
        Quantity<LengthUnit> q1 = new Quantity<>(0.001, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0005, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2);
        assertEquals(0.0005, result.getValue(), 0.00001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testSubtraction_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
    }

    @Test
    public void testSubtraction_NullTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.subtract(q2, null));
    }

    @Test
    public void testSubtraction_ChainedOperations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q1.subtract(q2).subtract(q3);
        assertEquals(7.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

// ============ UC12: DIVISION TESTS ============

    @Test
    public void testDivision_SameUnit_FeetDividedByFeet() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void testDivision_SameUnit_KilogramDividedByKilogram() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        double result = q1.divide(q2);
        assertEquals(2.0, result, 0.0001);
    }

    @Test
    public void testDivision_SameUnit_LitreDividedByLitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(4.0, VolumeUnit.LITRE);
        double result = q1.divide(q2);
        assertEquals(2.5, result, 0.0001);
    }

    @Test
    public void testDivision_CrossUnit_FeetDividedByInches() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertEquals(1.0, result, 0.0001); // 24 inches / 24 inches = 1
    }

    @Test
    public void testDivision_CrossUnit_KilogramDividedByGram() {
        Quantity<WeightUnit> q1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(2000.0, WeightUnit.GRAM);
        double result = q1.divide(q2);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testDivision_CrossUnit_LitreDividedByMillilitre() {
        Quantity<VolumeUnit> q1 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        double result = q1.divide(q2);
        assertEquals(4.0, result, 0.0001); // 2L / 0.5L = 4
    }

    @Test
    public void testDivision_RatioGreaterThanOne() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void testDivision_RatioLessThanOne() {
        Quantity<LengthUnit> q1 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertEquals(0.2, result, 0.0001);
    }

    @Test
    public void testDivision_RatioEqualToOne() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(10.0, LengthUnit.FEET);
        double result = q1.divide(q2);
        assertEquals(1.0, result, 0.0001);
    }

    @Test
    public void testDivision_NonCommutative() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        double result1 = q1.divide(q2);
        double result2 = q2.divide(q1);
        assertEquals(2.0, result1, 0.0001);
        assertEquals(0.5, result2, 0.0001);
    }

    @Test
    public void testDivision_ByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    public void testDivision_ByZeroWithEpsilon() {
        // Very small value close to zero
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1e-13, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    public void testDivision_LargeRatio() {
        Quantity<WeightUnit> q1 = new Quantity<>(1_000_000.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        double result = q1.divide(q2);
        assertEquals(1_000_000.0, result, 0.1);
    }

    @Test
    public void testDivision_SmallRatio() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1_000_000.0, WeightUnit.KILOGRAM);
        double result = q1.divide(q2);
        assertEquals(0.000001, result, 1e-9);
    }

    @Test
    public void testDivision_NullOperand() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.divide(null));
    }

    @Test
    public void testDivision_CrossCategory() {
        Quantity<LengthUnit> len = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> wt = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> len.divide(wt));
    }

    @Test
    public void testDivision_Associativity() {
        Quantity<LengthUnit> a = new Quantity<>(100.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> c = new Quantity<>(2.0, LengthUnit.FEET);

        double left = a.divide(b) / c.divide(new Quantity<>(1.0, LengthUnit.FEET));
        double step1 = a.divide(b); // 10.0
        double step2 = step1 / c.getValue(); // 10/2 = 5.0
        double expected = a.getValue() / (b.getValue() * c.getValue()); // 100 / (10*2) = 100/20 = 5.0
        assertEquals(expected, step2, 0.0001);

        double alternative = a.getValue() / (b.getValue() / c.getValue());
        assertEquals(20.0, alternative, 0.0001);
    }

// ============ UC12: INTEGRATION TESTS ============

    @Test
    public void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
        Quantity<LengthUnit> sum = a.add(b);
        Quantity<LengthUnit> back = sum.subtract(b);
        assertTrue(a.equals(back));
    }

    @Test
    public void testSubtraction_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.subtract(b);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertEquals(10.0, a.getValue(), 0.0001);
        assertEquals(4.0, b.getValue(), 0.0001);
    }

    @Test
    public void testDivision_Immutability() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        double result = a.divide(b);
        assertEquals(10.0, a.getValue(), 0.0001);
        assertEquals(2.0, b.getValue(), 0.0001);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void testSubtraction_PrecisionAndRounding() {
        Quantity<LengthUnit> a = new Quantity<>(1.23456, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.12345, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.subtract(b);
        // Expect rounding to 4 decimal places: 1.23456 - 0.12345 = 1.11111 -> rounded 1.1111
        assertEquals(1.1111, result.getValue(), 0.0001);
    }

    @Test
    public void testDivision_PrecisionHandling() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);
        double result = a.divide(b);
        // No rounding applied, should be ~0.3333333333333333
        assertEquals(1.0/3.0, result, 1e-12);
    }

// ============ UC12: DEMONSTRATION METHOD TESTS ============

    @Test
    public void testAppDemonstrateSubtraction_Length() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateSubtraction(q1, q2);
        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAppDemonstrateSubtractionWithTarget_Length() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateSubtraction(q1, q2, LengthUnit.INCHES);
        assertEquals(114.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAppDemonstrateDivision_Length() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        double result = QuantityMeasurementApp.demonstrateDivision(q1, q2);
        assertEquals(5.0, result, 0.0001);
    }

// ============ UC12: EDGE CASE TESTS ============


    @Test
    public void testSubtraction_InvalidValueNaN() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }



    @Test
    public void testDivision_InvalidValueNaN() {
        // Constructor should reject NaN values up-front
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }


    // Additional cross-category checks with different unit enums

    @Test
    public void testSubtraction_DifferentUnitEnumsSameCategory() {
        // Minimal alt "length" enum with a different type
        enum AltLen implements IMeasurable {
            FT(1.0), IN(1.0 / 12.0);
            private final double f; AltLen(double f){ this.f = f; }
            public double getConversionFactor(){ return f; }
            public double convertToBaseUnit(double v){ return v * f; }
            public double convertFromBaseUnit(double b){ return b / f; }
            public String getUnitName(){ return name(); }
        }

        // Use raw types to bypass generics and hit runtime category check
        @SuppressWarnings({"rawtypes","unchecked"}) Quantity a = new Quantity(10.0, LengthUnit.FEET);
        @SuppressWarnings({"rawtypes","unchecked"}) Quantity b = new Quantity(12.0, AltLen.IN);

        assertThrows(IllegalArgumentException.class, () -> a.subtract(b));
    }

    // Ensure all categories are covered
    @Test
    public void testSubtraction_AllCategories() {
        // Length already done, do Weight and Volume
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(3.0, WeightUnit.KILOGRAM);
        assertEquals(7.0, w1.subtract(w2).getValue(), 0.0001);

        Quantity<VolumeUnit> v1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        assertEquals(7.0, v1.subtract(v2).getValue(), 0.0001);
    }

    @Test
    public void testDivision_AllCategories() {
        Quantity<WeightUnit> w1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertEquals(2.0, w1.divide(w2), 0.0001);

        Quantity<VolumeUnit> v1 = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(4.0, VolumeUnit.LITRE);
        assertEquals(2.5, v1.divide(v2), 0.0001);
    }
}