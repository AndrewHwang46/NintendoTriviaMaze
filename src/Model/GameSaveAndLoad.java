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

    /**
     * This method is a default constructor for GameSaveAndLoad.
     */
    public GameSaveAndLoad() {
        //mySaved = false;
        this.myMaze = new Maze(MAZE_FILE_NAME);
        this.myPlayer = Player.getInstance();
    }

    /**
     * This method saves the current game state to a file.
     * This method serializes the current maze and player objects and writes them to a file.
     * The file name is defined by the FILE_NAME constant.
     *
     * @return true if the game was successfully saved, false otherwise
     */
    public boolean saveGame() {
        boolean gameSaved = false;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(myMaze);
            out.writeObject(myPlayer);
            gameSaved = true;
            mySaved = true;
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
        return gameSaved;
    }

    /**
     * This method loads a previously saved game state from a file.
     * This method deserializes the maze and player objects from a file and updates
     * the current game state with the loaded data. The file name is defined by the
     * FILE_NAME constant. If the game is successfully loaded, it updates the singleton
     * instances of Maze and Player.
     *
     * @return true if the game was successfully loaded, false otherwise
     */
    public boolean loadGame() {
        boolean gameLoaded = false;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Maze loadedMaze = (Maze) in.readObject();
            Player loadedPlayer = (Player) in.readObject();

            Maze.setMazeSingleton(loadedMaze);
            Player.getInstance().updateState(loadedPlayer);

            this.myMaze = loadedMaze;
            this.myPlayer = Player.getInstance();

            gameLoaded = true;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
        }
        return gameLoaded;
    }

    /**
     * This method returns the current Maze object.
     * @return is a maze and is the current Maze object.
     */
    public Maze getMaze() {
        return myMaze;
    }

    /**
     * This method returns the current Player object.
     * @return is a player and is the current Player object.
     */
    public Player getPlayer() {
        return myPlayer;
    }

    /**
     * This method returns a boolean showing whether the game has been saved.
     * @return is a boolean and is the save state.
     */
    public Boolean getSaved() {
        return mySaved;
    }
}
