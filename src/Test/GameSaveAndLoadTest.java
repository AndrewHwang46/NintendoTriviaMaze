/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Test;

import Model.GameSaveAndLoad;
import Model.Maze;
import Model.Player;
import org.junit.jupiter.api.BeforeEach;

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
}
