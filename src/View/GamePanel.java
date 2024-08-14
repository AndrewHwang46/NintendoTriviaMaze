package View;

import Model.GameSettings;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    private transient Thread myGameThread;
    private transient SoundManager mySound;

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / GameSettings.FPS;
        // 0.0166 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastUpdateTime = System.currentTimeMillis();

        while (myGameThread != null) {
            update();

            repaint();

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime >= 3000) {
                myHotBar.updateGUI(myGame.getMyPlayer(),this);
                lastUpdateTime = currentTime;
            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {

    }

    public void musicBackground(final int theIndex) {
        mySound.setFile(theIndex);
        mySound.play();
        mySound.loop();
    }
}
