package logiclayer;

import logiclayer.enums.CompassDirection;

public class Position {
    private int x;
    private int y;
    private CompassDirection facing;

    public Position (int x, int y, CompassDirection facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CompassDirection getFacing() {
        return facing;
    }

    // Override the toString method to return the position in the desired format
    @Override
    public String toString() {
        return x + " " + y + " " + facing;
    }
}
