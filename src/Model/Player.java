package Model;

import java.beans.PropertyChangeSupport;

public class Player {

     private int myX;

     private int myY;

     private int myLife = 3;

     private int myScore;

     private boolean myWin;

     private PropertyChangeSupport myPCS = new PropertyChangeSupport(this);

     public Player() {
         myX = GameSettings.SCREEN_WIDTH/2 - (GameSettings.TILE_SIZE/2);
         myY = GameSettings.SCREEN_HEIGHT/2 - (GameSettings.TILE_SIZE/2);
         myScore = 0;
         myWin = false;
     }

     public int getX() {
         return myX;
     }

     public int getY() {
         return myY;
     }

     public int getLife() {
         return myLife;
     }

     public void setLife(int life) {
         myLife = life;
     }

     public void damageTaken () {
         if (myLife > 0) {
             myLife--;
         }
     }

     public void scoreChanger(boolean theCorrectAnswer) {
         if (theCorrectAnswer) {
             myScore =+ 100;
         }else {
             myScore =- 100;
         }


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
}
