package com.pantry.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum QuantityType {
    TEASPOON("teaspoon", "teaspoons"),
    TABLESPOON("tablespoon", "tablespoons"),
    POUND("pound", "pounds"),
    BLANK("");

    private final List<java.lang.String> values;

    QuantityType(java.lang.String...values) {
        this.values = Arrays.asList(values);
    }

    public List<java.lang.String> getValues() {
        return values;
    }

    public static QuantityType forTypeString(java.lang.String typeString) {
        for (QuantityType quantityType: QuantityType.values()) {
            if (quantityType.getValues().contains(typeString)) {
                return quantityType;
            }
        }
        return null;
    }

    public static boolean isVolumetric(QuantityType quantityType) {
        return Arrays.asList(QuantityType.TABLESPOON, QuantityType.TABLESPOON).contains(quantityType);
    }

    public static boolean isMass(QuantityType quantityType) {
        return Arrays.asList(QuantityType.POUND).contains(quantityType);
    }

    public static double tablespoonToGram(double tablespoons) {
        return tablespoons * 12.781700527272;
    }

    public static double poundToGram(double pounds) {
        return pounds * 453.592;
    }
}
