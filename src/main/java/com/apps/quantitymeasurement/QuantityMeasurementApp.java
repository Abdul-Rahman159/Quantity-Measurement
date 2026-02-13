package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // LENGTH METHODS (UC1-UC8)
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        boolean result = length1.equals(length2);
        System.out.println(length1 + " equals " + length2 + ": " + result);
        return result;
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1,
                                                      double value2, LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        return demonstrateLengthEquality(length1, length2);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit,
                                                     LengthUnit toUnit) {
        System.out.print("Converting " + value + " " + fromUnit + " to " + toUnit + ": ");
        Length converted = new Length(value, fromUnit).convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit);
        return converted;
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        System.out.print("Converting " + length + " to " + toUnit + ": ");
        Length converted = length.convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit);
        return converted;
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        System.out.print("Adding " + length1 + " + " + length2 + ": ");
        Length result = length1.add(length2);
        System.out.println("= " + result);
        return result;
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {
        System.out.print("Adding " + length1 + " + " + length2 + " in " + targetUnit + ": ");
        Length result = length1.add(length2, targetUnit);
        System.out.println("= " + result);
        return result;
    }

    // WEIGHT METHODS (NEW)
    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2) {
        boolean result = weight1.equals(weight2);
        System.out.println(weight1 + " equals " + weight2 + ": " + result);
        return result;
    }

    public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1,
                                                      double value2, WeightUnit unit2) {
        Weight weight1 = new Weight(value1, unit1);
        Weight weight2 = new Weight(value2, unit2);
        return demonstrateWeightEquality(weight1, weight2);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit fromUnit,
                                                     WeightUnit toUnit) {
        System.out.print("Converting " + value + " " + fromUnit + " to " + toUnit + ": ");
        Weight converted = new Weight(value, fromUnit).convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit);
        return converted;
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit) {
        System.out.print("Converting " + weight + " to " + toUnit + ": ");
        Weight converted = weight.convertTo(toUnit);
        System.out.println(converted.getValue() + " " + toUnit);
        return converted;
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2) {
        System.out.print("Adding " + weight1 + " + " + weight2 + ": ");
        Weight result = weight1.add(weight2);
        System.out.println("= " + result);
        return result;
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit targetUnit) {
        System.out.print("Adding " + weight1 + " + " + weight2 + " in " + targetUnit + ": ");
        Weight result = weight1.add(weight2, targetUnit);
        System.out.println("= " + result);
        return result;
    }

    public static void demonstrateCategoryIncompatibility() {
        System.out.println("\n=== Category Incompatibility ===");
        Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        System.out.println("Weight: " + weight);
        System.out.println("Length: " + length);
        System.out.println("Weight equals Length? " + weight.equals(length));
        System.out.println("Length equals Weight? " + length.equals(weight));
        System.out.println("Result: false (different categories cannot be compared)");
    }

    // Main method to demonstrate both categories
    public static void main(String[] args) {
        System.out.println("=== UC9: Weight Measurement Support ===\n");

        // 1. Demonstrate WeightUnit conversion methods
        System.out.println("1. WeightUnit Conversion Methods:");
        System.out.println("   1 kg in grams: " + WeightUnit.KILOGRAM.convertToBaseUnit(1.0) + " kg (base)");
        System.out.println("   1 kg in grams: " + WeightUnit.GRAM.convertFromBaseUnit(1.0) + " g");
        System.out.println("   1000 g in kg: " + WeightUnit.GRAM.convertToBaseUnit(1000.0) + " kg");
        System.out.println("   1 Pound in kg: " + WeightUnit.POUND.convertToBaseUnit(1.0) + " kg");
        System.out.println("   1 kg in Pound: " + WeightUnit.POUND.convertFromBaseUnit(1.0) + " lb");

        // 2. Demonstrate weight equality
        System.out.println("\n2. Weight Equality:");
        Weight kg1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight kg2 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight g1000 = new Weight(1000.0, WeightUnit.GRAM);
        Weight lb2_20462 = new Weight(2.20462, WeightUnit.POUND);

        demonstrateWeightEquality(kg1, kg2);
        demonstrateWeightEquality(kg1, g1000);
        demonstrateWeightEquality(kg1, lb2_20462);

        // 3. Demonstrate weight conversion
        System.out.println("\n3. Weight Conversion:");
        demonstrateWeightConversion(1.0, WeightUnit.KILOGRAM, WeightUnit.GRAM);
        demonstrateWeightConversion(2.0, WeightUnit.POUND, WeightUnit.KILOGRAM);
        demonstrateWeightConversion(500.0, WeightUnit.GRAM, WeightUnit.POUND);

        // 4. Demonstrate weight addition
        System.out.println("\n4. Weight Addition (Default Unit):");
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(2.0, WeightUnit.KILOGRAM));
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM));
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.POUND),
                new Weight(453.592, WeightUnit.GRAM));

        // 5. Demonstrate weight addition with target unit
        System.out.println("\n5. Weight Addition (Explicit Target Unit):");
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM);
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM),
                new Weight(2.20462, WeightUnit.POUND),
                WeightUnit.POUND);

    }
}