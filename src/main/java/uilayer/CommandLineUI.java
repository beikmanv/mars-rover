package uilayer;

import inputlayer.parsers.InstructionParser;
import inputlayer.parsers.PlateauParser;
import inputlayer.parsers.PositionParser;
import logiclayer.MissionControl;
import logiclayer.Plateau;
import logiclayer.Rover;
import logiclayer.enums.Instruction;

import java.util.Scanner;

public class CommandLineUI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Mars Rover simulation!");

        // Step 1: Get Plateau dimensions with validation
        Plateau plateau = null;
        while (plateau == null) {
            System.out.print("Enter the plateau dimensions (e.g., 5 5): ");
            String plateauInput = scanner.nextLine();

            try {
                PlateauParser plateauParser = new PlateauParser();
                plateau = new Plateau(plateauParser.parsePlateau(plateauInput));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        MissionControl missionControl = new MissionControl(plateau);

        // Step 2: Main loop to deploy and move rovers
        boolean continueSimulation = true;

        while (continueSimulation) {
            // Deploy a rover with validation
            Rover rover = null;
            while (rover == null) {
                System.out.print("Enter the rover's initial position (e.g., 1 2 N): ");
                String positionInput = scanner.nextLine();

                try {
                    PositionParser positionParser = new PositionParser();
                    rover = new Rover(positionParser.parsePosition(positionInput), plateau);
                    missionControl.deployRover(rover);
                } catch (IllegalArgumentException | IllegalStateException e) {
                    System.out.println(e.getMessage() + " Please try again.");
                }
            }

            // Move the rover with instructions
            boolean continueMoving = true;
            while (continueMoving) {
                System.out.print("Enter movement instructions (e.g., LMLMLMLMM): ");
                String instructionsInput = scanner.nextLine();

                try {
                    InstructionParser instructionParser = new InstructionParser();
                    Instruction[] instructions = instructionParser.parseInstruction(instructionsInput);

                    // Execute instructions and print rover's movement
                    missionControl.executeInstructions(rover, instructions);
                    System.out.println("Rover's final position: " + rover.getPosition());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + " Please try again.");
                    continue;
                }

                // Ask user if they want to continue moving the same rover
                System.out.print("Do you want to continue moving this rover? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    continueMoving = false;
                }
            }

            // Ask user if they want to deploy another rover
            System.out.print("Do you want to deploy another rover? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                continueSimulation = false;
            }
        }

        System.out.println("Thank you for using the Mars Rover simulation!");
        scanner.close();
    }
}
