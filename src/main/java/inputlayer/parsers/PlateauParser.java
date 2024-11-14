package inputlayer.parsers;

import inputlayer.data.PlateauSize;

public class PlateauParser {
    public PlateauSize parsePlateau(String inputPlateau) {
        // Split into width(x) and height(y)
        String[] splitPlateau = inputPlateau.trim().split(" ");

        return new PlateauSize();
    }
}
