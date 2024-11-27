import inputlayer.parsers.PlateauParser;
import logiclayer.Plateau;
import logiclayer.Position;
import logiclayer.Rover;
import inputlayer.data.PlateauSize;
import inputlayer.data.InitialPosition;  // Corrected import
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    private Plateau plateau;
    private Rover rover;

    @BeforeEach
    public void setUp() {
        // Arrange: Define the plateau dimensions and initial position
        PlateauParser plateauParser = new PlateauParser();
        plateau = new Plateau(plateauParser.parsePlateau("5 5"));  // 5x5 plateau

        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
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
    public void testMoveFacingWestOutOfBounds() {
        // Arrange: Create a plateau and place the rover at (0, 0), facing West.
        Plateau plateau = new Plateau(new PlateauSize(7, 7));
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.W);
        Rover rover = new Rover(initialPosition, plateau);

        // Act: Attempt to move west out of bounds.
        rover.move();

        // Assert 1: Verify the position remains unchanged.
        Position expectedPosition = new Position(0, 0, CompassDirection.W);
        assertEquals(expectedPosition, rover.getPosition());

    }

    @Test
    public void testMoveFacingSouthThrowsException() {
        // Arrange: Create a plateau and place the rover at (0, 0), facing South.
        Plateau plateau = new Plateau(new PlateauSize(5, 5));
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.S);
        Rover rover = new Rover(initialPosition, plateau);

        // Capture the System.out print statements
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Act: Attempt to move the rover south (which should print a message)
        rover.move();

        // Assert: Check the printed message
        String expectedMessage = "You can't move out of bounds.";
        assertTrue(outputStream.toString().contains(expectedMessage));

        // Optionally, assert the position remains unchanged
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(CompassDirection.S, rover.getDirection());

        // Reset System.out to its original state
        System.setOut(System.out);
    }


    @Test
    public void testMoveOutOfBounds() {
        // Arrange: Initial state is rover at (0, 0), facing North. Rotate to face South.
        rover.rotate(Instruction.L);  // Rotate twice to face South
        rover.rotate(Instruction.L);

        // Act: Attempt to move south out of bounds.
        rover.move();

        // Assert: Verify the position remains unchanged and direction is still South.
        assertEquals(0, rover.getPosition().getX());
        assertEquals(0, rover.getPosition().getY());
        assertEquals(CompassDirection.S, rover.getDirection());
    }

}
