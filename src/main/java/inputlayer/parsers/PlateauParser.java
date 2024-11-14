package inputlayer.parsers;

import inputlayer.data.PlateauSize;

public class PlateauParser {
    public PlateauSize parsePlateau(String inputPlateau) {
        // Split into width(x) and height(y)
        String[] splitPlateau = inputPlateau.trim().toUpperCase().split(" ");

        if (splitPlateau.length != 2) {
            throw new IllegalArgumentException("Plateau input must contain exactly two values: width and height.");
        }
        try {
            int width = Integer.parseInt(splitPlateau[0]);
            int height = Integer.parseInt(splitPlateau[1]);

            if (width <= 0 || height <=0) {
                throw new IllegalArgumentException("Plateau dimensions must be positive integers.");
            }
            return new PlateauSize(width, height);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid plateau input: Both width and height must be integers.", e);
        }
    }
}
