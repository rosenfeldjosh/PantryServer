package com.pantry.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.pantry.entities.Ingredient;
import com.pantry.entities.util.Minimal;
import com.pantry.respositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {

    @Autowired private IngredientRepository ingredientRepository;

    @GetMapping("getAllIngredients")
    @JsonView(Minimal.class)
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
