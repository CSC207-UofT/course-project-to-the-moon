package adaptors;

import java.awt.*;

/**
 * An implementation of the IGameGraphics object for use in DogGame.
 * @author Andy Wang
 * @since 23 October 2021
 */
public class DogGameGraphics implements IGameGraphics {
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
     * @param c The colour to use.
     */
    @Override
    public void drawText(String s, int x, int y, Color c) {
        this.g.setColor(c);
        this.g.drawString(s, x, y);
    }

    /**
     * Draws a filled in rectangle.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param w The width.
     * @param h The height.
     * @param c The colour to use.
     */
    @Override
    public void fillRect(int x, int y, int w, int h, Color c) {
        this.g.setColor(c);
        this.g.drawRect(x, y, w, h);
    }
}
