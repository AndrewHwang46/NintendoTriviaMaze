package Model;

import java.io.*;

/**
 * GameSaveAndLoad class
 *
 * @author Noah Ogilvie
 */
public final class GameSaveAndLoad {

    private static final String FILE_NAME = "saveGame.ser";


    private static final String MAZE_FILE_NAME = "ScaleDownMaze";



    private Maze myMaze;

    private Player myPlayer;

    private GameSaveAndLoad() {
        //no default constructor, no idea what the maze is originally is.
        //Putting random values to satisfy the constructor. Good luck!
        this.myMaze = new Maze(MAZE_FILE_NAME);
        this.myPlayer = new Player();
    }

    public final void saveGame() {
        try (FileOutputStream savingFile = new FileOutputStream(FILE_NAME);
             ObjectOutputStream out = new ObjectOutputStream(savingFile); ) {
            out.writeObject(myMaze);
            out.writeObject(myPlayer);
        } catch (final IOException e) {
            System.out.println("Error saving game");
        }
    }

    public final void loadGame() {
        try (FileInputStream loadingFile = new FileInputStream(FILE_NAME);
             ObjectInputStream in = new ObjectInputStream(loadingFile); ) {
            myMaze = (Maze) in.readObject();
            myPlayer = (Player) in.readObject();
        } catch (final IOException | ClassNotFoundException e) {
            System.out.println("Error loading game");
        }
    }
}
