package Model;

import java.awt.image.BufferedImage;

public class Tiles {
    private boolean myCollision;
    private transient BufferedImage myTileImage;

    public Tiles() {
        myCollision = false;
    }

    public BufferedImage getTileImage() {
        return myTileImage;
    }

    public void setTileImage(BufferedImage myTileImage) {
        this.myTileImage = myTileImage;

    }

    public void setMyTileImage(BufferedImage myTileImage) {
        this.myTileImage = myTileImage;
    }

    public boolean isMyCollision() {
        return myCollision;
    }

    public void setMyCollision(boolean myCollision) {
        this.myCollision = myCollision;
    }
}
