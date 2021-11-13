package usecases;

import adaptors.IGameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class which represents a platform,
 * in a minigame.
 *
 * @author Aria Paydari
 * @since 11 November 2021
 */
public class PlatformGameObject extends GameObject implements Drawable, Collidable {
    /**
     * Initializes a new PlatformGameObject at the given coordinates.
     *
     * @param x      The x coordinate.
     * @param y      The y coordinate.
     * @param tag    The tag of the platform.
     * @param sprite The sprite of the platform.
     */
    public PlatformGameObject(int x, int y, String tag, SpriteFacade sprite) {
        super(x, y, tag, sprite);
    }

    @Override
    public void draw(IGameGraphics g, int offsetX, int offsetY) {
        BufferedImage frame = this.getCurrentFrame();
        int drawnX = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle((int) this.getX(), (int) this.getY(),
                this.getWidth(), this.getHeight());
    }

    @Override
    public Rectangle getHitBoxAtCoords(int x, int y) {
        return new Rectangle(x, y,
                this.getWidth(), this.getHeight());
    }
}
