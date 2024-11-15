package inputlayer.data;

import logiclayer.enums.CompassDirection;

public class InitialPosition {
    private int x;
    private int y;
    private CompassDirection facing;

    public InitialPosition(int x, int y, CompassDirection facing) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Position coordinates must be positive X and Y.");
        }
        if (facing == null) {
            throw new IllegalArgumentException("Facing direction must be N, E, S or W.");
        }
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    // Getter for x
    public int getX() {
        return x;
    }

    // Getter for y
    public int getY() {
        return y;
    }

    public CompassDirection getFacing() {
        return facing;
    }

}
