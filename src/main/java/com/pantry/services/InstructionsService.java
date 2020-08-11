package com.pantry.services;

import com.digidemic.unitof.UnitOf;
import com.pantry.entities.Instruction;
import com.pantry.entities.Recipe;
import com.pantry.respositories.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class InstructionsService {

    @Autowired
    private InstructionRepository instructionRepository;

    public InstructionsService(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    public List<Instruction> createAndMapFromDTO(List<String> instructionList, Recipe recipe) {
        return IntStream.range(0, instructionList.size())
                .mapToObj(i -> this.createAndSaveInstruction(instructionList.get(i), i, recipe))
                .collect(Collectors.toList());
    }

    public Instruction createAndSaveInstruction(String instructionText, int stepNumber, Recipe recipe) {
        return instructionRepository.save(new Instruction(recipe, stepNumber, instructionText));
    }
}
