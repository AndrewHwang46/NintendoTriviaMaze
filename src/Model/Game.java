package Model;

import View.GamePanel;
import View.Keyboard;

import java.awt.*;
import java.io.Serializable;

public class Game implements Serializable {
    private GameManager gameManager;
    private Keyboard myKeyboard;

    public Game(final GamePanel theGamePanel, Keyboard keyboard, GameManager gameManager) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        this.gameManager = gameManager;
        myKeyboard = keyboard;
    }

    public void update() {
        handlePlayerMovement();
        checkRoomInteraction();
    }

    private void handlePlayerMovement() {
        Player player = gameManager.getPlayer();
        if (myKeyboard.isMyUpPressed()) player.moveUp();
        if (myKeyboard.isMyDownPressed()) player.moveDown();
        if (myKeyboard.isMyLeftPressed()) player.moveLeft();
        if (myKeyboard.isMyRightPressed()) player.moveRight();
    }

    private void checkRoomInteraction() {
        Player player = gameManager.getPlayer();
        Maze maze = gameManager.getMaze();
        int roomX = player.getX() / GameSettings.TILE_SIZE;
        int roomY = player.getY() / GameSettings.TILE_SIZE;
        Room[][] mazeMap = maze.getMyMap();
        if (roomY < mazeMap.length && roomX < mazeMap[roomY].length && mazeMap[roomY][roomX] != null) {
            Room currentRoom = mazeMap[roomY][roomX];
            if (!currentRoom.getRoomMovement()) {
                player.setX((player.getX() / GameSettings.TILE_SIZE) * GameSettings.TILE_SIZE);
                player.setY((player.getY() / GameSettings.TILE_SIZE) * GameSettings.TILE_SIZE);
            }
        }
    }

    public void draw(Graphics2D g2) {
        // Draw maze
        Room[][] mazeMap = gameManager.getMaze().getMyMap();
        for (int y = 0; y < mazeMap.length; y++) {
            for (int x = 0; x < mazeMap[y].length; x++) {
                if (mazeMap[y][x] != null) {
                    g2.drawRect(x * GameSettings.TILE_SIZE, y * GameSettings.TILE_SIZE,
                            GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
                }
            }
        }

        // Draw player
        Player player = gameManager.getPlayer();
        g2.fillOval(player.getX(), player.getY(), GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
    }
    // for Jian
    public Keyboard getMyKeyboard() {
        return myKeyboard;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}