package com.pantry.services;

import com.pantry.entities.Ingredient;
import com.pantry.entities.Instruction;
import com.pantry.entities.Recipe;
import com.pantry.model.RecipeDTO;
import com.pantry.respositories.IngredientRepository;
import com.pantry.respositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Recipe newRecipe = new Recipe(recipeDTO.getTitle());
        recipeRepository.save(newRecipe);
        ingredientsService.createAndMapFromDTO(recipeDTO.getIngredients(), newRecipe);
        instructionsService.createAndMapFromDTO(recipeDTO.getInstructions(), newRecipe);
    }
}
