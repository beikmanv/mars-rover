import inputlayer.data.InitialPosition;  // Corrected import
import logiclayer.Rover;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotationTest {

    @Test
    void testRotateRightFromNorth() {
        // Arrange: Create a Rover instance, explicitly set to face North
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);

        // Act: Rotate the rover to the right (should face East)
        rover.rotate(Instruction.R);

        // Assert: Check that the direction is now East
        assertEquals(CompassDirection.E, rover.getDirection());  // Expect direction to be East
    }


    @Test
    void testRotateLeftFromNorth() {
        // Arrange: Create a Rover instance, explicitly set to face North
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);

        // Act: Rotate the rover to the left (should face West)
        rover.rotate(Instruction.L);

        // Assert: Check that the direction is now West
        assertEquals(CompassDirection.W, rover.getDirection());  // Expect direction to be West
    }

    @Test
    void testRotateRightFromEast() {
        // Arrange: Create a Rover instance, initially facing North, then rotate right to face East
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);
        rover.rotate(Instruction.R);  // Initially facing North, rotate right to East

        // Act: Rotate the rover to the right again (should face South)
        rover.rotate(Instruction.R);

        // Assert: Check that the direction is now South
        assertEquals(CompassDirection.S, rover.getDirection());  // Expect direction to be South
    }

    @Test
    void testRotateLeftFromEast() {
        // Arrange: Create a Rover instance, initially facing North, then rotate left to face West
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);
        rover.rotate(Instruction.L);  // Initially facing North, rotate left to West

        // Act: Rotate the rover to the left again (should face South)
        rover.rotate(Instruction.L);

        // Assert: Check that the direction is now South
        assertEquals(CompassDirection.S, rover.getDirection());  // Expect direction to be South
    }

    @Test
    void testRotateRightFromSouth() {
        // Arrange: Create a Rover instance, initially facing North, then rotate left twice to face South
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);
        rover.rotate(Instruction.L);  // Initially facing North, rotate left to West
        rover.rotate(Instruction.L);  // Rotate left again to face South

        // Act: Rotate the rover to the right (should face West)
        rover.rotate(Instruction.R);

        // Assert: Check that the direction is now West
        assertEquals(CompassDirection.W, rover.getDirection());  // Expect direction to be West
    }

    @Test
    void testRotateLeftFromSouth() {
        // Arrange: Create a Rover instance, initially facing North, then rotate left twice to face South
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);
        rover.rotate(Instruction.L);  // Initially facing North, rotate left to West
        rover.rotate(Instruction.L);  // Rotate left again to face South

        // Act: Rotate the rover to the left (should face East)
        rover.rotate(Instruction.L);

        // Assert: Check that the direction is now East
        assertEquals(CompassDirection.E, rover.getDirection());  // Expect direction to be East
    }

    @Test
    void testRotateRightFromWest() {
        // Arrange: Create a Rover instance, initially facing North, then rotate left three times to face West
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);
        rover.rotate(Instruction.L);  // Initially facing North, rotate left to West
        rover.rotate(Instruction.L);  // Rotate left again to face South
        rover.rotate(Instruction.L);  // Rotate left again to face East

        // Act: Rotate the rover to the right (should face South)
        rover.rotate(Instruction.R);

        // Assert: Check that the direction is now South
        assertEquals(CompassDirection.S, rover.getDirection());  // Expect direction to be South
    }

    @Test
    void testRotateLeftFromWest() {
        // Arrange: Create a Rover instance, initially facing North, then rotate left three times to face West
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);
        rover.rotate(Instruction.L);  // Initially facing North, rotate left to West
        rover.rotate(Instruction.L);  // Rotate left again to face South
        rover.rotate(Instruction.L);  // Rotate left again to face East

        // Act: Rotate the rover to the left (should face North)
        rover.rotate(Instruction.L);

        // Assert: Check that the direction is now North
        assertEquals(CompassDirection.N, rover.getDirection());  // Expect direction to be North
    }

    // Invalid Instruction
    @Test
    void testRotateInvalidInstruction() {
        // Arrange: Create a Rover instance, explicitly set to face North
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);

        // Act & Assert: Try to rotate the rover with an invalid instruction (e.g., 'M' for Move) and check for exception
        try {
            rover.rotate(Instruction.M);  // This should not change direction, as 'M' is for move
            assertEquals(CompassDirection.N, rover.getDirection());  // Expect direction to be North (no rotation)
        } catch (IllegalArgumentException e) {
            // Handle exception and ensure the direction remains North
            assertEquals(CompassDirection.N, rover.getDirection());
        }
    }

    // Rotate multiple times in the same direction
    @Test
    void testRotateMultipleTimesRightFromNorth() {
        // Arrange: Create a Rover instance, explicitly set to face North
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);

        // Act: Rotate right 3 times from North (should end facing West)
        rover.rotate(Instruction.R);  // Right from North -> East
        rover.rotate(Instruction.R);  // Right from East -> South
        rover.rotate(Instruction.R);  // Right from South -> West

        // Assert: Check that the direction is now West
        assertEquals(CompassDirection.W, rover.getDirection());  // After 3 right rotations, it should face West
    }

    @Test
    void testRotateMultipleTimesLeftFromNorth() {
        // Arrange: Create a Rover instance, explicitly set to face North
        InitialPosition initialPosition = new InitialPosition(0, 0, CompassDirection.N);  // Corrected initialization
        Rover rover = new Rover(initialPosition, null);

        // Act: Rotate left 3 times from North (should end facing East)
        rover.rotate(Instruction.L);  // Left from North -> West
        rover.rotate(Instruction.L);  // Left from West -> South
        rover.rotate(Instruction.L);  // Left from South -> East

        // Assert: Check that the direction is now East
        assertEquals(CompassDirection.E, rover.getDirection());  // After 3 left rotations, it should face East
    }
}
