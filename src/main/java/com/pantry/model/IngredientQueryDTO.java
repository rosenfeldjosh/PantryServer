package com.pantry.model;

public class IngredientQueryDTO {
    String ingredientName;
    QuantityType quantityType;
    double quantityNumber;

    public IngredientQueryDTO(String ingredientName, QuantityType quantityType, double quantityNumber) {
        this.ingredientName = ingredientName;
        this.quantityType = quantityType;
        this.quantityNumber = quantityNumber;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }

    public double getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(double quantityNumber) {
        this.quantityNumber = quantityNumber;
    }
}
