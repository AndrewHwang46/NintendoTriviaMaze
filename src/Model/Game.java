package Model;

import View.GamePanel;
import View.Keyboard;

import java.awt.*;
import java.io.Serializable;

/**
 * This class manages the gameplay loop, including player movement, room interactions,
 * and rendering of the game state.
 *
 * @author Andrew Hwang
 * @version 1
 */
public class Game implements Serializable {
    private GameManager myGameManager;
    private Keyboard myKeyboard;

    /**
     * This method constructs a new Game instance.
     * @param theGamePanel is a GamePanel object for Holding the game.
     * @param theKeyboard is a Keyboard object for handling user input.
     * @param theGameManager is a GameManager object for managing game state.
     */
    public Game(final GamePanel theGamePanel, Keyboard theKeyboard, GameManager theGameManager) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        myGameManager = theGameManager;
        myKeyboard = theKeyboard;
    }

    /**
     * This method updates the game state based on user input and game rules.
     */
    public void update() {
        handlePlayerMovement();
        checkRoomInteraction();
    }

    /**
     * This method handles player movement based on keyboard input.
     */
    private void handlePlayerMovement() {
        Player player = myGameManager.getPlayer();
        if (myKeyboard.isMyUpPressed()) player.moveUp();
        if (myKeyboard.isMyDownPressed()) player.moveDown();
        if (myKeyboard.isMyLeftPressed()) player.moveLeft();
        if (myKeyboard.isMyRightPressed()) player.moveRight();
    }

    /**
     * This method checks and handles player interaction with the current room.
     */
    private void checkRoomInteraction() {
        Player player = myGameManager.getPlayer();
        Maze maze = myGameManager.getMaze();
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

    /**
     * This method draws the current game state.
     *
     * @param theG2 is a Graphics and is what is used to draw.
     */
    public void draw(Graphics2D theG2) {
        // Draw maze
        Room[][] mazeMap = myGameManager.getMaze().getMyMap();
        for (int y = 0; y < mazeMap.length; y++) {
            for (int x = 0; x < mazeMap[y].length; x++) {
                if (mazeMap[y][x] != null) {
                    theG2.drawRect(x * GameSettings.TILE_SIZE, y * GameSettings.TILE_SIZE,
                            GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
                }
            }
        }
        Player player = myGameManager.getPlayer();
        theG2.fillOval(player.getX(), player.getY(), GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
    }

    /**
     * This method returns the Keyboard object.
     * @return is a keyboard.
     */
    public Keyboard getMyKeyboard() {
        return myKeyboard;
    }

    /**
     * This method set the given game manager.
     * @param theGameManager is a GameManager object wanting to be set.
     */
    public void setMyGameManager(GameManager theGameManager) {
        myGameManager = theGameManager;
    }
}