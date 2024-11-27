package logiclayer;

import java.util.ArrayList;
import java.util.List;

public class MissionControl {
    private final Plateau plateau;
    private final List<Rover> rovers; // List to keep track of all deployed rovers

    public MissionControl(Plateau plateau) {
        this.plateau = plateau;
        this.rovers = new ArrayList<>();
    }

    // Deploy a new rover onto the plateau
    public void deployRover(Rover rover) {
        // Check if the starting position is empty
        if (isPositionEmpty(rover.getPosition().getX(), rover.getPosition().getY())) {
            rovers.add(rover); // Add the rover to the list
        } else {
            throw new IllegalStateException("Cannot deploy rover: Position already occupied.");
        }
    }

    // Check if a position is empty
    public boolean isPositionEmpty(int x, int y) {
        for (Rover rover : rovers) {
            if (rover.getPosition().getX() == x && rover.getPosition().getY() == y) {
                return false; // Position is occupied
            }
        }
        return true; // Position is empty
    }

    // Process instructions for a given rover
    public void executeInstructions(Rover rover, String instructions) {
        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'M') {
                // Move the rover and check if the new position is valid
                Position newPosition = calculateNextPosition(rover);
                if (isPositionEmpty(newPosition.getX(), newPosition.getY()) &&
                        plateau.isWithinBounds(newPosition.getX(), newPosition.getY())) {
                    rover.move(); // Update rover position
                } else {
                    System.out.println("Move aborted: Position occupied or out of bounds.");
                }
            } else if (instruction == 'L' || instruction == 'R') {
                rover.rotate(instruction == 'L' ? Instruction.L : Instruction.R); // Rotate the rover
            } else {
                throw new IllegalArgumentException("Invalid instruction: " + instruction);
            }
        }
    }

    // Helper to calculate the next position without moving the rover
    private Position calculateNextPosition(Rover rover) {
        int newX = rover.getPosition().getX();
        int newY = rover.getPosition().getY();
        switch (rover.getDirection()) {
            case N -> newY += 1;
            case E -> newX += 1;
            case S -> newY -= 1;
            case W -> newX -= 1;
        }
        return new Position(newX, newY, rover.getDirection());
    }

    // Retrieve all rovers (e.g., for status reporting)
    public List<Rover> getRovers() {
        return rovers;
    }
}
