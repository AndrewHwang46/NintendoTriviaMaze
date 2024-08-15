/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Model;

import java.io.*;

/**
 * GameSaveAndLoad class
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
        mySaved = true;
        boolean gameSaved = false;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(myMaze);
            out.writeObject(myPlayer);
            gameSaved = true;
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            gameSaved = false;
        }
        return gameSaved;
    }

    public boolean loadGame() {
        boolean gameLoaded = false;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Maze loadedMaze = (Maze) in.readObject();
            Player loadedPlayer = (Player) in.readObject();

            // Update the singleton instances
            Maze.setMazeSingleton(loadedMaze);
            Player.getInstance().updateState(loadedPlayer);

            // Update local references
            this.myMaze = loadedMaze;
            this.myPlayer = Player.getInstance();

            gameLoaded = true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            gameLoaded = false;
        }
        return gameLoaded;
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
