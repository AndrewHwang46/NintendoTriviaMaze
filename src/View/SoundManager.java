package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;

public class SoundManager {
    private Map<String, Clip> sounds;
    private float volume = 1.0f;
    private Clip myClip;
    private final String[] mySounds;

    /**
     * This method constructs a new SoundManager.
     */
    public SoundManager() {
        mySounds= new String[1];
        mySounds[0] = "View/Music/Rolling_Down_the_Street,_in_My_Katamari.mp3";

    }

    /**
     * This Method sets myClip to where the file where the audio is.
     * @param theFile
     */
    public void setFile(final int theFile) {
        try {
            InputStream is = getClass().getResourceAsStream(mySounds[theFile]);
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            myClip = AudioSystem.getClip();
            myClip.open(ais);
        } catch (Exception e) {
            System.out.println("Not found");
            e.printStackTrace();
        }
    }

    /**
     * Starts playing the audio clip.
     */
    public void play() {
        myClip.start();
    }

    /**
     * Starts playing the audio clip in a loop.
     */
    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the audio clip.
     */
    public void stop() {
        myClip.stop();
    }
}
