/*
 * T CSS 360 - Summer 2024
 * Nintendo Trivia Maze
 */
package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;

/**
 * This method creates the player. It holds the player's position, score
 * and if they have won the game.
 *
 * @author Andrew Hwang
 * @version 1
 */
public class Player implements Serializable{

    /**
     * serialVersionUID is the serializable constant for the implementation of Serializable.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * myPlayerSingleton is an instance Single instance of player.
     */
    private static Player myPlayerSingleton;

    /**
     * myX is the players x coordinate.
     */
    private int myX;

    /**
     * myY is the players y coordinate.
     */
    private int myY;

    /**
     * myScore is the players score.
     */
    private int myScore;

    /**
     * myWin is the player's win status.
     */
    private boolean myWin;

    /**
     * myPCS is a property change support to update the player's x,y and score values.
     */
    private PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

    /**
     * This is the constructor for the player class.
     */
    private Player() {
        myX = GameSettings.SCREEN_WIDTH/2 - (GameSettings.TILE_SIZE/2);
        myY = GameSettings.SCREEN_HEIGHT/2 - (GameSettings.TILE_SIZE/2);
        myScore = 0;
        myWin = false;
    }

    /**
     * This method gets the instance of the myPlayerSingleton.
     * @return is a Player and is the myPlayerSingleton.
     */
    public static synchronized Player getInstance() {
        if(myPlayerSingleton == null) {
            myPlayerSingleton = new Player();
        }
        return myPlayerSingleton;
    }

    /**
     * This method resets all of myPlayerSingleton's values.
     */
    public static synchronized void resetPlayer() {
        myPlayerSingleton = new Player();
    }

    /**
     * This method updates the saved player to the current instance of player.
     * @param theLoadedPlayer is a player and is the saved player information.
     */
    public void updateState(Player theLoadedPlayer) {
        this.myX = theLoadedPlayer.myX;
        this.myY = theLoadedPlayer.myY;
        this.myScore = theLoadedPlayer.myScore;
        this.myWin = theLoadedPlayer.myWin;
        scoreFirePropertyChanger();
    }

    /**
     * This method moves the player's position up
     * by adding the players speed to the y value.
     */
    public void moveUp() {
        setY(myY - GameSettings.PLAYER_SPEED);
    }

    /**
     * This method moves the player's position down
     * by subtracting the players speed to the y value.
     */
    public void moveDown() {
        setY(myY + GameSettings.PLAYER_SPEED);
    }

    /**
     * This method moves the player's position left
     * by subtracting the players speed to the x value.
     */
    public void moveLeft() {
        setX(myX - GameSettings.PLAYER_SPEED);
    }

    /**
     * This method moves the player's position right
     * by adding the players speed to the x value.
     */
    public void moveRight() {
        setX(myX + GameSettings.PLAYER_SPEED);
    }

    /**
     * This method returns the player's x value.
     * @return is an int and is the player's x value.
     */
    public int getX() { return myX; }

    /**
     * This method sets the x value of the player with the given int.
     * @param theX is an integer and the new x position.
     */
    public void setX(int theX) {
        int oldX = this.myX;
        this.myX = theX;
        myPCS.firePropertyChange("playerX", oldX, theX);
    }

    /**
     * This method returns the player's y value.
     * @return is an int and is the player's y value.
     */
    public int getY() { return myY; }

    /**
     * This method sets the y value of the player with the given int.
     * @param theY is an integer and the new y position.
     */
    public void setY(int theY) {
        int oldY = this.myY;
        this.myY = theY;
        myPCS.firePropertyChange("playerY", oldY, theY);
    }

    /**
     * This method increase the player's score if the given
     * boolean is true. Then updates the score.
     * @param theCorrectAnswer is a boolean and is whether
     *                         the question was answered correctly.
     */
    public void scoreChanger(boolean theCorrectAnswer) {
        if (theCorrectAnswer) {
            myScore += 100;
        }
        scoreFirePropertyChanger();
    }

    /**
     * This method returns the player's score.
     * @return is an int and is the player's score.
     */
    public int getMyScore() { return myScore; }

    /**
     * This method sets the Score of the player with the given int.
     * @param theScore is an integer and the new Score.
     */
    public void setMyScore(int theScore) {
        myScore = theScore;
        scoreFirePropertyChanger();
    }

    /**
     * This method returns the player win status.
     * @return is a boolean and is the players win status.
     */
    public boolean getWin() { return myWin; }

    /**
     * This method sets the player win status.
     * @param theWin is a boolean and is the players win status.
     */
    public void setWin(boolean theWin) { myWin = theWin; }

    /**
     * This method returns the property change support.
     * @return is a property change support.
     */
    public PropertyChangeSupport getPCS() { return myPCS; }

    /**
     * Notifies registered listeners that the score has changed.
     */
    public void scoreFirePropertyChanger() {
        myPCS.firePropertyChange("score", null, myScore);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * The listener will be notified when properties change.
     * @param listener  the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        myPCS.addPropertyChangeListener(listener);
    }

}
