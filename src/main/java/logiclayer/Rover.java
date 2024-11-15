package logiclayer;

import inputlayer.data.PlateauSize;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;

import java.util.concurrent.ConcurrentMap;

public class Rover {
    private Position position;
    private Plateau plateau;
    private CompassDirection direction;

    // Constructor for the default direction (e.g. North)
    public Rover(Position position, Plateau plateau) {
        this.position = position;
        this. plateau = plateau;
        this.direction = position.getFacing();
    }

    public void move() {
        int x = position.getX();
        int y = position.getY();
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

    public Position getPosition() {
        return position;
    }

    public CompassDirection getDirection() {
        return direction;
    }

}
