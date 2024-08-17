package Test;

import Model.GameManager;
import Model.Maze;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    void getMaze() {
        GameManager testingGM = new GameManager();
        assertNotNull(testingGM.getMaze());
    }

    @Test
    void setMaze() {
        Maze testingMaze = new Maze("/Resources/Map/ScaleDownMaze.txt");
        GameManager testingGM = new GameManager();
        testingGM.setMaze(testingMaze);
        assertEquals(testingGM.getMaze(), testingMaze);
    }

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

    @Test
    void getPlayer() {
        GameManager testingGM = new GameManager();
        assertNotNull(testingGM.getPlayer());
    }
}