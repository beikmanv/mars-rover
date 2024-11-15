import inputlayer.parsers.PlateauParser;
import logiclayer.Plateau;
import logiclayer.Position;
import logiclayer.Rover;
import inputlayer.data.PlateauSize;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    private Plateau plateau;
    private Rover rover;

    @BeforeEach
    public void setUp() {
        // Arrange: Define the plateau dimensions and initial position
        PlateauParser plateauParser = new PlateauParser();
        plateau = new Plateau(plateauParser.parsePlateau("5 5"));  // 5x5 plateau

        Position initialPosition = new Position(0, 0, CompassDirection.N);  // Start at position (0, 0) facing North
        rover = new Rover(initialPosition, plateau);  // Create a rover with the plateau and initial position
    }

    @Test
    public void testMoveFacingNorth() {
        // Arrange: Initial state is rover at (0, 0), facing North.

        // Act: Move the rover one step North
        rover.move();

        // Assert: The rover's new position should be (0, 1), still facing North.
        assertEquals(0, rover.getPosition().getX());
        assertEquals(1, rover.getPosition().getY());
        assertEquals(CompassDirection.N, rover.getDirection());
    }

    @Test
    public void testMoveFacingEast() {
        // Arrange: Initial state is rover at (0, 0), facing North. We rotate to face East.
        rover.rotate(Instruction.R);  // Rotate to the right (East)

        // Act: Move the rover one step East
        rover.move();

        // Assert: The rover's new position should be (1, 0), and it should be facing East.
        assertEquals(1, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(CompassDirection.E, rover.getDirection());
    }

    @Test
    public void testMoveFacingSouth() {
        // Arrange: Initial state is rover at (0, 0), facing North. We rotate to face South.
        rover.rotate(Instruction.L);  // Rotate to the left (South)

        // Act: Try moving the rover South, which should throw an exception because it's out of bounds.
        // Assert: The move should throw an IllegalArgumentException as it goes out of bounds.
        assertThrows(IllegalArgumentException.class, rover::move);
    }

    @Test
    public void testMoveOutOfBounds() {
        // Arrange: Initial state is rover at (0, 0), facing North. We rotate to face South.
        rover.rotate(Instruction.L);  // Rotate to face South

        // Act: Try moving out of bounds (from (0, 0) to (0, -1), which is out of bounds).
        // Assert: The move should throw an IllegalArgumentException.
        assertThrows(IllegalArgumentException.class, rover::move);
    }
}
