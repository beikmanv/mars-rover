import inputlayer.data.PlateauSize;
import inputlayer.parsers.InstructionParser;
import inputlayer.parsers.PlateauParser;
import inputlayer.parsers.PositionParser;
import logiclayer.MissionControl;
import logiclayer.Plateau;
import logiclayer.Rover;
import logiclayer.enums.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    // Method to process input and return the final positions of all rovers
    public static List<String> processInput(List<String> input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        List<String> finalPositions = new ArrayList<>();

        // Parse the Plateau from the first line of input
        PlateauParser plateauParser = new PlateauParser();
        PlateauSize plateauSize = plateauParser.parsePlateau(input.get(0));
        Plateau plateau = new Plateau(plateauSize);

        // Create MissionControl
        MissionControl missionControl = new MissionControl(plateau);

        // Parse and deploy rovers
        PositionParser positionParser = new PositionParser();
        InstructionParser instructionParser = new InstructionParser();

        for (int i = 1; i < input.size(); i += 2) {
            // Parse rover's initial position
            Rover rover = new Rover(positionParser.parsePosition(input.get(i)), plateau);
            missionControl.deployRover(rover);

            // Parse and execute instructions
            Instruction[] instructions = instructionParser.parseInstruction(input.get(i + 1));
            missionControl.executeInstructions(rover, instructions);

            // Collect the final position of the rover
            finalPositions.add(rover.getPosition().toString());
        }

        return finalPositions;
    }

    // Main method for running from the console
    public static void main(String[] args) {
        List<String> input = List.of(
                "5 5",       // Plateau size
                "1 2 N",     // Rover 1 initial position
                "LMLMLMLMM", // Rover 1 instructions
                "3 3 E",     // Rover 2 initial position
                "MMRMMRMRRM" // Rover 2 instructions
        );

        List<String> results = processInput(input);

        for (String result : results) {
            System.out.println(result);
        }
    }
}
