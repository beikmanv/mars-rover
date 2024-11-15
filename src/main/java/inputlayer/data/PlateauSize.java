package inputlayer.data;

public class PlateauSize {
    private final int width;
    private final int height;

    // Constructor that initializes width and height
    public PlateauSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Plateau dimensions must be positive numbers.");
        }

        this.width = width;
        this.height = height;
    }

    // Getters for width and height
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
