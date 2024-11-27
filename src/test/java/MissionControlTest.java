import logiclayer.MissionControl;
import logiclayer.Plateau;
import logiclayer.Position;
import logiclayer.Rover;
import logiclayer.enums.CompassDirection;
import logiclayer.enums.Instruction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class MissionControlTest {
    private MissionControl missionControl;
    private Plateau plateau;

    @BeforeEach
    void setUp() {
        plateau = new Plateau(new inputlayer.data.PlateauSize(5, 5));
        missionControl = new MissionControl(plateau);
    }

    @Test
    void testDeployRover_ValidPosition() {
        // Arrange
        Rover rover = new Rover(new inputlayer.data.InitialPosition(1, 2, CompassDirection.N), plateau);

        // Act
        missionControl.deployRover(rover);

        // Assert
        List<Rover> rovers = missionControl.getRovers();
        assertEquals(1, rovers.size());
        assertEquals(rover, rovers.get(0));
    }

    @Test
    void testDeployRover_InvalidPosition() {
        // Arrange
        Rover rover1 = new Rover(new inputlayer.data.InitialPosition(1, 2, CompassDirection.N), plateau);
        Rover rover2 = new Rover(new inputlayer.data.InitialPosition(1, 2, CompassDirection.E), plateau);

        missionControl.deployRover(rover1);

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> missionControl.deployRover(rover2));
        assertEquals("Cannot deploy rover: Position already occupied.", exception.getMessage());
    }

    @Test
    void testExecuteInstructions_MoveWithinBounds() {
        // Arrange
        Rover rover = new Rover(new inputlayer.data.InitialPosition(1, 2, CompassDirection.N), plateau);
        missionControl.deployRover(rover);
        Instruction[] instructions = {Instruction.M, Instruction.L, Instruction.M};

        // Act
        missionControl.executeInstructions(rover, instructions);

        // Assert
        Position finalPosition = rover.getPosition();
        assertEquals(0, finalPosition.getX());
        assertEquals(3, finalPosition.getY());
        assertEquals(CompassDirection.W, finalPosition.getFacing());
    }

    @Test
    void testExecuteInstructions_MoveOutOfBounds() {
        // Arrange
        Rover rover = new Rover(new inputlayer.data.InitialPosition(5, 5, CompassDirection.N), plateau);
        missionControl.deployRover(rover);
        Instruction[] instructions = {Instruction.M};

        // Act
        missionControl.executeInstructions(rover, instructions);

        // Assert
        Position finalPosition = rover.getPosition();
        assertEquals(5, finalPosition.getX()); // No movement
        assertEquals(5, finalPosition.getY()); // No movement
        assertEquals(CompassDirection.N, finalPosition.getFacing());
    }

    @Test
    void testExecuteInstructions_ValidRotation() {
        // Arrange
        Rover rover = new Rover(new inputlayer.data.InitialPosition(1, 2, CompassDirection.N), plateau);
        missionControl.deployRover(rover);
        Instruction[] instructions = {Instruction.L, Instruction.L, Instruction.R};

        // Act
        missionControl.executeInstructions(rover, instructions);

        // Assert
        Position finalPosition = rover.getPosition();
        assertEquals(1, finalPosition.getX());
        assertEquals(2, finalPosition.getY());
        assertEquals(CompassDirection.W, finalPosition.getFacing());
    }

    @Test
    void testExecuteInstructions_InvalidInstruction() {
        // Arrange
        Rover rover = new Rover(new inputlayer.data.InitialPosition(1, 2, CompassDirection.N), plateau);
        missionControl.deployRover(rover);
        Instruction[] instructions = {Instruction.M, null};

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> missionControl.executeInstructions(rover, instructions));
    }

    @Test
    void testIsPositionEmpty() {
        // Arrange
        Rover rover1 = new Rover(new inputlayer.data.InitialPosition(1, 2, CompassDirection.N), plateau);
        Rover rover2 = new Rover(new inputlayer.data.InitialPosition(3, 3, CompassDirection.E), plateau);
        missionControl.deployRover(rover1);
        missionControl.deployRover(rover2);

        // Act
        boolean isEmpty1 = missionControl.isPositionEmpty(2, 2); // Empty position
        boolean isEmpty2 = missionControl.isPositionEmpty(1, 2); // Occupied by rover1

        // Assert
        assertTrue(isEmpty1);
        assertFalse(isEmpty2);
    }
}
