package com.pantry.model;

import java.io.Serializable;
import java.util.List;

public class RecipeDTO implements Serializable {
    private String title;
    private List<String> ingredients;
    private List<String> instructions;
    private String contentLink;
    private String imageLink;

    public RecipeDTO(String title, List<String> ingredients, List<String> instructions) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public RecipeDTO(String title, List<String> ingredients, List<String> instructions, String contentLink, String imageLink) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.contentLink = contentLink;
        this.imageLink = imageLink;
    }

    public RecipeDTO() {
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
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

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
