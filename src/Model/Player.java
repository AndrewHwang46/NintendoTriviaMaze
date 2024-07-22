package Model;

public class Player {
     private int myX;
     private int myY;
     private int myLife;
     private int myMovementSpeed;
     private int myScore;

     public Player(int x, int y) {
         myX = x;
         myY = y;
         myLife = 3;
         myMovementSpeed = 5;
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

     public void damageTaken (boolean theAnswer) {
         if (!theAnswer) {
             myLife = 1;
         }
     }

     public int getMovementSpeed() {
         return myMovementSpeed;
     }

     public int getMyScore() {
         return myScore;
     }

     public void setMyScore(int myScore) {
         this.myScore = myScore;
     }









}
