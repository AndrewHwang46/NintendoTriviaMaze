package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Player implements Serializable{
    private static Player myPlayerSingleton;

    private int myX;

    private int myY;

    private int myScore;

    private boolean myWin;

    private PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

//    public Player() {
//        myX = GameSettings.SCREEN_WIDTH/2 - (GameSettings.TILE_SIZE/2);
//        myY = GameSettings.SCREEN_HEIGHT/2 - (GameSettings.TILE_SIZE/2);
//        myScore = 0;
//        myWin = false;
//    }
//
//    public int getX() {
//         return myX;
//     }
//
//     public int getY() {
//         return myY;
//     }
//
//    public void moveUp() { setY(myY - GameSettings.TILE_SIZE); }
//    public void moveDown() { setY(myY + GameSettings.TILE_SIZE); }
//    public void moveLeft() { setX(myX - GameSettings.TILE_SIZE); }
//    public void moveRight() { setX(myX + GameSettings.TILE_SIZE); }
//
//     public void scoreChanger(boolean theCorrectAnswer) {
//         if (theCorrectAnswer) {
//             myScore =+ 100;
//         }else {
//             myScore =- 100;
//         }
//
//
//     }
//
//     public static synchronized Player getInstance() {
//        if(myPlayerSingleton == null) {
//            myPlayerSingleton = new Player();
//        }
//        return myPlayerSingleton;
//     }
//
//    public static synchronized void resetPlayer() {
//        myPlayerSingleton = new Player();
//    }
//
//     public int getMyScore() {
//         return myScore;
//     }
//
//     public void setMyScore(int myScore) {
//         this.myScore = myScore;
//     }
//
//     public PropertyChangeSupport getPCS() {
//         return myPCS;
//     }
//
//     public void scoreFirePropertyChanger() {
//         myPCS.firePropertyChange("score", null, myScore);
//     }
//
//     public boolean getWin() {
//        return myWin;
//     }
//
//     public void setWin(boolean theWin) {
//        myWin = theWin;
//     }
//
//    public void setY(int theY) {
//        myY = theY;
//    }
//    public void setX(int theX) {
//        myX = theX;
//    }
//
//
//    private static Player myPlayerSingleton;
//
//    private int myX;
//    private int myY;
//    private int myScore;
//    private boolean myWin;
//    private PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

    private Player() {
        myX = GameSettings.SCREEN_WIDTH/2 - (GameSettings.TILE_SIZE/2);
        myY = GameSettings.SCREEN_HEIGHT/2 - (GameSettings.TILE_SIZE/2);
        myScore = 0;
        myWin = false;
    }

    public static synchronized Player getInstance() {
        if(myPlayerSingleton == null) {
            myPlayerSingleton = new Player();
        }
        return myPlayerSingleton;
    }

    public static synchronized void resetPlayer() {
        myPlayerSingleton = new Player();
    }

    // New method to update player state
    public void updateState(Player loadedPlayer) {
        this.myX = loadedPlayer.myX;
        this.myY = loadedPlayer.myY;
        this.myScore = loadedPlayer.myScore;
        this.myWin = loadedPlayer.myWin;
        scoreFirePropertyChanger();
    }

    public void moveUp() {
        setY(myY - GameSettings.PLAYER_SPEED);
    }

    public void moveDown() {
        setY(myY + GameSettings.PLAYER_SPEED);
    }

    public void moveLeft() {
        setX(myX - GameSettings.PLAYER_SPEED);
    }

    public void moveRight() {
        setX(myX + GameSettings.PLAYER_SPEED);
    }


    public int getX() { return myX; }
    public void setX(int x) {
        int oldX = this.myX;
        this.myX = x;
        myPCS.firePropertyChange("playerX", oldX, x);
    }

    public int getY() { return myY; }
    public void setY(int y) {
        int oldY = this.myY;
        this.myY = y;
        myPCS.firePropertyChange("playerY", oldY, y);
    }

    public void scoreChanger(boolean theCorrectAnswer) {
        if (theCorrectAnswer) {
            myScore += 100;
        } else {
            myScore -= 100;
        }
        scoreFirePropertyChanger();
    }

    public int getMyScore() { return myScore; }
    public void setMyScore(int myScore) {
        this.myScore = myScore;
        scoreFirePropertyChanger();
    }

    public boolean getWin() { return myWin; }
    public void setWin(boolean theWin) { myWin = theWin; }

    public PropertyChangeSupport getPCS() { return myPCS; }

    public void scoreFirePropertyChanger() {
        myPCS.firePropertyChange("score", null, myScore);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        myPCS.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        myPCS.removePropertyChangeListener(listener);
    }
}
