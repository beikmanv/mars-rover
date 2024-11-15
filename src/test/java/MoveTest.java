import inputlayer.data.PlateauSize;
import logiclayer.Plateau;
import logiclayer.Position;
import logiclayer.Rover;
import logiclayer.enums.CompassDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    void testMoveFacingNorth() {
        // Arrange
        PlateauSize plateauSize = new PlateauSize(5, 5);
        Plateau plateau = new Plateau(plateauSize);
        Position position = new Position(0, 0, CompassDirection.N);  // Facing North
        Rover rover = new Rover(position, plateau);

        // Act
        rover.move();  // Move forward in North direction

        // Assert
        assertEquals(1, rover.getPosition().getY());  // Y should increase since we're moving north
        assertEquals(0, rover.getPosition().getX());  // X should remain the same
    }

    @Test
    void testMoveFacingEast() {
        // Arrange
        PlateauSize plateauSize = new PlateauSize(5, 5);
        Plateau plateau = new Plateau(plateauSize);
        Position position = new Position(0, 0, CompassDirection.E);  // Facing East
        Rover rover = new Rover(position, plateau);

        // Act
        rover.move();  // Move forward in East direction

        // Assert
        assertEquals(0, rover.getPosition().getY());  // Y should remain the same
        assertEquals(1, rover.getPosition().getX());  // X should increase since we're moving east
    }

    @Test
    void testMoveFacingSouth() {
        // Arrange
        PlateauSize plateauSize = new PlateauSize(5, 5);
        Plateau plateau = new Plateau(plateauSize);
        Position position = new Position(1, 1, CompassDirection.S);  // Facing South
        Rover rover = new Rover(position, plateau);

        // Act
        rover.move();  // Move forward in South direction

        // Assert
        assertEquals(0, rover.getPosition().getY());  // Y should decrease since we're moving south
        assertEquals(1, rover.getPosition().getX());  // X should remain the same
    }

    @Test
    void testMoveFacingWest() {
        // Arrange
        PlateauSize plateauSize = new PlateauSize(5, 5);
        Plateau plateau = new Plateau(plateauSize);
        Position position = new Position(1, 1, CompassDirection.W);  // Facing West
        Rover rover = new Rover(position, plateau);

        // Act
        rover.move();  // Move forward in West direction

        // Assert
        assertEquals(1, rover.getPosition().getY());  // Y should remain the same
        assertEquals(0, rover.getPosition().getX());  // X should decrease since we're moving west
    }

    @Test
    void testMoveOutOfBounds() {
        // Arrange
        PlateauSize plateauSize = new PlateauSize(5, 5);
        Plateau plateau = new Plateau(plateauSize);
        Position position = new Position(0, 0, CompassDirection.N);  // Facing North at (0, 0)
        Rover rover = new Rover(position, plateau);

        // Act & Assert: Try moving forward, which should go out of bounds
        try {
            rover.move();
            rover.move();  // Try moving forward twice (Y would be out of bounds)
        } catch (IllegalArgumentException e) {
            // Assert the rover does not move out of bounds
            assertEquals(1, rover.getPosition().getY());
            assertEquals(0, rover.getPosition().getX());
        }
    }
}
