package inputlayer.parsers;

import logiclayer.enums.Instruction;

public class InstructionParser {
    public Instruction[] parseInstruction(String inputInstructions) {
        if (inputInstructions == null || inputInstructions.trim().isEmpty()) {
            throw new IllegalArgumentException("Input instructions cannot be null or empty.");
        }

        // Iterate over the string (e.g. LLMMRML)
        char[] splitInstruction = inputInstructions.trim().toUpperCase().toCharArray();

        Instruction[] instructions = new Instruction[splitInstruction.length];

        for (int i = 0; i < splitInstruction.length; i++) {
            try {
                // Convert each character to the corresponding Instruction enum
                instructions[i] = Instruction.valueOf(String.valueOf(splitInstruction[i]));
                // else ... throw new Exception and catch
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid instruction: " + splitInstruction[i]);
            }
        }

        return instructions;
    }
}
