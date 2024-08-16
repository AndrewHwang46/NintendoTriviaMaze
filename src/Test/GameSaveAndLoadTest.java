/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Test;

import Model.GameSaveAndLoad;
import Model.Maze;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * GameSaveAndLoadTest class tests the GameAndSaveLoad
 * class functionality
 *
 * @author Noah Ogilvie
 * @version 1.0
 */
final class GameSaveAndLoadTest {

    /**
     * A global private constant containing the file for the
     * Maze constructor.
     */
    private static final String MAZE_FILE_NAME = "ScaleDownMaze.txt";

    private static final String FILE_NAME = "saveGame.ser";

    /**
     * myMaze is a Maze private field.
     */
    private Maze myMaze;

    /**
     * myPlayer is a Player private field.
     */
    private Player myPlayer;

    /**
     * myGameSaveAndLoad is a GameSaveAndLoad private field.
     */
    private GameSaveAndLoad myGameSaveAndLoad;

    /**
     * setUp() method initializes the global private fields.
     */
    @BeforeEach
    void setUp() {
        myGameSaveAndLoad = new GameSaveAndLoad();
        myMaze = new Maze(MAZE_FILE_NAME);
        myPlayer = Player.getInstance();
    }

    /**
     * testSaveGame() method checks if the game is saved.
     */
    @Test
    void testSaveGame() {
        boolean result = myGameSaveAndLoad.saveGame();
        assertTrue(result);
    }

    /**
     * testLoadGame() method checks if the game is loaded.
     */
    @Test
    void testLoadGame() {
        boolean result = myGameSaveAndLoad.loadGame();
        assertTrue(result);
    }

    /**
     * testPlayerFromLoad() method checks if the Player objects are the same.
     */
    @Test
    void testPlayerFromLoad() {
        myGameSaveAndLoad.saveGame();
        myGameSaveAndLoad.loadGame();
        assertEquals(myPlayer, myGameSaveAndLoad.getPlayer());
    }

    /**
     * testMazeFromLoad() method checks if the Maze objects are the same.
     */
    @Test
    void testMazeFromLoad() {
        myGameSaveAndLoad.saveGame();
        myGameSaveAndLoad.loadGame();
        assertEquals(myMaze, myGameSaveAndLoad.getMaze());
    }
}
