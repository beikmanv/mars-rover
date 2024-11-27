package logiclayer;

import inputlayer.data.PlateauSize;

public class Plateau {
    private PlateauSize plateauSize;

    public Plateau(PlateauSize plateauSize) {
        this.plateauSize = plateauSize;
    }

    public int getWidth() {
        return plateauSize.getWidth();
    }

    public int getHeight() {
        return plateauSize.getHeight();
    }

    // New method to check if a position is within bounds
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x <= PlateauSize.width && y >= 0 && y <= PlateauSize.height;
    }
}
