package inputlayer.data;

public class PlateauSize {
    public static int width;
    public static int height;

    public PlateauSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Plateau dimensions must be positive numbers.");
        }
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
