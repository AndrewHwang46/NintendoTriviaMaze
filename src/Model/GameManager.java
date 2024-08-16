package Model;

/**
 * This class is responsible for managing the core game state and logic.
 * It acts as a central point of control for the game's model components, including
 * the maze and the player.
 *
 * @author Andrew Hwang
 * @version 1
 */
public class GameManager {
    private Maze maze;
    private Player player;

    /**
     * This method constructs a new GameManager and initializes the game state.
     */
    public GameManager() {
        initializeGame();
    }

    /**
     * This method initializes the game by creating a new maze and player.
     */
    private void initializeGame() {
        maze = new Maze("/Resources/Map/ScaleDownMaze.txt");
        player = Player.getInstance();
    }

    public Maze getMaze() {
        return maze;
    }

    /**
     * Gets the current maze.
     *
     */
    public void setMaze(Maze theNewMaze) {
        this.maze = theNewMaze;
    }

    /**
     * This method resets the maze by creating a new maze.
     */
    public void resetMaze() {
        maze = new Maze("/Resources/Map/ScaleDownMaze.txt");
    }

    /**
     * This method returns the player.
     * @return is Player object and is the player that is store in this class.
     */
    public Player getPlayer() {
        return player;
    }
}