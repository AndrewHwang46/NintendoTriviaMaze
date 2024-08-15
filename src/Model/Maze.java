/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Model;

import javax.imageio.ImageIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * This class create the maze class where all the rooms are stored.
 *
 * @author Anderw Hwang
 * @version 1
 */
public class Maze implements Serializable {

    /**
     * myMazeSingleton is an instance Single instance of maze.
     */
    private static Maze myMazeSingleton;

    /**
     * COLUMN_SIZE is the max size of the column.
     */
    private static final int COLUMN_SIZE = 4;

    /**
     * ROW_SIZE is the max size of the row.
     */
    private static final int ROW_SIZE = 4;

    /**
     * myMap is a two-d matrix where all the rooms in the maze are stored.
     */
    private Room[][] myMap;

    /**
     * myNumberOfColumns is the number of columns.
     */
    private int myNumberOfColumns;

    /**
     *myEntranceRow is the row coordinate of the entrance.
     */
    private int myEntranceRow;

    /**
     *myEntranceColumn is the column coordinate of the entrance.
     */
    private int myEntranceColumn;

    /**
     *myExitRow is the row coordinate of the exit.
     */
    private int myExitRow;

    /**
     *myExitColumn is the column coordinate of the exit.
     */
    private int myExitColumn;

    /**
     *myNumberOfRows is the number of rows.
     */
    private int myNumberOfRows;

    /**
     *myTile is a list that holds different tiles.
     */
    private Tiles[] myTile;

    /**
     * This a constructor of Maze if int are given.
     * @param theColumns is an int and is the number of columns given.
     * @param theRows is an int and is the number of rows given.
     */
    public Maze(int theColumns, int theRows) {
        myNumberOfColumns = theColumns;
        myNumberOfRows = theRows;
        myMap = new Room[myNumberOfRows][myNumberOfColumns];
        initializeEmptyMaze();
    }

    /**
     * This is the maze constructor when a file is passed in.
     * @param theFileName is a string and the name of the File.
     */
    public Maze(String theFileName) {
//        InputStream is = Maze.class.getResourceAsStream(theFileName);
//        Scanner fileReader = new Scanner(is);
        myNumberOfColumns = COLUMN_SIZE;
        myNumberOfRows = ROW_SIZE;
        //printTempMaze(fileReader);
        loadMazeFromFile(theFileName);
    }

    /**
     *This method loads a maze from a file resource.
     *This method reads a maze configuration from a file, constructs a temporary
     *representation, and then creates the final maze structure. If any errors occur
     *during the loading process, it falls back to initializing an empty maze.
     *
     * @param theFileName the name of the file resource containing the maze configuration
     */
    private void loadMazeFromFile(String theFileName) {
        try (InputStream is = Maze.class.getResourceAsStream(theFileName);
             Scanner fileReader = new Scanner(is)) {

            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + theFileName);
            }

            int fileRow = fileReader.nextInt();
            int fileCol = fileReader.nextInt();
            char[][] tempMaze = new char[(fileRow * 2) + 1][(fileCol * 2) + 1];

            fileReader.nextLine(); // consume the newline
            int inputRowCounter = 0;
            while (fileReader.hasNextLine() && inputRowCounter < tempMaze.length) {
                String inputRow = fileReader.nextLine();
                for (int i = 0; i < inputRow.length() && i < tempMaze[inputRowCounter].length; i++) {
                    tempMaze[inputRowCounter][i] = inputRow.charAt(i);
                }
                inputRowCounter++;
            }

            createMazeFromTemp(tempMaze);
        } catch (FileNotFoundException e) {
            System.err.println("Error: Maze file not found - " + e.getMessage());
            initializeEmptyMaze();
        } catch (IOException e) {
            System.err.println("Error reading maze file: " + e.getMessage());
            initializeEmptyMaze();
        } catch (Exception e) {
            System.err.println("Unexpected error loading maze: " + e.getMessage());
            initializeEmptyMaze();
        }
    }

    /**
     * This method creates the final maze structure from a temporary character array representation.
     * This method interprets the characters in the temporary maze to create Room objects
     * and set the entrance and exit positions.
     * The characters are interpreted as follows:
     * - '□': A regular room
     * - '!': The entrance room
     * - '#': The exit room
     *
     * @param theTempMaze a 2D character array representing the temporary maze structure
     */
    private void createMazeFromTemp(char[][] theTempMaze) {
        int mazeRow = -1;
        for (int i = 0; i < theTempMaze.length; i++) {
            boolean rowHasRooms = false;
            int mazeColCount = -1;
            for (int j = 0; j < theTempMaze[0].length; j++) {
                char charGrab = theTempMaze[i][j];
                switch (charGrab) {
                    case '□':
                        if (!rowHasRooms) {
                            mazeRow++;
                        }
                        rowHasRooms = true;
                        mazeColCount++;
                        myMap[mazeRow][mazeColCount] = new Room();
                        break;
                    case '!':
                        if (!rowHasRooms) {
                            mazeRow++;
                        }
                        rowHasRooms = true;
                        mazeColCount++;
                        myMap[mazeRow][mazeColCount] = new Room();
                        myEntranceRow = mazeRow;
                        myEntranceColumn = mazeColCount;
                        break;
                    case '#':
                        if (!rowHasRooms) {
                            mazeRow++;
                        }
                        rowHasRooms = true;
                        mazeColCount++;
                        myMap[mazeRow][mazeColCount] = new Room();
                        myExitRow = mazeRow;
                        myExitColumn = mazeColCount;
                        break;
                }
            }
        }
    }

    /**
     * This method initializes an empty 4x4 maze.
     */
    private void initializeEmptyMaze() {
        myMap = new Room[myNumberOfRows][myNumberOfColumns];
        for (int i = 0; i < myNumberOfRows; i++) {
            for (int j = 0; j < myNumberOfColumns; j++) {
                myMap[i][j] = new Room();
            }
        }
    }

    /**
     * This method returns the maze matrix.
     * @return is a two-d matrix and is the maze.
     */
    public Room[][] getMyMap() {
        return myMap;
    }


    //FINISH TILES
    public void getTileImage() {
        for (int i = 0; i < 16; i++) {
            myTile[i] = new Tiles();
        }
        try {
            myTile[0].setTileImage(ImageIO.read(getClass().getResourceAsStream("View/Sprites/Tiles/Beige.png")));
            myTile[0].setMyCollision(true);
            myTile[1].setTileImage(ImageIO.read(getClass().getResourceAsStream("View/Sprites/Tiles/Brown.png")));
            myTile[2].setMyTileImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_wallV3.png")));
    //          myTile[3].setMyTileImage(ImageIO.read());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public int getMyMazeRows() {
        return myMap.length;
    }

    /**
     *
     * @return
     */
    public int getMyMazeCols() {
        return myMap[0].length;
    }

    /**
     *
     * @param theFileName
     */
    public static synchronized void resetMaze(String theFileName){
        myMazeSingleton = new Maze(theFileName);
    }

    /**
     *
     * @return
     */
    public int getMyNumberOfColumns() {
        return myNumberOfColumns;
    }

    /**
     *
     * @return
     */
    public int getMyNumberOfRows() {
        return myNumberOfRows;
    }

    /**
     *
     * @param myNumberOfColumns
     */
    public void setMyNumberOfColumns(int myNumberOfColumns) {
        this.myNumberOfColumns = myNumberOfColumns;
    }

    /**
     *
     * @param myNumberOfRows
     */
    public void setMyNumberOfRows(int myNumberOfRows) {
        this.myNumberOfRows = myNumberOfRows;
    }


    /**
     *This method gets the single instance of maze.
     * @param theFileName is a string and is the file name loading the maze.
     * @return is a Maze and is the single instance of maze.
     */
    public static synchronized Maze getMazeSingleton(String theFileName){
        if (myMazeSingleton == null) {
            myMazeSingleton = new Maze(theFileName);
        }

        return myMazeSingleton;
    }

    /**
     *This method sets the single instance of maze.
     * @param theMazeSingleton is a maze and is the single instance of maze that is
     *                         setting myMazeSingleton.
     */
    public static synchronized void setMazeSingleton(Maze theMazeSingleton) {
        myMazeSingleton = theMazeSingleton;
    }

    /**
     * This method gets the entrance row.
     * @return is an int and is the entrance row.
     */
    public int getMyEntranceRow() {
            return myEntranceRow;
    }

    /**
     * This method gets the entrance column.
     * @return is an int and is the entrance column.
     */
    public int getMyEntranceColumn() {
        return myEntranceColumn;
    }

    /**
     * This method gets the exit row.
     * @return is an int and is the exit row.
     */
    public int getMyExitRow() {
        return myExitRow;
    }

    /**
     * This method gets the exit column.
     * @return is an int and is the exit column.
     */
    public int getMyExitColumn() {
        return myExitColumn;
    }


}

