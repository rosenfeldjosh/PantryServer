package com.pantry.entities;

import com.pantry.entities.util.RecipeStep;

import javax.persistence.*;

@Entity
@Table(name = "instructions")
public class Instruction {

    @EmbeddedId
    private RecipeStep recipeStep;

    @Column(name = "step_text")
    private String stepText;

    public Instruction() {
    }

    public Instruction(Recipe recipe, int stepNumber, String stepText) {
        this( new RecipeStep(recipe, stepNumber), stepText);
    }

    public Instruction(RecipeStep recipeStep, String stepText) {
        this.recipeStep = recipeStep;
        this.stepText = stepText;
    }

    public RecipeStep getRecipeStep() {
        return recipeStep;
    }

    public int getStepNumber() {
        return recipeStep.getStepNumber();
    }

    public Recipe getRecipe() {
        return recipeStep.getRecipe();
    }

    public void setRecipeStep(RecipeStep recipeStep) {
        this.recipeStep = recipeStep;
    }

    public String getStepText() {
        return stepText;
    }

    public void setStepText(String stepText) {
        this.stepText = stepText;
    }
}
