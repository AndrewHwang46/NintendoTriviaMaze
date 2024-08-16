package Test;

import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player testPlayer = new Player();

    @Test
    void getInstance() {

    }

    @Test
    void resetPlayer() {
        Player tempCompare = new Player();
        tempCompare.setX(1);
        tempCompare.setY(1);
        testPlayer.resetPlayer();
        assertNotEquals(tempCompare, testPlayer);
    }

    @Test
    void updateState() {
        testPlayer.setX(1);
        testPlayer.setY(1);
        Player tempCompare = new Player();
        tempCompare.updateState(testPlayer);
        assertEquals(testPlayer, tempCompare);
    }

    @Test
    void moveUp() {
        int ogY = testPlayer.getY();
        testPlayer.moveUp();
        assertNotEquals(ogY, testPlayer.getY());
    }

    @Test
    void moveDown() {
        int ogY = testPlayer.getY();
        testPlayer.moveDown();

        assertNotEquals(ogY, testPlayer.getY());
    }

    @Test
    void moveLeft() {
        int ogX = testPlayer.getX();
        testPlayer.moveLeft();
        assertNotEquals(ogX, testPlayer.getX());
    }

    @Test
    void moveRight() {
        int ogX = testPlayer.getX();
        testPlayer.moveRight();
        assertNotEquals(ogX, testPlayer.getX());
    }
}