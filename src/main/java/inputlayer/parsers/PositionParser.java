package inputlayer.parsers;

import inputlayer.data.InitialPosition;
import logiclayer.enums.CompassDirection;

public class PositionParser {

    public InitialPosition parsePosition(String inputPosition) {
        String[] splitPosition = inputPosition.trim().toUpperCase().split(" ");

        if (splitPosition.length != 3) {
            throw new IllegalArgumentException("Invalid position format. Expected: x y direction.");
        }

        //  Convert x and y into integers
        int x = Integer.parseInt(splitPosition[0]);
        int y = Integer.parseInt(splitPosition[1]);

        // Convert direction to CompassDirection enum
        CompassDirection facing;
        try {
            facing = CompassDirection.valueOf(splitPosition[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid compass direction: " + splitPosition[2]);
        }

        return new InitialPosition(x, y, facing);
    }
}
