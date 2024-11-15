package logiclayer;

import inputlayer.data.PlateauSize;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;

import java.util.concurrent.ConcurrentMap;

public class Rover {
    private Position position;
    private Plateau plateau;
    private CompassDirection direction;

    public Rover(Position position, Plateau plateau) {
        this.position = position;
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
        int x = position.getX();
        int y = position.getY();

        // Use switch expression with arrow operator to simplify the case logic
        switch (direction) {
            case N -> {
                // Move North: Increase Y-coordinate
                if (y < plateau.getHeight() - 1) {
                    position = new Position(x, y + 1, direction);
                } else {
                    throw new IllegalArgumentException("Move out of bounds");
                }
            }
            case E -> {
                // Move East: Increase X-coordinate
                if (x < plateau.getWidth() - 1) {
                    position = new Position(x + 1, y, direction);
                } else {
                    throw new IllegalArgumentException("Move out of bounds");
                }
            }
            case S -> {
                // Move South: Decrease Y-coordinate
                if (y > 0) {
                    position = new Position(x, y - 1, direction);
                } else {
                    throw new IllegalArgumentException("Move out of bounds");
                }
            }
            case W -> {
                // Move West: Decrease X-coordinate
                if (x > 0) {
                    position = new Position(x - 1, y, direction);
                } else {
                    throw new IllegalArgumentException("Move out of bounds");
                }
            }
            default -> throw new IllegalArgumentException("Unknown direction");
        }
    }

    public Position getPosition() {
        return position;
    }

    public CompassDirection getDirection() {
        return direction;
    }

}
