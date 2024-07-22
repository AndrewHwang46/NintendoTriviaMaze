package View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private Map<String, Clip> sounds;
    private float volume = 1.0f;

    public SoundManager() {
        sounds = new HashMap<>();
    }

    public void loadSound(String name, String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            sounds.put(name, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSound(String name) {
        Clip clip = sounds.get(name);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loopSound(String name, int count) {
        Clip clip = sounds.get(name);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(count);
        }
    }

    public void stopSound(String name) {
        Clip clip = sounds.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void stopAllSounds() {
        for (Clip clip : sounds.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
        for (Clip clip : sounds.values()) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    public float getVolume() {
        return volume;
    }

    public void dispose() {
        for (Clip clip : sounds.values()) {
            clip.close();
        }
        sounds.clear();
    }
}