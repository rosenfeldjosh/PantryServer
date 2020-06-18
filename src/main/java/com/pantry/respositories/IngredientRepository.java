package com.pantry.respositories;

import com.pantry.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

    public Ingredient findByName(String ingredientName);
}
