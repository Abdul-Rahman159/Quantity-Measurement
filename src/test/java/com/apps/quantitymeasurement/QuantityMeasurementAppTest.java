package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ============ UC11: VOLUMEUNIT ENUM TESTS ============

    @Test
    public void testVolumeUnitEnum_LitreConstant() {
        assertEquals(1.0, VolumeUnit.LITRE.getConversionFactor(), 0.0001);
        assertEquals("LITRE", VolumeUnit.LITRE.getUnitName());
    }

    @Test
    public void testVolumeUnitEnum_MillilitreConstant() {
        assertEquals(0.001, VolumeUnit.MILLILITRE.getConversionFactor(), 0.0001);
        assertEquals("MILLILITRE", VolumeUnit.MILLILITRE.getUnitName());
    }

    @Test
    public void testVolumeUnitEnum_GallonConstant() {
        assertEquals(3.78541, VolumeUnit.GALLON.getConversionFactor(), 0.0001);
        assertEquals("GALLON", VolumeUnit.GALLON.getUnitName());
    }

    @Test
    public void testConvertToBaseUnit_LitreToLitre() {
        assertEquals(5.0, VolumeUnit.LITRE.convertToBaseUnit(5.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_MillilitreToLitre() {
        assertEquals(1.0, VolumeUnit.MILLILITRE.convertToBaseUnit(1000.0), 0.0001);
    }

    @Test
    public void testConvertToBaseUnit_GallonToLitre() {
        assertEquals(3.78541, VolumeUnit.GALLON.convertToBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_LitreToLitre() {
        assertEquals(2.0, VolumeUnit.LITRE.convertFromBaseUnit(2.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_LitreToMillilitre() {
        assertEquals(1000.0, VolumeUnit.MILLILITRE.convertFromBaseUnit(1.0), 0.0001);
    }

    @Test
    public void testConvertFromBaseUnit_LitreToGallon() {
        assertEquals(0.264172, VolumeUnit.GALLON.convertFromBaseUnit(1.0), 0.001);
    }

    // ============ UC11: VOLUME EQUALITY TESTS ============

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    public void testEquality_LitreToLitre_DifferentValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        assertFalse(v1.equals(v2));
    }

    @Test
    public void testEquality_MillilitreToMillilitre_SameValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        assertTrue(v1.equals(v2));
    }

    @Test
    public void testEquality_GallonToGallon_SameValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertTrue(v1.equals(v2));
    }

    @Test
    public void testEquality_LitreToMillilitre_EquivalentValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(litre.equals(milli));
    }

    @Test
    public void testEquality_MillilitreToLitre_EquivalentValue() {
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(milli.equals(litre));
    }

    @Test
    public void testEquality_LitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(0.264172, VolumeUnit.GALLON);
        assertTrue(litre.equals(gallon));
    }

    @Test
    public void testEquality_GallonToLitre_EquivalentValue() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertTrue(gallon.equals(litre));
    }

    @Test
    public void testEquality_MillilitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(0.264172, VolumeUnit.GALLON);
        assertTrue(milli.equals(gallon));
    }

    @Test
    public void testEquality_ZeroValue() {
        Quantity<VolumeUnit> litreZero = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milliZero = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallonZero = new Quantity<>(0.0, VolumeUnit.GALLON);

        assertTrue(litreZero.equals(milliZero));
        assertTrue(litreZero.equals(gallonZero));
    }

    @Test
    public void testEquality_NegativeVolume() {
        Quantity<VolumeUnit> litreNeg = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milliNeg = new Quantity<>(-1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallonNeg = new Quantity<>(-0.264172, VolumeUnit.GALLON);

        assertTrue(litreNeg.equals(milliNeg));
        assertTrue(litreNeg.equals(gallonNeg));
    }

    @Test
    public void testEquality_LargeVolumeValue() {
        Quantity<VolumeUnit> litreLarge = new Quantity<>(1000.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milliLarge = new Quantity<>(1000000.0, VolumeUnit.MILLILITRE);
        assertTrue(litreLarge.equals(milliLarge));
    }

    @Test
    public void testEquality_SmallVolumeValue() {
        Quantity<VolumeUnit> litreSmall = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milliSmall = new Quantity<>(1.0, VolumeUnit.MILLILITRE);
        assertTrue(litreSmall.equals(milliSmall));
    }

    @Test
    public void testEquality_TransitiveProperty() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c = new Quantity<>(0.264172, VolumeUnit.GALLON);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testEquality_SameReference() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(volume.equals(volume));
    }

    @Test
    public void testEquality_NullComparison() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertFalse(volume.equals(null));
    }

    // ============ UC11: VOLUME VS LENGTH/WEIGHT INCOMPATIBILITY ============

    @Test
    public void testEquality_VolumeVsLength_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(volume.equals(length));
    }

    @Test
    public void testEquality_VolumeVsWeight_Incompatible() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(volume.equals(weight));
    }

    // ============ UC11: VOLUME CONVERSION TESTS ============

    @Test
    public void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = milli.convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, result.getValue(), 0.001);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, result.getValue(), 0.001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = milli.convertTo(VolumeUnit.GALLON);
        assertEquals(0.264172, result.getValue(), 0.001);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testConversion_GallonToMillilitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = gallon.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(3785.41, result.getValue(), 0.01);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testConversion_SameUnit() {
        Quantity<VolumeUnit> litre = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.LITRE);
        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testConversion_ZeroValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(0.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testConversion_NegativeValue() {
        Quantity<VolumeUnit> litre = new Quantity<>(-1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(-1000.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testConversion_RoundTrip() {
        Quantity<VolumeUnit> original = new Quantity<>(1.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> toMilli = original.convertTo(VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> backToLitre = toMilli.convertTo(VolumeUnit.LITRE);

        assertEquals(original.getValue(), backToLitre.getValue(), 0.0001);
        assertEquals(original.getUnit(), backToLitre.getUnit());
    }

    // ============ UC11: VOLUME ADDITION TESTS ============

    @Test
    public void testAddition_SameUnit_LitrePlusLitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_SameUnit_MillilitrePlusMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testAddition_SameUnit_GallonPlusGallon() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(milli);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = milli.add(litre);

        assertEquals(2000.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_LitrePlusGallon() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = litre.add(gallon);

        assertEquals(4.78541, result.getValue(), 0.001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_GallonPlusLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = gallon.add(litre);

        assertEquals(2.0, result.getValue(), 0.001);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testAddition_CrossUnit_MillilitrePlusGallon_DefaultToFirstUnit() {
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = milli.add(gallon);

        // 1000 mL = 1 L; 1 gallon = 3.78541 L; total = 4.78541 L = 4785.41 mL
        assertEquals(4785.41, result.getValue(), 0.01);  // 4-decimal rounding inside Quantity
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    // ============ UC11: ADDITION WITH EXPLICIT TARGET UNIT ============

    @Test
    public void testAddition_ExplicitTargetUnit_Litre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(milli, VolumeUnit.LITRE);

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Millilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(milli, VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gallon() {
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> litre2 = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = litre.add(litre2, VolumeUnit.GALLON);

        assertEquals(2.0, result.getValue(), 0.001);
        assertEquals(VolumeUnit.GALLON, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_MixedUnits() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = litre.add(gallon, VolumeUnit.MILLILITRE);

        assertEquals(4785.41, result.getValue(), 0.01);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result1 = litre.add(milli);
        Quantity<VolumeUnit> result2 = milli.add(litre);

        Quantity<VolumeUnit> result1InMilli = result1.convertTo(VolumeUnit.MILLILITRE);
        assertTrue(result2.equals(result1InMilli));
    }

    @Test
    public void testAddition_WithZero() {
        Quantity<VolumeUnit> litre = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> zero = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(zero);

        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_NegativeValues() {
        Quantity<VolumeUnit> pos = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> neg = new Quantity<>(-2000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = pos.add(neg);

        assertEquals(3.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_LargeValues() {
        Quantity<VolumeUnit> large1 = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> large2 = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = large1.add(large2);

        assertEquals(2e6, result.getValue(), 0.1);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_SmallValues() {
        Quantity<VolumeUnit> small1 = new Quantity<>(0.001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> small2 = new Quantity<>(0.002, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = small1.add(small2);

        assertEquals(0.003, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_NullOperand() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertThrows(IllegalArgumentException.class, () -> {
            litre.add(null);
        });
    }

    @Test
    public void testAddition_NullTargetUnit() {
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertThrows(IllegalArgumentException.class, () -> {
            litre.add(milli, null);
        });
    }

    // ============ UC11: PRECISION AND TOLERANCE TESTS ============

    @Test
    public void testPrecision_LitreToGallonRoundTrip() {
        Quantity<VolumeUnit> original = new Quantity<>(1.2345, VolumeUnit.LITRE);
        Quantity<VolumeUnit> toGallon = original.convertTo(VolumeUnit.GALLON);
        Quantity<VolumeUnit> backToLitre = toGallon.convertTo(VolumeUnit.LITRE);

        assertEquals(original.getValue(), backToLitre.getValue(), 0.001);
    }

    // ============ UC11: DEMONSTRATION METHOD TESTS ============

    @Test
    public void testAppDemonstrateEquality_Volume() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(v1, v2));
    }

    @Test
    public void testAppDemonstrateConversion_Volume() {
        Quantity<VolumeUnit> result = QuantityMeasurementApp.demonstrateConversion(1.0, VolumeUnit.LITRE, VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testAppDemonstrateAddition_Volume() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = QuantityMeasurementApp.demonstrateAddition(v1, v2);
        assertEquals(2.0, sum.getValue(), 0.0001);
        assertEquals(VolumeUnit.LITRE, sum.getUnit());
    }

    @Test
    public void testAppDemonstrateAddition_VolumeWithTargetUnit() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = QuantityMeasurementApp.demonstrateAddition(v1, v2, VolumeUnit.MILLILITRE);
        assertEquals(2000.0, sum.getValue(), 0.0001);
        assertEquals(VolumeUnit.MILLILITRE, sum.getUnit());
    }

    // ============ UC11: BACKWARD COMPATIBILITY TESTS ============

    @Test
    public void testBackwardCompatibility_LengthOperationsStillWork() {
        // UC1-UC8 length tests should still pass
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(feet.equals(inches));

        Quantity<LengthUnit> converted = feet.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, converted.getValue(), 0.0001);

        Quantity<LengthUnit> sum = feet.add(inches);
        assertEquals(2.0, sum.getValue(), 0.0001);
    }

    @Test
    public void testBackwardCompatibility_WeightOperationsStillWork() {
        // UC9 weight tests should still pass
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(kg.equals(g));

        Quantity<WeightUnit> converted = kg.convertTo(WeightUnit.POUND);
        assertEquals(2.20462, converted.getValue(), 0.001);

        Quantity<WeightUnit> sum = kg.add(g);
        assertEquals(2.0, sum.getValue(), 0.0001);
    }

    // ============ UC11: GENERIC QUANTITY CONSISTENCY TESTS ============

    @Test
    public void testGenericQuantity_VolumeOperations_Consistency() {
        // Verify that generic Quantity works identically for volume as for length/weight
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> milli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        // Equality
        assertTrue(litre.equals(milli));

        // Conversion
        Quantity<VolumeUnit> converted = litre.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, converted.getValue(), 0.0001);

        // Addition
        Quantity<VolumeUnit> sum = litre.add(milli);
        assertEquals(2.0, sum.getValue(), 0.0001);

        // Addition with target unit
        Quantity<VolumeUnit> sumInMilli = litre.add(milli, VolumeUnit.MILLILITRE);
        assertEquals(2000.0, sumInMilli.getValue(), 0.0001);
    }
}