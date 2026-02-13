package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testWeightUnitEnum_KilogramConstant() {
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactorToBase(), 0.0001);
    }

    @Test
    public void testWeightUnitEnum_GramConstant() {
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactorToBase(), 0.0001);
    }

    @Test
    public void testWeightUnitEnum_PoundConstant() {
        assertEquals(0.453592, WeightUnit.POUND.getConversionFactorToBase(), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_KilogramToKilogram() {
        assertEquals(5.0, WeightUnit.KILOGRAM.convertToBaseUnit(5.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_GramToKilogram() {
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_PoundToKilogram() {
        assertEquals(0.453592, WeightUnit.POUND.convertToBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_KilogramToKilogram() {
        assertEquals(2.0, WeightUnit.KILOGRAM.convertFromBaseUnit(2.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_KilogramToGram() {
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_KilogramToPound() {
        assertEquals(2.20462, WeightUnit.POUND.convertFromBaseUnit(1.0), 0.001);
    }

    // ============ UC9: WEIGHT EQUALITY TESTS ============

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);
        assertFalse(w1.equals(w2));
    }

    @Test
    public void testEquality_GramToGram_SameValue() {
        Weight w1 = new Weight(500.0, WeightUnit.GRAM);
        Weight w2 = new Weight(500.0, WeightUnit.GRAM);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void testEquality_PoundToPound_SameValue() {
        Weight w1 = new Weight(2.0, WeightUnit.POUND);
        Weight w2 = new Weight(2.0, WeightUnit.POUND);
        assertTrue(w1.equals(w2));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(g.equals(kg));
    }

    @Test
    public void testEquality_KilogramToPound_EquivalentValue() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight lb = new Weight(2.20462, WeightUnit.POUND);
        assertTrue(kg.equals(lb));
    }

    @Test
    public void testEquality_NegativeWeight() {
        Weight kgNeg = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight gNeg = new Weight(-1000.0, WeightUnit.GRAM);
        Weight lbNeg = new Weight(-2.20462, WeightUnit.POUND);

        assertTrue(kgNeg.equals(gNeg));
        assertTrue(kgNeg.equals(lbNeg));
    }

    @Test
    public void testEquality_TransitiveProperty() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);
        Weight c = new Weight(2.20462, WeightUnit.POUND);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testEquality_PoundToGram_EquivalentValue() {
        Weight lb = new Weight(1.0, WeightUnit.POUND);
        Weight g = new Weight(453.592, WeightUnit.GRAM);
        assertTrue(lb.equals(g));
    }

    @Test
    public void testEquality_ZeroValue() {
        Weight kgZero = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight gZero = new Weight(0.0, WeightUnit.GRAM);
        Weight lbZero = new Weight(0.0, WeightUnit.POUND);

        assertTrue(kgZero.equals(gZero));
        assertTrue(kgZero.equals(lbZero));
        assertTrue(gZero.equals(lbZero));
    }

    @Test
    public void testEquality_LargeWeightValue() {
        Weight kgLarge = new Weight(1000.0, WeightUnit.KILOGRAM);
        Weight gLarge = new Weight(1000000.0, WeightUnit.GRAM);
        assertTrue(kgLarge.equals(gLarge));
    }

    @Test
    public void testEquality_SmallWeightValue() {
        Weight kgSmall = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight gSmall = new Weight(1.0, WeightUnit.GRAM);
        assertTrue(kgSmall.equals(gSmall));
    }

    @Test
    public void testEquality_SameReference() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        assertTrue(weight.equals(weight));
    }

    @Test
    public void testEquality_NullComparison() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        assertFalse(weight.equals(null));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(1.0, null);
        });
    }

    @Test
    public void testEquality_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(Double.NaN, WeightUnit.KILOGRAM);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Weight(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM);
        });
    }

    // UC9: WEIGHT VS LENGTH INCOMPATIBILITY ============
    @Test
    public void testEquality_WeightVsLength_Incompatible() {
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
        assertFalse(length.equals(weight));
    }

    // WEIGHT CONVERSION TESTS

    @Test
    public void testConversion_KilogramToGram() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testConversion_KilogramToPound() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight result = kg.convertTo(WeightUnit.POUND);
        assertEquals(2.20462, result.getValue(), 0.001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testConversion_GramToKilogram() {
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        Weight result = g.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testConversion_GramToPound() {
        Weight g = new Weight(453.592, WeightUnit.GRAM);
        Weight result = g.convertTo(WeightUnit.POUND);
        assertEquals(1.0, result.getValue(), 0.001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testConversion_PoundToKilogram() {
        Weight lb = new Weight(2.20462, WeightUnit.POUND);
        Weight result = lb.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, result.getValue(), 0.001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testConversion_PoundToGram() {
        Weight lb = new Weight(1.0, WeightUnit.POUND);
        Weight result = lb.convertTo(WeightUnit.GRAM);
        assertEquals(453.592, result.getValue(), 0.001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testConversion_SameUnit() {
        Weight kg = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight result = kg.convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testConversion_ZeroValue() {
        Weight kg = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(0.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testConversion_NegativeValue() {
        Weight kg = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight result = kg.convertTo(WeightUnit.GRAM);
        assertEquals(-1000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testConversion_RoundTrip() {
        Weight original = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight toGrams = original.convertTo(WeightUnit.GRAM);
        Weight backToKg = toGrams.convertTo(WeightUnit.KILOGRAM);

        assertEquals(original.getValue(), backToKg.getValue(), 0.0001);
        assertEquals(original.getUnit(), backToKg.getUnit());
    }

    @Test
    public void testConversion_NullTargetUnit() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> {
            kg.convertTo(null);
        });
    }

    @Test
    public void testStaticConvertMethod() {
        double result = Weight.convert(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);
        assertEquals(1000.0, result, 0.0001);
    }

    // WEIGHT ADDITION TESTS
    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);
        Weight result = w1.add(w2);

        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_SameUnit_GramPlusGram() {
        Weight w1 = new Weight(500.0, WeightUnit.GRAM);
        Weight w2 = new Weight(500.0, WeightUnit.GRAM);
        Weight result = w1.add(w2);

        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        Weight result = kg.add(g);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_GramPlusKilogram() {
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight result = g.add(kg);

        assertEquals(2000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusPound() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight lb = new Weight(2.20462, WeightUnit.POUND);
        Weight result = kg.add(lb);

        assertEquals(2.0, result.getValue(), 0.001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusGram() {
        Weight lb = new Weight(1.0, WeightUnit.POUND);
        Weight g = new Weight(453.592, WeightUnit.GRAM);
        Weight result = lb.add(g);

        assertEquals(2.0, result.getValue(), 0.001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        Weight result = kg.add(g, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Pound() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight lb = new Weight(2.20462, WeightUnit.POUND);
        Weight result = kg.add(lb, WeightUnit.POUND);

        assertEquals(4.40924, result.getValue(), 0.001);
        assertEquals(WeightUnit.POUND, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
        Weight lb1 = new Weight(2.20462, WeightUnit.POUND);
        Weight lb2 = new Weight(2.20462, WeightUnit.POUND);
        Weight result = lb1.add(lb2, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(1000.0, WeightUnit.GRAM);

        Weight result1 = kg.add(g);
        Weight result2 = g.add(kg);

        Weight result1InGrams = result1.convertTo(WeightUnit.GRAM);
        assertTrue(result2.equals(result1InGrams));
    }

    @Test
    public void testAddition_WithZero() {
        Weight kg = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight zero = new Weight(0.0, WeightUnit.GRAM);
        Weight result = kg.add(zero);

        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_NegativeValues() {
        Weight pos = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight neg = new Weight(-2000.0, WeightUnit.GRAM);
        Weight result = pos.add(neg);

        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_NullOperand() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> {
            kg.add(null);
        });
    }

    @Test
    public void testAddition_NullTargetUnit() {
        Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight g = new Weight(1000.0, WeightUnit.GRAM);
        assertThrows(IllegalArgumentException.class, () -> {
            kg.add(g, null);
        });
    }

    @Test
    public void testAddition_LargeValues() {
        Weight large1 = new Weight(1e6, WeightUnit.KILOGRAM);
        Weight large2 = new Weight(1e6, WeightUnit.KILOGRAM);
        Weight result = large1.add(large2);

        assertEquals(2e6, result.getValue(), 0.1);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddition_SmallValues() {
        Weight small1 = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight small2 = new Weight(1.0, WeightUnit.GRAM);
        Weight result = small1.add(small2);

        assertEquals(0.002, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    //TO_STRING METHOD TEST

    @Test
    public void testToString() {
        Weight weight = new Weight(1.2345, WeightUnit.KILOGRAM);
        String str = weight.toString();
        assertTrue(str.contains("1.2345"));
        assertTrue(str.contains("kilogram"));
    }

    // BACKWARD COMPATIBILITY TESTS
    @Test
    public void testLengthFunctionalityStillWorks() {
        Length feet = new Length(1.0, LengthUnit.FEET);
        Length inches = new Length(12.0, LengthUnit.INCHES);

        assertTrue(feet.equals(inches));
        assertEquals(2.0, feet.add(inches).getValue(), 0.0001);
        assertEquals(24.0, feet.add(inches, LengthUnit.INCHES).getValue(), 0.0001);
    }
}
