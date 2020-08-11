package com.pantry.entities;

import com.digidemic.unitof.UnitOf;
import com.fasterxml.jackson.annotation.JsonView;
import com.pantry.entities.util.Detailed;
import com.pantry.entities.util.Minimal;
import com.pantry.entities.util.QuantityTypeConverter;
import com.pantry.model.QuantityType;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @JsonView(Minimal.class)
    private java.lang.String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @JsonView(Detailed.class)
    private Recipe recipe;

    @Column(name = "numeric_quantity")
    @JsonView(Minimal.class)
    private double quantityNumber;

    @Column(name = "quantity_type")
    @Convert(converter = QuantityTypeConverter.class)
    @JsonView(Minimal.class)
    private QuantityType quantityType;

    public Ingredient() {
    }

    public Ingredient(java.lang.String name, Recipe recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public Ingredient(String name, double quantityNumber, QuantityType quantityType) {
        this.name = name;
        this.quantityNumber = quantityNumber;
        this.quantityType = quantityType;
    }

    public Ingredient(java.lang.String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public double getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(double quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }

    // Conversion helper methods
    public static double convertQuantityToUniversalValue(Ingredient ingredient) {
        if (QuantityType.isVolumetric(ingredient.getQuantityType())) {
            return convertVolumetricValue(ingredient);
        }
        if (QuantityType.isMass(ingredient.getQuantityType())) {
            return convertWeightValue(ingredient);
        }
        return -1.0;
    }

    public static double convertFromTablespoons(Ingredient ingredient) {
        return new UnitOf.Volume().fromTablespoonsUS(ingredient.getQuantityNumber()).toTeaspoonsMetric();
    }

    public static double convertFromPounds(Ingredient ingredient) {
        return new UnitOf.Mass().fromPounds(ingredient.getQuantityNumber()).toMilligrams();
    }

    public static double convertVolumetricValue(Ingredient ingredient) {
        if (ingredient.getQuantityType() == QuantityType.TABLESPOON) {
            return convertFromTablespoons(ingredient);
        }
        return -1.0;
    }

    public static double convertWeightValue(Ingredient ingredient) {
        if (ingredient.getQuantityType() == QuantityType.POUND) {
            return convertFromPounds(ingredient);
        }
        return -1.0;
    }
}
