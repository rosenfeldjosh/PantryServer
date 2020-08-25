package com.pantry.services;

import com.pantry.entities.Ingredient;
import com.pantry.entities.Recipe;
import com.pantry.respositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientsService {

    @Autowired
    IngredientRepository ingredientRepository;

    public IngredientsService() {

    }

    public IngredientsService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> createAndMapFromDTO(List<String> ingredientList, Recipe recipe) {
        return ingredientList
                .stream()
                .map(ingredientText -> this.createAndSaveIngredient(ingredientText, recipe))
                .collect(Collectors.toList());
    }

    public Ingredient createAndSaveIngredient(String ingredientName, Recipe recipe) {
        Ingredient newIngredient = new Ingredient(ingredientName);
        newIngredient.setRecipe(recipe);
        return ingredientRepository.save(newIngredient);
    }

}
