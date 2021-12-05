package adaptors;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * A class that plays sound.
 * @author Andy Wang
 * @since Dec 4 2021
 */
public class DogGameSoundPlayer implements ISoundPlayer {
    // this table is for memoization
    private final HashMap<String, Clip> soundMap = new HashMap<>();

    /**
     * Plays a sound effect given the number of times it should loop. A value of 0 means it plays once. A value
     * of -1 means it loops indefinitely.
     * @param name The name of the sound to play.
     * @param loopCount The number of times it should loop.
     */
    @Override
    public void play(String name, int loopCount) {
        Clip clip;
        // First, check if the sound has already been memoized
        if (soundMap.containsKey(name)) {
            clip = soundMap.get(name);
        } else {
            clip = load(name);
            soundMap.put(name, clip);
        }

        if ((clip != null) && (!clip.isActive())) {
            clip.setMicrosecondPosition(0);
            clip.loop(loopCount);
        }
    }

    /**
     * Stops a sound effect.
     * @param name The name of the sound to stop.
     */
    @Override
    public void stop(String name) {
        Clip clip;
        // First, check if the sound has already been memoized
        if (soundMap.containsKey(name)) {
            clip = soundMap.get(name);
        } else {
            clip = load(name);
            soundMap.put(name, clip);
        }

        if (clip != null) {
            clip.stop();
            clip.setMicrosecondPosition(0);
        }
    }

    /**
     * Loads a sound given its name.
     * @param name The name of the sound to load.
     */
    private Clip load(String name) {
        try {
            File soundFile = new File("phase-2/src/sounds/" + name);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            return clip;
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println("Something went wrong loading the sound!");
            e.printStackTrace();
        }

        return null;
    }
}
