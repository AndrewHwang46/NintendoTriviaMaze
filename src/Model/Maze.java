package Model;

import javax.imageio.ImageIO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Maze implements Serializable {

    private static Maze myMazeSingleton;

    private static final int COLUMN_SIZE = 4;

    private static final int ROW_SIZE = 4;

    private Room[][] myMap;

    private int myNumberOfColumns;

    private int myEntranceRow;

    private int myEntranceColumn;

    private int myExitRow;

    private int myExitColumn;

    private int myNumberOfRows;

    private Tiles[] myTile;

    public Maze(int theColumns, int theRows) {
        myNumberOfColumns = theColumns;
        myNumberOfRows = theRows;
        myMap = new Room[myNumberOfRows][myNumberOfColumns];
        mazeInstantiate();
    }


    public Maze(String theFileName) {
//        InputStream is = Maze.class.getResourceAsStream(theFileName);
//        Scanner fileReader = new Scanner(is);
        myNumberOfColumns = COLUMN_SIZE;
        myNumberOfRows = ROW_SIZE;
        //printTempMaze(fileReader);
        loadMazeFromFile(theFileName);
    }

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

//    public void printTempMaze (Scanner theScanner) {
//        int fileRow, fileCol;
//        fileRow = theScanner.nextInt();
//        fileCol = theScanner.nextInt();
//        char[][] tempMaze = new char[(fileRow * 2) + 1][(fileCol * 2) + 1];
//        String inputRow;
//        theScanner.nextLine();
//        int inputRowCounter = -1;
//        while (theScanner.hasNextLine()) {
//            inputRow = theScanner.nextLine();
//            inputRowCounter++;
//            for (int i = 0; i < inputRow.length(); i++) {
//                tempMaze[inputRowCounter][i] = inputRow.charAt(i);
//            }
//        }
//        loadToMyMaze(tempMaze);
//    }
//
//
//
//    private void loadToMyMaze(char[][] theTempMaze) {
//        int mazeRow = -1;
//        for (int i = 0; i < theTempMaze.length; i++) {
//            boolean rowHasRooms = false;
//            int mazeColCount = -1;
//            for (int j = 0; j < theTempMaze[0].length; j++) {
//                char charGrab = theTempMaze[i][j];
//                switch (charGrab) {
//                    case '□' -> {
//                        if (!rowHasRooms) {
//                            mazeRow++;
//                        }
//                        mazeColCount++;
//                        rowHasRooms = true;
//                        myMap[mazeRow][mazeColCount] = new Room();
//                    }
//                    case '!' -> {
//                        if (!rowHasRooms) {
//                            mazeRow++;
//                        }
//                        rowHasRooms = true;
//                        mazeColCount++;
//                        myMap[mazeRow][mazeColCount] = new Room();
//                        myEntranceRow = mazeRow;
//                        myEntranceColumn = mazeColCount;
//                    }
//                    case '#' -> {
//                        if (!rowHasRooms) {
//                            mazeRow++;
//                        }
//                        rowHasRooms = true;
//                        mazeColCount++;
//                        myMap[mazeRow][mazeColCount] = new Room();
//                        myExitRow = mazeRow;
//                        myExitColumn = mazeColCount;
//                    }
//                }
//            }
//        }
//
//
//
//    }

    private void initializeEmptyMaze() {
        myMap = new Room[myNumberOfRows][myNumberOfColumns];
        for (int i = 0; i < myNumberOfRows; i++) {
            for (int j = 0; j < myNumberOfColumns; j++) {
                myMap[i][j] = new Room();
            }
        }
    }

    public Room[][] getMyMap() {
        return myMap;
    }

    public void mazeInstantiate() {
        for (int i = 0; i < getMyMazeRows(); i++) {
            for (int j = 0; j < getMyMazeCols(); j++) {
                myMap[i][j] = new Room();
            }
        }
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




    public int getMyMazeRows() {
        return myMap.length;
    }

    public int getMyMazeCols() {
        return myMap[0].length;
    }

    public static synchronized void resetMaze(String theFileName){
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

    public static synchronized Maze getMazeSingleton(String theFileName){
        if (myMazeSingleton == null) {
            myMazeSingleton = new Maze(theFileName);
        }

        return myMazeSingleton;
    }

    public static synchronized void setMazeSingleton(Maze theMazeSingleton) {
        myMazeSingleton = theMazeSingleton;
    }

    public int getMyEntranceRow() {
            return myEntranceRow;
    }

    public int getMyEntranceColumn() {
        return myEntranceColumn;
    }

    public int getMyExitRow() {
        return myExitRow;
    }

    public int getMyExitColumn() {
        return myExitColumn;
    }


}

