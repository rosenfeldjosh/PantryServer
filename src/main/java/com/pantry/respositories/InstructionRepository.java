package com.pantry.respositories;

import com.pantry.entities.Ingredient;
import com.pantry.entities.Instruction;
import com.pantry.entities.util.RecipeStep;
import org.springframework.data.repository.CrudRepository;

public interface InstructionRepository extends CrudRepository<Instruction, RecipeStep> {

}
