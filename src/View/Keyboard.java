package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class Keyboard implements KeyListener, Serializable {

    private boolean myUpPressed, myDownPressed, myLeftPressed, myRightPressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

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
     * If key is release, set boolean fields to false.
     * @param e the event to be processed
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

    public void setAllKeys() {
        myUpPressed = false;
        myDownPressed = false;
        myRightPressed = false;
        myLeftPressed = false;
    }

    public boolean isMyUpPressed() {
        return myUpPressed;
    }


    public void setMyUpPressed(boolean myUpPressed) {
        this.myUpPressed = myUpPressed;
    }

    public boolean isMyDownPressed() {
        return myDownPressed;
    }

    public void setMyDownPressed(boolean myDownPressed) {
        this.myDownPressed = myDownPressed;
    }

    public boolean isMyRightPressed() {
        return myRightPressed;
    }

    public void setMyRightPressed(boolean myRightPressed) {
        this.myRightPressed = myRightPressed;
    }

    public boolean isMyLeftPressed() {
        return myLeftPressed;
    }

    public void setMyLeftPressed(boolean myLeftPressed) {
        this.myLeftPressed = myLeftPressed;
    }
}
