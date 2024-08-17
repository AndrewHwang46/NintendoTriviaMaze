package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * The SoundManager class is responsible for managing and playing audio in the game.
 * It handles loading, storing, and controlling the playback of audio clips.
 *
 * @author Andrew Hwang
 * @author Jian Azul
 * @version 1
 */
public class SoundManager {
    /** A map to store loaded sound clips, with their names as keys. */
    private Map<String, Clip> sounds;

    /** The current volume level for all sounds, ranging from 0.0 (mute) to 1.0 (full volume). */
    private float volume = 1.0f;

    /** The currently playing audio clip. */
    private Clip myClip;

    /** An array of sound file paths to be loaded and used in the game. */
    private final String[] mySounds;

    /**
     * Constructs a new SoundManager.
     * Initializes the mySounds array with a single audio file path.
     */
    public SoundManager() {
        mySounds = new String[1];
        mySounds[0] = "View/Music/Rolling_Down_the_Street,_in_My_Katamari.mp3";
    }

    /**
     * Sets the audio file to be played.
     * Loads the audio file from the resources and prepares it for playback.
     *
     * @param theFile The index of the audio file in the mySounds array
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
     * Starts playing the currently loaded audio clip.
     */
    public void play() {
        myClip.start();
    }

    /**
     * Starts playing the currently loaded audio clip in a continuous loop.
     */
    public void loop() {
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the playback of the currently playing audio clip.
     */
    public void stop() {
        myClip.stop();
    }
}
