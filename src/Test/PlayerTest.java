package Test;

import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class test the Player class.
 *
 * @author Andrew Hwang
 * @version 1
 */
class PlayerTest {

    /**
     * testPlayer is Player object is used to test.
     */
    Player testPlayer = new Player();

    /**
     * This test checks if the resetPlayer method
     * by checking if the reset testPlayer is not equal
     * to the stored testPlayer before reset.
     */
    @Test
    void resetPlayer() {
        Player tempCompare = new Player();
        tempCompare.setX(1);
        tempCompare.setY(1);
        testPlayer.resetPlayer();
        assertNotEquals(tempCompare, testPlayer);
    }

    /**
     * This test checks if the Player object properly
     * updates its state, by checking itself once
     * updated with a new Player object.
     */
    @Test
    void updateState() {
        testPlayer.setX(1);
        testPlayer.setY(1);
        Player tempCompare = new Player();
        tempCompare.updateState(testPlayer);
        assertEquals(tempCompare,testPlayer);
    }

    /**
     * This test checks if the Player object properly
     * moves up by comparing the users previous Y coordinate
     * with the Y coordinate once moved up.
     */
    @Test
    void moveUp() {
        int ogY = testPlayer.getY();
        testPlayer.moveUp();
        assertNotEquals(ogY, testPlayer.getY());
    }

    /**
     * This test checks if the Player object properly
     * moves down by comparing the users previous Y coordinate
     * with the Y coordinate once moved down.
     */
    @Test
    void moveDown() {
        int ogY = testPlayer.getY();
        testPlayer.moveDown();

        assertNotEquals(ogY, testPlayer.getY());
    }

    /**
     * This test checks if the Player object properly
     * moves left by comparing the users previous X coordinate
     * with the X coordinate once moved left.
     */
    @Test
    void moveLeft() {
        int ogX = testPlayer.getX();
        testPlayer.moveLeft();
        assertNotEquals(ogX, testPlayer.getX());
    }

    /**
     * This test checks if the Player object properly
     * moves right by comparing the users previous X coordinate
     * with the X coordinate once moved right.
     */
    @Test
    void moveRight() {
        int ogX = testPlayer.getX();
        testPlayer.moveRight();
        assertNotEquals(ogX, testPlayer.getX());
    }
}