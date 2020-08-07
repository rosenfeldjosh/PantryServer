package com.pantry.controllers;

import com.pantry.entities.Recipe;
import com.pantry.model.RecipeDTO;
import com.pantry.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("postNewRecipe")
    public ResponseEntity<String> postNewRecipe(@RequestBody RecipeDTO newRecipe) {
        recipeService.addNewRecipe(newRecipe);
        return ResponseEntity.ok("Recipe Posted");
    }

    @GetMapping("getRecipesFromIngredients")
    public List<Recipe> getRecipesFromIngredients(@RequestBody List<String> ingredientNames) {
        return recipeService.findRecipesByIngredients(ingredientNames);
    }
}
