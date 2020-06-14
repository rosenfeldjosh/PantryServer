package com.pantry.model;

import java.io.Serializable;
import java.util.List;

public class RecipeDTO implements Serializable {
    private String title;
    private List<String> ingredients;

    public RecipeDTO(String title, List<String> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
    }

    public RecipeDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
