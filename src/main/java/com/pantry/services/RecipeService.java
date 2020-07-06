package com.pantry.services;

import com.pantry.entities.Ingredient;
import com.pantry.entities.Instruction;
import com.pantry.entities.Recipe;
import com.pantry.model.RecipeDTO;
import com.pantry.respositories.IngredientRepository;
import com.pantry.respositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RecipeService {

    @Autowired
    private
    RecipeRepository recipeRepository;

    @Autowired
    private
    IngredientsService ingredientsService;

    @Autowired
    private InstructionsService instructionsService;

    public RecipeService() {
    }

    public RecipeService(RecipeRepository recipeRepository, IngredientsService ingredientsService, InstructionsService instructionsService) {
        this.recipeRepository = recipeRepository;
        this.ingredientsService = ingredientsService;
        this.instructionsService = instructionsService;
    }

    public RecipeService(RecipeRepository recipeRepository, IngredientsService ingredientsService) {
        this.recipeRepository = recipeRepository;
        this.ingredientsService = ingredientsService;
    }

    public void addNewRecipe(RecipeDTO recipeDTO) {
        Recipe newRecipe = new Recipe(recipeDTO.getTitle(), recipeDTO.getContentLink(), recipeDTO.getImageLink());
        recipeRepository.save(newRecipe);
        ingredientsService.createAndMapFromDTO(recipeDTO.getIngredients(), newRecipe);
        instructionsService.createAndMapFromDTO(recipeDTO.getInstructions(), newRecipe);
    }

    public Double scoreRecipeForIngredients(List<String> ingredientNames, Recipe recipe) {
        int numberOfIngredientsInRecipe = recipe.getIngredients().size();
        long numberOfMutualIngredients = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredientNames.contains(ingredient.getName()))
                .count();
        // the amount of mutual ingredients is important, but we don't want to always value recipes with less
        double percentageOfRecipeCovered = (double) numberOfMutualIngredients/numberOfIngredientsInRecipe;
        return percentageOfRecipeCovered * numberOfMutualIngredients;
    }

    /**
     * Finds all recipes that contain the ingredients given. Then prioritizes them based on a calculated "score"
     */
    public List<Recipe> findRecipesByIngredients(List<String> ingredientNames) {
        return recipeRepository.findRecipesByIngredientNames(ingredientNames)
                .stream()
                .sorted(Comparator.comparing((Recipe recipe) -> scoreRecipeForIngredients(ingredientNames, recipe)))
                .collect(Collectors.toList());
    }
}
