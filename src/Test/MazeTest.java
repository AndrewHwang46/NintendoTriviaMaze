package Test;

import Model.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * MazeTest class is used to test the functionality of the Maze class.
 *
 * @author Andrew Hwang
 * @version 1
 */
public class MazeTest {

    /**
     * testingMaze is a Maze object used for testing.
     */
    Maze testingMaze = new Maze("/Resources/Map/ScaleDownMaze.txt");


    /**
     * This test checks if the getMyMazRows method works by
     * comparing the gotten row number with the preset
     * row number which is 4.
     */
    @Test
    void getMyMazeRows() {
        assertEquals(testingMaze.getMyMazeRows(), 4);
    }

    /**
     * This test checks if getMyMazeCols method works by
     * comparing the gotten column number with the preset
     * column number which is 4.
     */
    @Test
    void getMyMazeCols() {
        assertEquals(testingMaze.getMyMazeCols(), 4);
    }

    /**
     * This test checks if getMyNumberOfColumns method works by
     * comparing the gotten column number with the preset
     * column number which is 4.
     */
    @Test
    void getMyNumberOfColumns() {
        assertEquals(testingMaze.getMyNumberOfColumns(), 4);
    }

    /**
     * This test checks if the getMyNumberOfRows method works by
     * comparing the gotten row number with the preset
     * row number which is 4.
     */
    @Test
    void getMyNumberOfRows() {
        assertEquals(testingMaze.getMyNumberOfRows(), 4);
    }

    /**
     * This test checks if getMyEntranceRow method works by
     * comparing the gotten row number with the entrance room's
     * row position 0.
     */
    @Test
    void getMyEntranceRow() {
        assertEquals(testingMaze.getMyEntranceRow(), 0);
    }

    /**
     * This test checks if getMyEntranceColumn method works by
     * comparing the gotten column number with the entrance room's
     * column position 0.
     */
    @Test
    void getMyEntranceColumn() {
        assertEquals(testingMaze.getMyEntranceColumn(), 0);
    }

    /**
     * This test checks if getMyExitRow method works by
     * comparing the gotten row number with the exit room's
     * row position 0.
     */
    @Test
    void getMyExitRow() {
        assertEquals(testingMaze.getMyExitRow(), 3);
    }

    /**
     * This test checks if getMyExitColumn method works by
     * comparing the gotten column number with the exit room's
     * column position 3.
     */
    @Test
    void getMyExitColumn() {
        assertEquals(testingMaze.getMyExitColumn(), 3);
    }
}