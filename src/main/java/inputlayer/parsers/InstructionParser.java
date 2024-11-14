package inputlayer.parsers;

import inputlayer.enums.Instruction;

public class InstructionParser {
    public Instruction[] parseIntruction (String inputInstructions) {
        String[] splitInstructions = inputInstructions.trim().split(" ");

        //Convert the string array into an Instruction array
        Instruction[] instructions = new Instruction[splitInstructions.length];
        for (int i = 0; i < splitInstructions.length; i++) {
            //Convert each character to enum
            instructions[i] = Instruction.valueOf(splitInstructions[i]);
        }

        return instructions;
    }
}
