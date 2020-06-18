package com.pantry.entities.util;

import com.pantry.entities.Recipe;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RecipeStep implements Serializable {

    @JoinColumn(name = "recipe_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

    @Column(name = "step_number")
    private int stepNumber;

    public RecipeStep() {
    }

    public RecipeStep(Recipe recipe, int stepNumber) {
        this.recipe = recipe;
        this.stepNumber = stepNumber;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
