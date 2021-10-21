package usecases;

import entities.Sprite;

import java.awt.image.BufferedImage;

/**
 * A class that represents a sprite, but has animator functionality. A facade class.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class SpriteFacade {
    private Sprite sprite;
    private SpriteAnimator animator;

    /**
     * Initializes a sprite facade given its frames as a BufferedImage array.
     * This method should be used for sprites that are not animated only.
     * @param frames An array of BufferedImages representing its frames.
     */
    public SpriteFacade(BufferedImage[] frames) {
        this.sprite = new Sprite(frames);
    }

    /**
     * Initializes a sprite facade given its frames as a BufferedImage array.
     * This method should be used for animated sprite.
     *
     * Precondiitons: fps should be > 0
     *
     * @param frames An array of BufferedImages representing its frames.
     * @param fps The animation speed in frames per second.
     */
    public SpriteFacade(BufferedImage[] frames, int fps) {
        this.sprite = new Sprite(frames);
        this.animator = new SpriteAnimator();

        try {
            this.animator.animateSprite(this.sprite, fps);
        } catch (NullPointerException e) {
            System.err.println("Animation speed cannot be 0!");
        }
    }

    /**
     * Changes the speed at which the sprite is animated.
     * @param fps The new fps.
     */
    public void changeFPS(int fps) {
        this.animator.setFPS(fps);
    }
}
