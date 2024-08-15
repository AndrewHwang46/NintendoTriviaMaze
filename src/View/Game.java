package View;

import Model.GameSettings;
import Model.Maze;
import Model.Player;
import Model.Room;

import java.awt.*;
import java.io.Serializable;

public class Game implements Serializable {
    private Player myPlayer;
    private Maze myMaze;
    private Keyboard myKeyboard;

    public Game(final GamePanel theGamePanel, Keyboard keyboard) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        myPlayer = Player.getInstance();
        initializeMaze();
        myKeyboard = keyboard;
    }

    public void update() {
        handlePlayerMovement();
        checkRoomInteraction();
    }

    private void initializeMaze() {
        try {
            myMaze = Maze.getMazeSingleton("/View/Sprites/ScaleDownMaze.txt");
        } catch (Exception e) {
            System.err.println("Error initializing maze: " + e.getMessage());
            e.printStackTrace();
            // Handle the error appropriately, maybe set a default maze or exit the game
        }
    }

    private void handlePlayerMovement() {
        if (myKeyboard.isMyUpPressed()) myPlayer.moveUp();
        if (myKeyboard.isMyDownPressed()) myPlayer.moveDown();
        if (myKeyboard.isMyLeftPressed()) myPlayer.moveLeft();
        if (myKeyboard.isMyRightPressed()) myPlayer.moveRight();
    }

    private void checkRoomInteraction() {
        int roomX = myPlayer.getX() / GameSettings.TILE_SIZE;
        int roomY = myPlayer.getY() / GameSettings.TILE_SIZE;
        Room[][] mazeMap = myMaze.getMyMap();
        if (roomY < mazeMap.length && roomX < mazeMap[roomY].length && mazeMap[roomY][roomX] != null) {
            Room currentRoom = mazeMap[roomY][roomX];
            if (!currentRoom.getRoomMovement()) {
                myPlayer.setX((myPlayer.getX() / GameSettings.TILE_SIZE) * GameSettings.TILE_SIZE);
                myPlayer.setY((myPlayer.getY() / GameSettings.TILE_SIZE) * GameSettings.TILE_SIZE);
            }
        }
    }

    public void draw(Graphics2D g2) {
        // Draw maze
        Room[][] mazeMap = myMaze.getMyMap();
        for (int y = 0; y < mazeMap.length; y++) {
            for (int x = 0; x < mazeMap[y].length; x++) {
                if (mazeMap[y][x] != null) {
                    g2.drawRect(x * GameSettings.TILE_SIZE, y * GameSettings.TILE_SIZE,
                            GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
                }
            }
        }

        // Draw player
        g2.fillOval(myPlayer.getX(), myPlayer.getY(), GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
    }

    public Keyboard getMyKeyboard() {
        return myKeyboard;
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public Maze getMyMaze() {
        return myMaze;
    }


}