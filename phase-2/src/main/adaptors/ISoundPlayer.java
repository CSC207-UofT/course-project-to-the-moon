package adaptors;

/**
 * An interface that represents a class that plays sounds.
 * @author Andy Wang
 * @since Dec 4 2021
 */
public interface ISoundPlayer {
    void play(String name, int loopCount);
    void stop(String name);
}
