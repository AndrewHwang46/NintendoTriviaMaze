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

    Maze testingMaze= new Maze("/Resources/Map/ScaleDownMaze.txt");


    @Test
    void getMyMazeRows() {
        assertEquals(testingMaze.getMyMazeRows(), 4);
    }

    @Test
    void getMyMazeCols() {
        assertEquals(testingMaze.getMyMazeCols(), 4);
    }

    @Test
    void getMyNumberOfColumns() {
        assertEquals(testingMaze.getMyNumberOfColumns(), 4);
    }

    @Test
    void getMyNumberOfRows() {
        assertEquals(testingMaze.getMyNumberOfRows(), 4);
    }

    @Test
    void getMyEntranceRow() {
        assertEquals(testingMaze.getMyEntranceRow(), 0);
    }

    @Test
    void getMyEntranceColumn() {
        assertEquals(testingMaze.getMyEntranceColumn(), 0);
    }

    @Test
    void getMyExitRow() {
        assertEquals(testingMaze.getMyExitRow(), 3);
    }

    @Test
    void getMyExitColumn() {
        assertEquals(testingMaze.getMyExitColumn(), 3);
    }
}