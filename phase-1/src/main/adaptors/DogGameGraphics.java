package adaptors;

import java.awt.*;

/**
 * An implementation of the GameGraphics object for use in DogGame.
 * @author Andy Wang
 * @since 23 October 2021
 */
public class DogGameGraphics implements GameGraphics {
    private Graphics g = null;

    /**
     * Initializes a new DogGameGraphics by adapting a default Graphics object.
     * @param g The Graphics object to adapt.
     */
    public DogGameGraphics(Graphics g) {
        this.g = g;
    }

    /**
     * Draws an image.
     * @param i The image to draw.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    @Override
    public void drawImage(Image i, int x, int y) {
        this.g.drawImage(i, x, y, null);
    }

    /**
     * Draws text.
     * @param s The text to draw.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    @Override
    public void drawText(String s, int x, int y) {
        this.g.drawString(s, x, y);
    }

    /**
     * Sets the color to use.
     * @param c The new color.
     */
    @Override
    public void setColor(Color c) {
        this.g.setColor(c);
    }
}
