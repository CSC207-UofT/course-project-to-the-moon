package usecases;

import adaptors.IGameController;
import adaptors.IGameGraphics;

import java.awt.*;

/**
 * A class representing a label that displays text.
 * @author Juntae Park
 * @since 29 October 2021
 */
public class TextLabel extends AbstractObject implements Drawable {
    protected String text;
    protected Rectangle rectangle;

    private Color labelColor = null;
    private Color textColor = null;

    /**
     * Initializes a new TextLabel.
     * @param r The rectangle representing the bounds and location of this label.
     * @param text The text to display.
     * @param tag The tag of this label.
     */
    public TextLabel(Rectangle r, String text, String tag) {
        super(r.getX(), r.getY(), tag);
        this.text = text;
        this.rectangle = r;
    }

    /* setters */
    // set to null for transparent background
    public void setLabelColor(Color c) {
        this.labelColor = c;
    }

    public void setTextColor(Color c) {
        this.textColor = c;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Draw this text label.
     * @param g The GameGraphics implementation.
     * @param offsetX The offset in the x coordinate.
     * @param offsetY The offset in the y coordinate.
     */
    @Override
    public void draw(IGameGraphics g, int offsetX, int offsetY) {
        //TODO: Add more customization to this, like outline and font?
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        if (this.labelColor != null) {
            g.fillRect(drawnX, drawnY,
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight(),
                    this.labelColor);
        }

        g.drawText(this.text, drawnX, drawnY, this.textColor);
    }
}
