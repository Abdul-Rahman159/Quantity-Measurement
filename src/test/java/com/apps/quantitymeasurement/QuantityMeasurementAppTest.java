package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ============ IMeasurable Interface Tests ============

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {
        assertTrue(LengthUnit.FEET instanceof IMeasurable);
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), 0.0001);
        assertEquals(1.0, LengthUnit.FEET.convertFromBaseUnit(1.0), 0.0001);
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), 0.0001);
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), 0.0001);
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), 0.0001);
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), 0.0001);
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {
        assertTrue(WeightUnit.KILOGRAM instanceof IMeasurable);
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), 0.0001);
        assertEquals(1.0, WeightUnit.KILOGRAM.convertFromBaseUnit(1.0), 0.0001);
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), 0.0001);
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), 0.0001);
        assertEquals(1.0, WeightUnit.POUND.convertToBaseUnit(2.20462), 0.001);
    }

    // ============ Generic Quantity Constructor Validation ============

    @Test
    public void testConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    public void testConstructor_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    // ============ Length Operations (UC1-UC8) with Generic Quantity ============

    @Test
    public void testLengthEquality_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testLengthEquality_CrossUnit() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));
    }

    @Test
    public void testLengthEquality_YardToFeet() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);
        assertTrue(yard.equals(feet));
    }

    @Test
    public void testLengthEquality_CmToInches() {
        Quantity<LengthUnit> cm = new Quantity<>(2.54, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> inches = new Quantity<>(1.0, LengthUnit.INCHES);
        assertTrue(cm.equals(inches));
    }

    @Test
    public void testLengthInequality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        assertFalse(q1.equals(q2));
    }

    @Test
    public void testLengthConversion_FeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testLengthConversion_YardsToCm() {
        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> result = yard.convertTo(LengthUnit.CENTIMETERS);
        assertEquals(91.44, result.getValue(), 0.01); // 1 yard = 91.44 cm
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    public void testLengthAddition_SameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> sum = q1.add(q2);
        assertEquals(3.0, sum.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    public void testLengthAddition_CrossUnit() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = feet.add(inches);
        assertEquals(2.0, sum.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    public void testLengthAddition_WithTargetUnit() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = feet.add(inches, LengthUnit.YARDS);
        assertEquals(0.6667, sum.getValue(), 0.0001);
        assertEquals(LengthUnit.YARDS, sum.getUnit());
    }

    // ============ Weight Operations (UC9) with Generic Quantity ============

    @Test
    public void testWeightEquality_SameUnit() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void testWeightEquality_KgToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test
    public void testWeightEquality_KgToPound() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> lb = new Quantity<>(2.20462, WeightUnit.POUND);
        assertTrue(kg.equals(lb)); // uses epsilon 0.001
    }

    @Test
    public void testWeightEquality_NegativeWeight() {
        Quantity<WeightUnit> kgNeg = new Quantity<>(-1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gNeg = new Quantity<>(-1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> lbNeg = new Quantity<>(-2.20462, WeightUnit.POUND);
        assertTrue(kgNeg.equals(gNeg));
        assertTrue(kgNeg.equals(lbNeg));
    }

    @Test
    public void testWeightEquality_TransitiveProperty() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> c = new Quantity<>(2.20462, WeightUnit.POUND);
        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testWeightConversion_KgToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testWeightConversion_KgToPound() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg.convertTo(WeightUnit.POUND);
        assertEquals(2.20462, result.getValue(), 0.001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testWeightConversion_PoundToGram() {
        Quantity<WeightUnit> lb = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> result = lb.convertTo(WeightUnit.GRAM);
        assertEquals(453.592, result.getValue(), 0.001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testWeightAddition_SameUnit() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> sum = w1.add(w2);
        assertEquals(3.0, sum.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    public void testWeightAddition_CrossUnit() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> sum = kg.add(g);
        assertEquals(2.0, sum.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }

    @Test
    public void testWeightAddition_WithTargetUnit() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> sum = kg.add(g, WeightUnit.GRAM);
        assertEquals(2000.0, sum.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, sum.getUnit());
    }

    // ============ Cross-Category Prevention ============

    @Test
    public void testCrossCategoryComparison_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(length.equals(weight)); // should return false, not exception
    }

    // The compiler prevents assigning one type to another, but we test runtime behavior.

    // ============ Edge Cases and Additional Generic Tests ============

    @Test
    public void testEquality_Null() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(q.equals(null));
    }

    @Test
    public void testEquality_DifferentClass() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        String s = "test";
        assertFalse(q.equals(s));
    }

    @Test
    public void testSameReference() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    public void testHashCodeConsistency() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1.hashCode(), q2.hashCode()); // because they are equal
    }

    @Test
    public void testZeroValue() {
        Quantity<WeightUnit> zeroKg = new Quantity<>(0.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> zeroG = new Quantity<>(0.0, WeightUnit.GRAM);
        assertTrue(zeroKg.equals(zeroG));
    }

    @Test
    public void testLargeValues() {
        Quantity<LengthUnit> large1 = new Quantity<>(1e6, LengthUnit.FEET);
        Quantity<LengthUnit> large2 = new Quantity<>(1e6, LengthUnit.FEET);
        Quantity<LengthUnit> sum = large1.add(large2);
        assertEquals(2e6, sum.getValue(), 0.1);
    }

    @Test
    public void testSmallValues() {
        Quantity<LengthUnit> small1 = new Quantity<>(0.001, LengthUnit.FEET);
        Quantity<LengthUnit> small2 = new Quantity<>(0.001, LengthUnit.FEET);
        Quantity<LengthUnit> sum = small1.add(small2);
        assertEquals(0.002, sum.getValue(), 0.0001);
    }

    // ============ QuantityMeasurementApp Generic Method Tests ============

    @Test
    public void testAppDemonstrateEquality_Length() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(q1, q2));
    }

    @Test
    public void testAppDemonstrateEquality_Weight() {
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(w1, w2));
    }

    @Test
    public void testAppDemonstrateConversion() {
        Quantity<LengthUnit> result = QuantityMeasurementApp.demonstrateConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAppDemonstrateAddition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = QuantityMeasurementApp.demonstrateAddition(q1, q2);
        assertEquals(2.0, sum.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    private enum VolumeUnit implements IMeasurable {
        LITER(1.0),
        GALLON(3.78541);

        private final double factor;
        VolumeUnit(double factor) { this.factor = factor; }
        @Override public double getConversionFactor() { return factor; }
        @Override public double convertToBaseUnit(double value) { return value * factor; }
        @Override public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }
        @Override public String getUnitName() { return name(); }
    }

    @Test
    public void testScalability_NewUnitEnum() {
        Quantity<VolumeUnit> liter = new Quantity<>(1.0, VolumeUnit.LITER);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        // Conversion
        Quantity<VolumeUnit> converted = liter.convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172, converted.getValue(), 0.001); // 1 liter ≈ 0.264172 gallons
        // Equality
        assertFalse(liter.equals(gallon));
        // Addition
        Quantity<VolumeUnit> sum = liter.add(gallon, VolumeUnit.LITER);
        assertEquals(4.78541, sum.getValue(), 0.001); // 1 + 3.78541
    }
}