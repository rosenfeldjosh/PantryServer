package com.pantry.entities;

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
    private int quantityNumber;

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

    public int getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(int quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }
}
