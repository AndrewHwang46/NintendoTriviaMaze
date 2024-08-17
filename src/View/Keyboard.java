package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * The Keyboard class handles keyboard input for the game.
 * It implements KeyListener to detect key presses and releases,
 * and Serializable to allow saving the keyboard state.
 *
 * @author Andrew Hwang
 * @author Jian Azul
 * @version 1
 */
public class Keyboard implements KeyListener, Serializable {

    /** Indicates whether the up key (W) is currently pressed. */
    private boolean myUpPressed;

    /** Indicates whether the down key (S) is currently pressed. */
    private boolean myDownPressed;

    /** Indicates whether the left key (A) is currently pressed. */
    private boolean myLeftPressed;

    /** Indicates whether the right key (D) is currently pressed. */
    private boolean myRightPressed;

    /**
     * Invoked when a key is typed. This method is empty as it's not used in this implementation.
     *
     * @param e the KeyEvent object containing information about the key typed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // This method is intentionally left empty
    }

    /**
     * Invoked when a key is pressed. Updates the corresponding boolean field to true.
     *
     * @param e the KeyEvent object containing information about the key pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            setMyUpPressed(true);
        }
        if (code == KeyEvent.VK_S) {
            setMyDownPressed(true);
        }
        if (code == KeyEvent.VK_A) {
            setMyLeftPressed(true);
        }
        if (code == KeyEvent.VK_D) {
            setMyRightPressed(true);
        }
    }

    /**
     * Invoked when a key is released. Updates the corresponding boolean field to false.
     *
     * @param e the KeyEvent object containing information about the key released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            setMyUpPressed(false);
        }
        if (code == KeyEvent.VK_S) {
            setMyDownPressed(false);
        }
        if (code == KeyEvent.VK_A) {
            setMyLeftPressed(false);
        }
        if (code == KeyEvent.VK_D) {
            setMyRightPressed(false);
        }
    }

    /**
     * Resets all key states to false.
     */
    public void setAllKeys() {
        myUpPressed = false;
        myDownPressed = false;
        myRightPressed = false;
        myLeftPressed = false;
    }

    /**
     * Checks if the up key is currently pressed.
     *
     * @return true if the up key is pressed, false otherwise
     */
    public boolean isMyUpPressed() {
        return myUpPressed;
    }

    /**
     * Sets the state of the up key.
     *
     * @param theUpPressed the new state of the up key
     */
    public void setMyUpPressed(boolean theUpPressed) {
        myUpPressed = theUpPressed;
    }

    /**
     * Checks if the down key is currently pressed.
     *
     * @return true if the down key is pressed, false otherwise
     */
    public boolean isMyDownPressed() {
        return myDownPressed;
    }

    /**
     * Sets the state of the down key.
     *
     * @param theDownPressed the new state of the down key
     */
    public void setMyDownPressed(boolean theDownPressed) {
        myDownPressed = theDownPressed;
    }

    /**
     * Checks if the right key is currently pressed.
     *
     * @return true if the right key is pressed, false otherwise
     */
    public boolean isMyRightPressed() {
        return myRightPressed;
    }

    /**
     * Sets the state of the right key.
     *
     * @param theRightPressed the new state of the right key
     */
    public void setMyRightPressed(boolean theRightPressed) {
        this.myRightPressed = theRightPressed;
    }

    /**
     * Checks if the left key is currently pressed.
     *
     * @return true if the left key is pressed, false otherwise
     */
    public boolean isMyLeftPressed() {
        return myLeftPressed;
    }

    /**
     * Sets the state of the left key.
     *
     * @param theLeftPressed the new state of the left key
     */
    public void setMyLeftPressed(boolean theLeftPressed) {
        this.myLeftPressed = theLeftPressed;
    }
}
