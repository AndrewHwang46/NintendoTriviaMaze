/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Model;

import java.io.*;

/**
 * GameSaveAndLoad class uses serialization and
 * deserialization in order to save and load the game.
 *
 * @author Noah Ogilvie
 * @author Andrew Hwang
 * @version 2.0
 */
public final class GameSaveAndLoad {

    private static final String FILE_NAME = "saveGame.ser";
    private static final String MAZE_FILE_NAME = "ScaleDownMaze.txt";

    private Boolean mySaved;
    private Maze myMaze;
    private Player myPlayer;

    public GameSaveAndLoad() {
        mySaved = false;
        this.myMaze = new Maze(MAZE_FILE_NAME);
        this.myPlayer = Player.getInstance();
    }

    public boolean saveGame() {
        boolean saved = true;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(myMaze);
            out.writeObject(myPlayer);
            mySaved = saved;
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            saved =  false;
        }
        return saved;
    }

    public boolean loadGame() {
        boolean load = true;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Maze loadedMaze = (Maze) in.readObject();
            Player loadedPlayer = (Player) in.readObject();

            // Update the singleton instances
            Maze.setMazeSingleton(loadedMaze);
            Player.getInstance().updateState(loadedPlayer);

            // Update local references
            this.myMaze = loadedMaze;
            this.myPlayer = Player.getInstance();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            load = false;
        }
        return load;
    }

    public Maze getMaze() {
        return myMaze;
    }

    public Player getPlayer() {
        return myPlayer;
    }
    public Boolean getSaved() {
        return mySaved;
    }
}
