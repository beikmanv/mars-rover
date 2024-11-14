package inputlayer.data;

import inputlayer.enums.CompassDirection;

public class InitialPosition {
    private int x;
    private int y;
    private CompassDirection facing;

    public InitialPosition(int x, int y, CompassDirection facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public CompassDirection getFacing() {
        return facing;
    }
}
