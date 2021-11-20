package usecases;

import entities.Sprite;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A use case class that animates a sprite.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class SpriteAnimator {
    private int fps = 0; // The stored fps
    private Timer timer = new Timer();
    private TimerTask updateTask;

    /**
     * Animates a sprite given frames per second by scheduling a new task that calls the sprite's
     * incrementFrame method.
     *
     * @param sprite The sprite to animate.
     * @param fps The animation speed in frames per second.
     */
    public void animateSprite(Sprite sprite, int fps) {
        this.fps = fps;

        this.updateTask = new TimerTask() {
            @Override
            public void run() {
                sprite.incrementFrame();
            }
        };

        if (fps > 0) {
            timer.scheduleAtFixedRate(this.updateTask, 0, 1000 / this.fps);
        }
    }

    /*
     * Returns the current fps being animated.
     * @return The current fps in seconds.

   public int getFPS() {
       return this.fps;
    }
    */

    /**
     * Sets the fps being animated.
     * @param fps The new fps in seconds.
     */
    public void setFPS(int fps) {
        this.fps = fps;

        this.timer.cancel(); // cancel the current timer
        this.timer = new Timer(); // create a new timer

        // schedule the update task at the new speed
        if (fps > 0) {
            timer.scheduleAtFixedRate(this.updateTask, 0, 1000 / this.fps);
        }
    }
}
