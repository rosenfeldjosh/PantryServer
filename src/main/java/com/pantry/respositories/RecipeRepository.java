package com.pantry.respositories;

import com.pantry.entities.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

    @Query(
            "SELECT recipe " +
            "FROM Ingredient ingredient " +
            "JOIN ingredient.recipe Recipe " +
            "WHERE ingredient.name IN :ingredientNames"
    )
    List<Recipe> findRecipesByIngredientNames(@Param(value = "ingredientNames") List<String> ingredientNames);
}