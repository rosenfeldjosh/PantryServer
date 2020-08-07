package com.pantry.respositories;

import com.pantry.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    List<Ingredient> findAll();
    public Ingredient findByName(String ingredientName);
}
