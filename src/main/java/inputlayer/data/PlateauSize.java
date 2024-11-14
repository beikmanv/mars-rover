package inputlayer.data;

public class PlateauSize {
    private final int width;
    private final int height;

    public PlateauSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Plateau dimensions must be positive integers.");
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

    @Override
    public String toString() {
        return "PlateauSize{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
