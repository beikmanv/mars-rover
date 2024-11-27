package logiclayer;

import inputlayer.data.InitialPosition;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;

public class Rover {
    private Position position;
    private Plateau plateau;
    private CompassDirection direction;

    // Accessor for position
    public Position getPosition() {
        return position;
    }

    // Accessor for direction
    public CompassDirection getDirection() {
        return direction;
    }

    public Rover(InitialPosition initialPosition, Plateau plateau) {
        this.position = new Position(initialPosition.getX(), initialPosition.getY(), initialPosition.getFacing());
        this.plateau = plateau;
        this.direction = position.getFacing();
    }

    public void rotate(Instruction instruction) {
        if (instruction == Instruction.L) {
            switch (direction) {
                case N -> direction = CompassDirection.W;
                case W -> direction = CompassDirection.S;
                case S -> direction = CompassDirection.E;
                case E -> direction = CompassDirection.N;
            }
        } else if (instruction == Instruction.R) {
            switch (direction) {
                case N -> direction = CompassDirection.E;
                case E -> direction = CompassDirection.S;
                case S -> direction = CompassDirection.W;
                case W -> direction = CompassDirection.N;
            }
        } else {
            throw new IllegalArgumentException("Invalid instruction: " + instruction);
        }
    }

    public void move() {
        int newX = position.getX();
        int newY = position.getY();

        // Print the current position and direction
        System.out.print(position.getX() + " " + position.getY() + " " + direction + " -> ");

        switch (direction) {
            case N -> newY += 1;  // Move North
            case E -> newX += 1;  // Move East
            case S -> newY -= 1;  // Move South
            case W -> newX -= 1;  // Move West
        }

        // Check if new position is within bounds of the plateau
        if (newX >= 0 && newY >= 0 && newX <= plateau.getWidth() && newY <= plateau.getHeight()) {
            // Update position if move is valid
            position = new Position(newX, newY, direction);

            // Print the updated position after the move
            System.out.println(position.getX() + " " + position.getY() + " " + direction);
        } else {
            // If out of bounds, print a message and no move happens
            System.out.println("You can't move out of bounds.");
        }
    }

}
