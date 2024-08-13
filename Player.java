package Model;

import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private static Player myPlayerSingleton;

    private int myX;

    private int myY;

    private int myScore;

    private boolean myWin;

    private PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

    public Player() {
//        myX = GameSettings.SCREEN_WIDTH/2 - (GameSettings.TILE_SIZE/2);
//        myY = GameSettings.SCREEN_HEIGHT/2 - (GameSettings.TILE_SIZE/2);
        myScore = 0;
        myWin = false;
    }

    public int getX() {
         return myX;
     }

     public int getY() {
         return myY;
     }

     public void scoreChanger(boolean theCorrectAnswer) {
         if (theCorrectAnswer) {
             myScore =+ 100;
         }else {
             myScore =- 100;
         }


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

     public int getMyScore() {
         return myScore;
     }

     public void setMyScore(int myScore) {
         this.myScore = myScore;
     }

     public PropertyChangeSupport getPCS() {
         return myPCS;
     }

     public void scoreFirePropertyChanger() {
         myPCS.firePropertyChange("score", null, myScore);
     }

     public boolean getWin() {
        return myWin;
     }

     public void setWin(boolean theWin) {
        myWin = theWin;
     }
}
