/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Model;

import java.io.*;

/**
 * GameSaveAndLoad class handles with the serialization of the game.
 *
 * @author Noah Ogilvie
 */
public final class GameSaveAndLoad {

    /**
     * A String constant that holds the serializable file name.
     */
    private static final String FILE_NAME = "saveGame.ser";

    /**
     * A String constant that holds the initialization parameter value for the Maze class.
     */
    private static final String MAZE_FILE_NAME = "JIAN_MAZE_INPUT.txt";

    /**
     * myMaze is a Maze private field.
     */
    private Maze myMaze;

    /**
     * myPlayer is a Player private field.
     */
    private Player myPlayer;

    /**
     * GameSaveAndLoad() is a public constructor, it initializes the private fields.
     */
    private GameSaveAndLoad() {
        //no default constructor, no idea what the maze is originally is.
        //Putting random values to satisfy the constructor. Good luck!
        this.myMaze = new Maze(MAZE_FILE_NAME);
        this.myPlayer = new Player();
    }

    /**
     * saveGame() method serializes the game into a file.
     */
    public void saveGame() {
        try (FileOutputStream savingFile = new FileOutputStream(FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(savingFile)) {
            out.writeObject(myMaze);
            out.writeObject(myPlayer);
        } catch (final IOException e) {
            System.out.println("Error saving game" + e.getMessage());
        }
    }

    /**
     * loadGame() method deserializes the file and the game data is returned.
     */
    public void loadGame() {
        try (FileInputStream loadingFile = new FileInputStream(FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(loadingFile)) {
            myMaze = (Maze) in.readObject();
            myPlayer = (Player) in.readObject();
        } catch (final IOException | ClassNotFoundException e) {
            System.out.println("Error loading game" + e.getMessage());
        }
    }
}
