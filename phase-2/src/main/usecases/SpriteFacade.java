package usecases;

import entities.Sprite;

import java.awt.image.BufferedImage;

/**
 * A class that represents a sprite, but has animator functionality. A facade class.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class SpriteFacade {
    private final Sprite sprite ;
    private final SpriteAnimator animator = new SpriteAnimator();

    /**
     * Initializes a sprite facade given its frames as a BufferedImage array.
     * This method should be used for sprites that are not animated, or at least on creation.
     * @param frames An array of BufferedImages representing its frames.
     */
    public SpriteFacade(BufferedImage[] frames) {
        this.sprite = new Sprite(frames);
        this.animator.animateSprite(this.sprite, 0);
    }

    /**
     * Initializes a sprite facade given its frames as a BufferedImage array.
     * This method should be used for animated sprites that are animated upon creation.
     *
     * @param frames An array of BufferedImages representing its frames.
     * @param fps The animation speed in frames per second.
     */
    public SpriteFacade(BufferedImage[] frames, int fps) {
        this.sprite = new Sprite(frames);
        this.animator.animateSprite(this.sprite, fps);
    }

    /*
     * Changes the speed at which the sprite is animated.
     *
     * Preconditions:
     * @param fps The new fps.

    public void changeFPS(int fps) {
        this.animator.setFPS(fps);
    }
     */

    /**
     * Returns the sprite.
     * @return The sprite used by this facade.
     */
    public Sprite getSprite() {
        return this.sprite;
    }
}
