import inputlayer.parsers.PlateauParser;
import inputlayer.parsers.InstructionParser;
import inputlayer.parsers.PositionParser;
import inputlayer.data.PlateauSize;
import inputlayer.data.InitialPosition;
import logiclayer.enums.Instruction;
import logiclayer.enums.CompassDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    // PlateauParser Tests
    @Test
    void testPlateauParser_Valid() {
        // Arrange
        String input = "10 10";
        PlateauParser plateauParser = new PlateauParser();

        // Act
        PlateauSize plateauSize = plateauParser.parsePlateau(input);

        // Assert
        assertNotNull(plateauSize);
        assertEquals(10, plateauSize.getWidth());
        assertEquals(10, plateauSize.getHeight());
    }

    @Test
    void testPlateauParser_Invalid() {
        // Arrange
        String input = "10";  // Missing height value
        PlateauParser plateauParser = new PlateauParser();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            plateauParser.parsePlateau(input);
        });
        assertEquals("Plateau input must contain exactly two values: width and height.", exception.getMessage());
    }

    // InstructionParser Tests
    @Test
    public void testInstructionParser_Valid() {
        // Arrange
        String input = "LMMMRML";
        InstructionParser parser = new InstructionParser();

        // Act
        Instruction[] result = parser.parseInstruction(input);

        // Assert
        // Ensure the parsed instructions match what is expected
        assertEquals(Instruction.L, result[0]);
        assertEquals(Instruction.M, result[1]);
        assertEquals(Instruction.M, result[2]);
        assertEquals(Instruction.M, result[3]);
        assertEquals(Instruction.R, result[4]);
        assertEquals(Instruction.M, result[5]);
        assertEquals(Instruction.L, result[6]);
    }

    @Test
    void testInstructionParser_Invalid() {
        // Arrange
        String input = "LMXMM";  // Invalid instruction "X"
        InstructionParser instructionParser = new InstructionParser();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            instructionParser.parseInstruction(input);
        });
        assertEquals("Invalid instruction: X", exception.getMessage());
    }

    // PositionParser Tests
    @Test
    void testPositionParser_Valid() {
        // Arrange
        String input = "1 2 N";
        PositionParser positionParser = new PositionParser();

        // Act
        InitialPosition position = positionParser.parsePosition(input);

        // Assert
        assertNotNull(position);
        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(CompassDirection.N, position.getFacing());
    }

    @Test
    void testPositionParser_Invalid() {
        // Arrange
        String input = "1 2 Z";  // Invalid direction
        PositionParser positionParser = new PositionParser();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            positionParser.parsePosition(input);
        });
        assertEquals("Invalid compass direction: Z", exception.getMessage());
    }
}
