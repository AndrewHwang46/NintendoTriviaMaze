package Model;

import java.io.*;

/**
 * GameSaveAndLoad class
 *
 * @author Noah Ogilvie
 */
public final class GameSaveAndLoad {

    private static final String FILE_NAME = "saveGame.ser";
    private static final String MAZE_FILE_NAME = "ScaleDownMaze.txt";

    private Maze myMaze;
    private Player myPlayer;

    public GameSaveAndLoad() {
        this.myMaze = new Maze(MAZE_FILE_NAME);
        this.myPlayer = Player.getInstance();
    }

    public boolean saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(myMaze);
            out.writeObject(myPlayer);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            return false;
        }
    }

    public boolean loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Maze loadedMaze = (Maze) in.readObject();
            Player loadedPlayer = (Player) in.readObject();

            // Update the singleton instances
            Maze.setMazeSingleton(loadedMaze);
            Player.getInstance().updateState(loadedPlayer);

            // Update local references
            this.myMaze = loadedMaze;
            this.myPlayer = Player.getInstance();

            return true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return false;
        }
    }

    public Maze getMaze() {
        return myMaze;
    }

    public Player getPlayer() {
        return myPlayer;
    }
}
