package Model;

/**
 *
 * @author
 * @author Noah Ogilvie
 */

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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }

        if (theOther == null) {
            return false;
        }

        if (!(theOther instanceof Tiles)) {
            return false;
        }

        final Tiles other = (Tiles) theOther;

        return this.myCollision == other.myCollision &&
                this.myTileImage == other.myTileImage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 97;
        hash = 31 * hash + (this.myCollision ? 1 : 0);
        hash = 31 * hash + (this.myTileImage != null ? this.myTileImage.hashCode() : 0);
        return hash;
    }
}
