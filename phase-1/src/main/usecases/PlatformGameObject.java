package usecases;

import adaptors.IGameGraphics;

import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * A class which represents a platform,
 * in a minigame.
 * @author Aria Paydari
 * @since 11 November 2021
 */
public class PlatformGameObject extends GameObject implements Drawable, Collidable{
        /**
         * Initializes a new PlatformGameObject at the given coordinates.
         * @param x The x coordinate.
         * @param y The y coordinate.
         * @param sprite The sprite of the platform.
         */
        public PlatformGameObject(int x, int y, SpriteFacade sprite) {
            super(x, y, "Platform", sprite);
        }

        @Override public void draw(IGameGraphics g, int offsetX, int offsetY) {
            BufferedImage frame = this.getSprite().getCurrentFrame();
            int drawnX  = (int) this.getX() - offsetX;
            int drawnY = (int) this.getY() - offsetY;

            g.drawImage(frame, drawnX, drawnY);
        }

        @Override
        public Rectangle getHitBox() {
            return new Rectangle((int) this.getX(), (int) this.getY(),
                    this.getSprite().getWidth(), this.getSprite().getHeight());
        }
}
