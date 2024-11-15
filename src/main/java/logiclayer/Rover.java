package logiclayer;

import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;

import java.util.concurrent.ConcurrentMap;

public class Rover {

    private CompassDirection direction;

    // Constructor for the default direction (e.g. North)
    public Rover() {
        this.direction = CompassDirection.N;
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

    public CompassDirection getDirection() {
        return direction;
    }
}
