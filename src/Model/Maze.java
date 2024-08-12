package Model;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

import java.io.InputStream;

public class Maze implements Serializable {

    private static Maze myMazeSingleton;

    private static final int COLUMN_SIZE = 80;

    private static final int ROW_SIZE = 72;

    private Room[][] myMap;

    private int myNumberOfColumns;

    private int myNumberOfRows;


    public Maze(String theFileName) {
        myNumberOfColumns = COLUMN_SIZE;
        myNumberOfRows = ROW_SIZE;
        loadMaze(theFileName);
    }

    public void loadMaze(String theFileName) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(theFileName);
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + theFileName);
            }

            Scanner scanner = new Scanner(inputStream);

            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    sb.append(line);
                    myNumberOfRows++;
                }
            }
            myNumberOfColumns = sb.length() / myNumberOfRows;

            scanner.close();
            inputStream = getClass().getResourceAsStream(theFileName);
            scanner = new Scanner(inputStream);


            myMap = new Room[myNumberOfRows][myNumberOfColumns];

            for (int row = 0; row < myNumberOfRows; row++) {
                String line = scanner.nextLine().trim();
                for (int col = 0; col < myNumberOfColumns; col++) {
                    char roomType = line.charAt(col);
                    if (roomType != '0') {  // Assuming '0' represents an empty space
                        myMap[row][col] = new Room();
                        myMap[row][col].setRandomDoorInRoom();
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error loading maze: " + e.getMessage());
            throw new RuntimeException("Failed to load maze", e);
        }
    }

    public Room[][] getMyMap() {
        return myMap;
    }


    void mazeInstantiate() {
        for (int i = 0; i < getMyMazeRows(); i++) {
            for (int j = 0; j < getMyMazeCols(); j++) {
                myMap[i][j] = new Room();
            }
        }
    }



    public int getMyMazeRows() {
        return myMap.length;
    }

    public int getMyMazeCols() {
        return myMap[0].length;
    }

    public static synchronized void resetMaze(String theFileName) {
        myMazeSingleton = new Maze(theFileName);
    }

    public int getMyNumberOfColumns() {
        return myNumberOfColumns;
    }

    public int getMyNumberOfRows() {
        return myNumberOfRows;
    }

    public void setMyNumberOfColumns(int myNumberOfColumns) {
        this.myNumberOfColumns = myNumberOfColumns;
    }

    public void setMyNumberOfRows(int myNumberOfRows) {
        this.myNumberOfRows = myNumberOfRows;
    }

    public static synchronized Maze getMazeSingleton(String theFileName) {
        if (myMazeSingleton == null) {
            myMazeSingleton = new Maze(theFileName);
        }
        return myMazeSingleton;
    }

    public static synchronized void setMazeSingleton(Maze theMazeSingleton) {
        myMazeSingleton = theMazeSingleton;
    }


}
