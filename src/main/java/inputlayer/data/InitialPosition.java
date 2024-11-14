package inputlayer.data;

import inputlayer.enums.CompassDirection;

public class InitialPosition {
    private int x;
    private int y;
    private CompassDirection facing;

    public InitialPosition(int x, int y, CompassDirection facing) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Position coordinates must be non-negative.");
        }
        if (facing == null) {
            throw new IllegalArgumentException("Facing direction must not be null.");
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
