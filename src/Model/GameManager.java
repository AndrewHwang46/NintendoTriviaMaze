package Model;

public class GameManager {
    private Maze maze;
    private Player player;

    public GameManager() {
        initializeGame();
    }

    private void initializeGame() {
        maze = new Maze("/Resources/Map/ScaleDownMaze.txt");
        player = Player.getInstance();
    }
    // for Jian
    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze newMaze) {
        this.maze = newMaze;
    }

    public void resetMaze() {
        maze = new Maze("/Resources/Map/ScaleDownMaze.txt");
    }

    public Player getPlayer() {
        return player;
    }
}