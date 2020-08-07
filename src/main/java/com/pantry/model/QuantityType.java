package com.pantry.model;

import java.util.Arrays;
import java.util.List;

public enum QuantityType {
    TEASPOON("teaspoon", "teaspoons"),
    TABLESPOON("tablespoon", "tablespoons"),
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
}
