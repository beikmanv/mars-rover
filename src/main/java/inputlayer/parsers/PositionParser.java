package inputlayer.parsers;

import inputlayer.data.InitialPosition;
import inputlayer.enums.CompassDirection;
import inputlayer.enums.Instruction;

import javax.swing.text.Position;

public class PositionParser {

    public InitialPosition parsePosition( String inputPosition) {
        String[] splitPosition = inputPosition.trim().split(" ");

        //  Convert x and y into integers
        int x = Integer.parseInt(splitPosition[0]);
        int y = Integer.parseInt(splitPosition[1]);

        // Convert direction to CompassDirection enum
        CompassDirection facing = CompassDirection.valueOf(splitPosition[2]);

        return new InitialPosition(x, y, facing);
    }
}
