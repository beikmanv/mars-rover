import inputlayer.parsers.PlateauParser;
import inputlayer.parsers.PositionParser;
import logiclayer.Plateau;
import logiclayer.Rover;
import logiclayer.enums.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<String> processInput(List<String> input) {
        // Parse the plateau size
        String plateauInput = input.get(0);
        PlateauParser plateauParser = new PlateauParser();
        Plateau plateau = new Plateau(plateauParser.parsePlateau(plateauInput));

        // Initialize a PositionParser for rover positions
        PositionParser positionParser = new PositionParser();

        List<String> results = new ArrayList<>();

        // Process each rover's initial position and instructions from input String
        for (int i = 1; i < input.size(); i += 2) {
            String initialPosition = input.get(i);    // Initial position of the rover
            String instructions = input.get(i + 1);   // Instructions for rover

            // Parse the initial position and create a Rover object
            Rover rover = new Rover(positionParser.parsePosition(initialPosition), plateau);

            // Execute the rover instructions (Move or Rotate)
            for (char instruction : instructions.toCharArray()) {
                if (instruction == 'M') {
                    rover.move();
                } else if (instruction == 'L' || instruction == 'R') {
                    rover.rotate(instruction == 'L' ? Instruction.L : Instruction.R);
                } else {
                    throw new IllegalArgumentException("Invalid instruction: " + instruction);
                }
            }

            // Capture the final position of the rover
            results.add(rover.getPosition().toString());
        }

        return results;
    }

    public static void main(String[] args) {
        // Example input as given in the brief
        List<String> input = List.of(
                "5 5",
                "1 2 N",
                "LMLMLMLMM",
                "3 3 E",
                "MMRMMRMRRM"
        );

        // Call processInput to execute the commands
        List<String> results = processInput(input);

        // Print the final positions of the rovers
        results.forEach(System.out::println);
    }
}
