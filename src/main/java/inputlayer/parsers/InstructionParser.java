package inputlayer.parsers;

import inputlayer.enums.Instruction;

import java.util.Arrays;

public class InstructionParser {
    public Instruction[] parseInstruction(String inputInstructions) {
        // Iterate over the string (e.g. LLMMRML)
        char[] splitInstruction = inputInstructions.trim().toUpperCase().toCharArray();

        Instruction[] instructions = new Instruction[splitInstruction.length];
        for (int i = 0; i < splitInstruction.length; i++) {
            try {
                // Convert each character to the corresponding Instruction enum
                instructions[i] = Instruction.valueOf(String.valueOf(splitInstruction[i]));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid instruction: " + splitInstruction[i]);
            }
        }

        return instructions;
    }
}
