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
}
