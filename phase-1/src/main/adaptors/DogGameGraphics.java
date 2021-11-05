package adaptors;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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

        // By default, Graphics draws strings from the LOWER LEFT corner
        // I want to make it draw from the UPPER LEFT corner
        FontMetrics fm = this.g.getFontMetrics();
        Rectangle2D bounds = fm.getStringBounds(s, this.g);
        int height = (int) bounds.getHeight();

        this.g.drawString(s, x, y + height);
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
        this.g.fillRect(x, y, w, h);
    }
}
