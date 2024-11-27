import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class IntegrationTest {

    // Test: Integration with valid input
    @Test
    void testIntegrationWithValidInput() {
        // Arrange: Set up the input data
        List<String> input = Arrays.asList(
                "100 100",          // Plateau size: 100x100
                "1 2 N",        // Rover 1 starts at (1, 2) facing North
                "LMLMLMLMM",    // Rover 1: Follow the movement instructions
                "3 3 E",        // Rover 2 starts at (3, 3) facing East
                "MMRMMRMRRM"    // Rover 2: Follow the movement instructions
        );

        // Act: Process the input and get the results
        List<String> results = Main.processInput(input);

        // Assert: Check if the final positions match the expected values
        assertEquals("1 3 N", results.get(0)); // Rover 1
        assertEquals("5 1 E", results.get(1)); // Rover 2
    }

    // Test: Rover tries to move out of bounds
    @Test
    void testRoverOutOfBounds() {
        // Arrange: Set up the input data with a rover trying to move beyond the plateau's top boundary
        List<String> input = Arrays.asList(
                "5 5",          // Plateau size: 5x5
                "0 0 N",        // Rover starts at (0, 0) facing North
                "MMMM"          // Rover moves beyond the top boundary
        );

        // Act: Process the input and get the results
        List<String> results = Main.processInput(input);

        // Assert: Check if the rover stays within the boundaries and does not exceed the plateau's size
        assertEquals("0 4 N", results.get(0)); // Rover 1 stays at (0, 4)
    }

    // Test: No movement for the rover
    @Test
    void testNoMovement() {
        // Arrange: Set up the input data with no movement instructions for the rover
        List<String> input = Arrays.asList(
                "5 5",          // Plateau size: 5x5
                "1 1 N",        // Rover starts at (1, 1) facing North
                ""              // No movement instructions
        );

        // Act: Process the input and get the results
        List<String> results = Main.processInput(input);

        // Assert: Check if the rover stays at the initial position
        assertEquals("1 1 N", results.get(0)); // Rover 1 stays at (1, 1)
    }

    // Test: Rotating rover 360 degrees (multiple left turns)
    @Test
    void testEdgeDirection() {
        // Arrange: Set up the input data with a rover rotating 360 degrees (4 left turns)
        List<String> input = Arrays.asList(
                "5 5",          // Plateau size: 5x5
                "1 1 N",        // Rover starts at (1, 1) facing North
                "LLLLLLLL"      // Rotate 4 times (L for 360 degrees), rover should face North again
        );

        // Act: Process the input and get the results
        List<String> results = Main.processInput(input);

        // Assert: Check if the rover remains facing North after 4 left turns
        assertEquals("1 1 N", results.get(0)); // Rover 1 stays facing North
    }

    // Test: Rover moves and rotates several times
    @Test
    void testMultipleMoves() {
        // Arrange: Set up the input data with a rover making multiple moves and rotations
        List<String> input = Arrays.asList(
                "5 5",          // Plateau size: 5x5
                "2 2 N",        // Rover starts at (2, 2) facing North
                "MMRMMRMM"      // Rover should end up at (4, 2) facing South
        );

        // Act: Process the input and get the results
        List<String> results = Main.processInput(input);

        // Assert: Check if the rover ends up at the correct position
        assertEquals("4 2 S", results.get(0)); // Rover 1 ends at (4, 2) facing South
    }

    // Test: Rover tries to move out of bounds but stops at the edge
    @Test
    void testInvalidMove() {
        // Arrange: Set up the input data with a rover trying to move out of bounds
        List<String> input = Arrays.asList(
                "5 5",          // Plateau size: 6x6
                "4 4 N",        // Rover starts at (4, 4) facing North
                "MMMM"          // Rover tries to move out of bounds
        );

        // Act: Process the input and get the results
        List<String> results = Main.processInput(input);

        // Assert: Check if the rover stays within the boundary and does not go out of bounds
        // Rover should move from (4, 4) to (4, 5) and not go out of bounds
        assertEquals("4 5 N", results.get(0)); // Rover 1 stays at (4, 5) after moving north
    }


}
