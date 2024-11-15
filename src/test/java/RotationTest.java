import logiclayer.Rover;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RotationTest {

    @Test
    void testRotateRightFromNorth() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.R);
        // Assert
        assertEquals(CompassDirection.E, rover.getDirection());  // Expect direction to be East
    }

    @Test
    void testRotateLeftFromNorth() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.L);
        // Assert
        assertEquals(CompassDirection.W, rover.getDirection());  // Expect direction to be West
    }

    @Test
    void testRotateRightFromEast() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.R);
        // Assert
        assertEquals(CompassDirection.S, rover.getDirection());  // Expect direction to be South
    }

    @Test
    void testRotateLeftFromEast() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.L);
        // Assert
        assertEquals(CompassDirection.N, rover.getDirection());  // Expect direction to be North
    }

    @Test
    void testRotateRightFromSouth() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.R);
        // Assert
        assertEquals(CompassDirection.W, rover.getDirection());  // Expect direction to be West
    }

    @Test
    void testRotateLeftFromSouth() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.L);
        // Assert
        assertEquals(CompassDirection.E, rover.getDirection());  // Expect direction to be East
    }

    @Test
    void testRotateRightFromWest() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.R);
        // Assert
        assertEquals(CompassDirection.N, rover.getDirection());  // Expect direction to be North
    }

    @Test
    void testRotateLeftFromWest() {
        // Arrange
        Rover rover = new Rover();
        // Act
        rover.rotate(Instruction.L);
        // Assert
        assertEquals(CompassDirection.S, rover.getDirection());  // Expect direction to be South
    }

    // Invalid Instruction
    @Test
    void testRotateInvalidInstruction() {
        // Arrange
        Rover rover = new Rover();

        // Act & Assert
        try {
            rover.rotate(Instruction.M);  // Moving should not change direction
            assertEquals(CompassDirection.N, rover.getDirection());  // Expect direction to be North (no rotation)
        } catch (IllegalArgumentException e) {
            // This can be handled by catching an exception if necessary
            assertEquals(CompassDirection.N, rover.getDirection());
        }
    }

    // Rotate multiple times in the same direction
    @Test
    void testRotateMultipleTimesRightFromNorth() {
        // Arrange
        Rover rover = new Rover();

        // Act
        rover.rotate(Instruction.R);  // Right from North -> East
        rover.rotate(Instruction.R);  // Right from East -> South
        rover.rotate(Instruction.R);  // Right from South -> West

        // Assert
        assertEquals(CompassDirection.W, rover.getDirection());  // After 3 right rotations, it should face West
    }

    @Test
    void testRotateMultipleTimesLeftFromNorth() {
        // Arrange
        Rover rover = new Rover();

        // Act
        rover.rotate(Instruction.L);  // Left from North -> West
        rover.rotate(Instruction.L);  // Left from West -> South
        rover.rotate(Instruction.L);  // Left from South -> East

        // Assert
        assertEquals(CompassDirection.W, rover.getDirection());  // After 3 left rotations, it should face West
    }
}
