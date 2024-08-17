package Test;

import Model.GameManager;
import Model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test the Game Manager class.
 *
 * @author Andrew Hwang
 * @version 1
 */
class GameManagerTest {

    /**
     * This test checks if the getMaze properly gets
     * the maze by checking if the method does
     * not return null.
     */
    @Test
    void getMaze() {
        GameManager testingGM = new GameManager();
        assertNotNull(testingGM.getMaze());
    }

    /**
     * This test checks if the setMaze method properly
     * sets the maze by checking if the testedMaze
     * equals the maze that was used to set testedMaze.
     */
    @Test
    void setMaze() {
        Maze testingMaze = new Maze("/Resources/Map/ScaleDownMaze.txt");
        GameManager testingGM = new GameManager();
        testingGM.setMaze(testingMaze);
        assertEquals(testingGM.getMaze(), testingMaze);
    }

    /**
     * This test checks if the resetMaze method
     * properly resets the maze. By resetting
     * a maze and comparing the number of columns
     * to a secondary maze with a column size of 3.
     */
    @Test
    void resetMaze() {
        GameManager testingGM = new GameManager();
        Maze testingMaze = testingGM.getMaze();
        testingMaze.setMyNumberOfColumns(3);
        testingGM.resetMaze();
        Maze restartMaze = testingGM.getMaze();
        assertNotEquals(testingMaze.getMyNumberOfColumns(),
                restartMaze.getMyNumberOfColumns());
    }

    /**
     * This test checks if the getPlayer properly gets
     * the player by checking if the method does
     * not return null.
     */
    @Test
    void getPlayer() {
        GameManager testingGM = new GameManager();
        assertNotNull(testingGM.getPlayer());
    }
}