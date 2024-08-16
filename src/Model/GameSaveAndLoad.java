package Model;

import java.io.*;

public class GameSaveAndLoad {
    private static final String FILE_NAME = "saveGame.ser";
    private Maze myMaze;
    private Player myPlayer;

    public GameSaveAndLoad() {
        // Default constructor
    }

    public GameSaveAndLoad(Maze maze, Player player) {
        this.myMaze = maze;
        this.myPlayer = player;
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
            myMaze = (Maze) in.readObject();
            myPlayer = (Player) in.readObject();
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
    // for Jian
}

///*
// * T CSS 360 - Summer 2024
// * Nintendo Trivia Maze
// */
//package Model;
//
//import java.io.*;
//
///**
// * GameSaveAndLoad class uses serialization and deserialization
// * for the save and load.
// *
// * @author Noah Ogilvie
// * @author Andrew Hwang
// * @version 3.0
// */
//public final class GameSaveAndLoad {
//
//    /**
//     * String constant that is the game's load and save
//     * file name.
//     */
//    private static final String FILE_NAME = "saveGame.ser";
//
//    /**
//     * String constant that is a file for the Maze constructor.
//     */
//    private static final String MAZE_FILE_NAME = "Resources/Map/ScaleDownMaze.txt";
//
//    /**
//     * mySaved is a boolean private field.
//     */
//    private Boolean mySaved;
//
//    /**
//     * myMaze is a Maze private field.
//     */
//    private Maze myMaze;
//
//    /**
//     * myPlayer is a Player private field.
//     */
//    private Player myPlayer;
//
//    /**
//     * GameSaveAndLoad() constructor initializes the global private fields.
//     */
//    public GameSaveAndLoad() {
//        //mySaved = false;
//        this.myMaze = new Maze(MAZE_FILE_NAME);
//        this.myPlayer = Player.getInstance();
//    }
//
//    /**
//     * saveGame() uses serialization to save the Maze and Player.
//     * @return whether if the game has happened to be saved or not.
//     */
//    public boolean saveGame() {
//        boolean gameSaved = false;
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
//            out.writeObject(myMaze);
//            out.writeObject(myPlayer);
//            gameSaved = true;
//            mySaved = true;
//        } catch (IOException e) {
//            System.err.println("Error saving game: " + e.getMessage());
//        }
//        return gameSaved;
//    }
//
//    /**
//     * loadGame() uses deserialization to load the MAze and Player.
//     * @return whether if the game has happened to load or not.
//     */
//    public boolean loadGame() {
//        boolean gameLoaded = false;
//        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
//            Maze loadedMaze = (Maze) in.readObject();
//            Player loadedPlayer = (Player) in.readObject();
//
//            myMaze.setSingletonToMaze(loadedMaze);
//            myMaze.getMyMap();
//            Player.getInstance().updateState(loadedPlayer);
//
//            this.myMaze = loadedMaze;
//            this.myPlayer = Player.getInstance();
//
//            gameLoaded = true;
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error loading game: " + e.getMessage());
//        }
//        return gameLoaded;
//    }
//
//    /**
//     * getMaze() gets the Maze object.
//     * @return the Maze object.
//     */
//    public Maze getMaze() {
//        return myMaze;
//    }
//
//    /**
//     * getPlayer() gets the Player object.
//     * @return the Player object.
//     */
//    public Player getPlayer() {
//        return myPlayer;
//    }
//
//    /**
//     * getSaved() gets whether if the game got saved or not.
//     * @return the boolean value of mySaved.
//     */
//    public Boolean getSaved() {
//        return mySaved;
//    }
//}
