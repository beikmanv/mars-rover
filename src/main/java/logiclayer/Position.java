package logiclayer;

import logiclayer.enums.CompassDirection;
import java.util.Objects; // For hashCode and equals

public class Position {
    private int x;
    private int y;
    private CompassDirection facing;

    public Position(int x, int y, CompassDirection facing) {
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

    // Override equals for logical equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same object instance
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class

        Position position = (Position) obj;
        return x == position.x && // Compare x values
                y == position.y && // Compare y values
                facing == position.facing; // Compare CompassDirection
    }

    // Override hashCode for consistent hashing
    @Override
    public int hashCode() {
        return Objects.hash(x, y, facing); // Generate a hash based on x, y, and direction
    }
}
