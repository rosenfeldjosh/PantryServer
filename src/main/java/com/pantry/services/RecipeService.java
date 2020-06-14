package com.pantry.services;

import com.pantry.entities.Ingredient;
import com.pantry.entities.Recipe;
import com.pantry.model.RecipeDTO;
import com.pantry.respositories.IngredientRepository;
import com.pantry.respositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private
    RecipeRepository recipeRepository;

    @Autowired
    private
    IngredientRepository ingredientRepository;

    public RecipeService() {
    }

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void addNewRecipe(RecipeDTO newRecipe) {
        Recipe r = new Recipe(newRecipe.getTitle());
        recipeRepository.save(r);
        ingredientRepository.saveAll(
                newRecipe.getIngredients()
                        .stream()
                        .filter(ingredient -> !ingredient.isEmpty())
                        .map(Ingredient::new).collect(Collectors.toList()));
    }
}
