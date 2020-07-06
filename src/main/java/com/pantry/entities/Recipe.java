package com.pantry.entities;

import com.pantry.entities.util.RecipeStep;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String name;

    @Column(name = "content_link")
    private String contentLink;

    @Column(name = "image_link")
    private String imageLink;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private List<Ingredient> ingredients;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipeStep.recipe")
    private List<Instruction> instructions;

    public Recipe() {
    }

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Recipe(String name, String contentLink, String imageLink) {
        this.name = name;
        this.contentLink = contentLink;
        this.imageLink = imageLink;
    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, List<Ingredient> ingredients, List<Instruction> instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
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

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }
}
