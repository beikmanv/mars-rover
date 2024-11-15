import inputlayer.data.PlateauSize;
import inputlayer.data.InitialPosition;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    // Test PlateauSize constructor and methods
    @Test
    void testPlateauSize_Valid() {
        // Arrange
        int width = 10;
        int height = 10;

        // Act
        PlateauSize plateauSize = new PlateauSize(width, height);

        // Assert
        assertNotNull(plateauSize);
        assertEquals(10, plateauSize.getWidth());
        assertEquals(10, plateauSize.getHeight());
    }

    @Test
    void testPlateauSize_Invalid() {
        // Arrange & Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new PlateauSize(-9, 10);  // Invalid width
        });

        // Assert
        assertEquals("Plateau dimensions must be positive numbers.", exception.getMessage());
    }

    // Test InitialPosition constructor and methods
    @Test
    void testInitialPosition_Valid() {
        // Arrange
        int x = 1;
        int y = 2;
        CompassDirection facing = CompassDirection.N;

        // Act
        InitialPosition initialPosition = new InitialPosition(x, y, facing);

        // Assert
        assertNotNull(initialPosition);
        assertEquals(1, initialPosition.getX());
        assertEquals(2, initialPosition.getY());
        assertEquals(CompassDirection.N, initialPosition.getFacing());
    }

    @Test
    void testInitialPosition_Invalid() {
        // Arrange & Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new InitialPosition(-1, 2, CompassDirection.N);  // Invalid x value
        });

        // Assert
        assertEquals("Position coordinates must be positive X and Y.", exception.getMessage());
    }

    // Test CompassDirection enum
    @Test
    void testCompassDirection_EnumValues() {
        // Arrange & Act
        CompassDirection north = CompassDirection.N;
        CompassDirection east = CompassDirection.E;

        // Assert
        assertNotNull(north);
        assertEquals("N", north.name());
        assertEquals("E", east.name());
    }

    // Test Instruction enum
    @Test
    void testInstruction_EnumValues() {
        // Arrange & Act
        Instruction left = Instruction.L;
        Instruction right = Instruction.R;
        Instruction move = Instruction.M;

        // Assert
        assertNotNull(left);
        assertEquals("L", left.name());
        assertEquals("R", right.name());
        assertEquals("M", move.name());
    }
}
