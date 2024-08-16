package Model;

import java.io.*;

public class Testing {

    private static final String MAZE_FILE_NAME = "ScaleDownMaze.txt";

    private static final String SAVED_FILE_NAME = "saveGame.ser";

    private static Maze myMaze;

    private static Player myPlayer;

    private Testing() {}

    public static void main(String[] args) {
        myMaze = new Maze(MAZE_FILE_NAME);
        myPlayer = Player.getInstance();
        saveGame();
        loadGame();
    }

    private static void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVED_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(myMaze);
            oos.writeObject(myPlayer);
            Player.getInstance().updateState(myPlayer);
            System.out.println("Saved Game: " + myMaze);
            System.out.println("Saved Player: " + myPlayer);
        } catch (final IOException e) {
            System.out.println("Unable to save: " + e.getMessage()
                             + " " + e.getCause());
        }
    }

    private static void loadGame() {
        try(FileInputStream fos = new FileInputStream(SAVED_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(fos)) {
            Maze loadedMaze = (Maze) in.readObject();
            Player loadedPlayer = (Player) in.readObject();
            Player.getInstance().updateState(loadedPlayer);
            System.out.println("Loaded Game: " + loadedMaze);
            System.out.println("Loaded Player: " + loadedPlayer);
        } catch (final IOException |  ClassNotFoundException e) {
            System.out.println("Unable to save: " + e.getMessage()
                    + " " + e.getCause());
        }
    }
}
