package usecases.object;

import adaptors.IGameGraphics;
import usecases.interfaces.Drawable;

import java.awt.*;

/**
 * A class representing a label that displays text.
 * @author Juntae Park
 * @since 29 October 2021
 */
public class TextLabel extends AbstractObject implements Drawable {
    public String text;
    protected Rectangle rectangle;

    private Color labelColor = null;
    private Color textColor = null;
    private Color strokeColor = null;
    private int stroke = 0;

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

    public void setStrokeColor(Color c) {this.strokeColor = c;}

    public void setStrokeWidth(int stroke) { this.stroke = stroke; }

    /**
     * Draw this text label.
     * @param g The GameGraphics implementation.
     * @param offsetX The offset in the x coordinate.
     * @param offsetY The offset in the y coordinate.
     */
    @Override
    public void draw(IGameGraphics g, int offsetX, int offsetY) {
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        // draw the outline
        if (this.strokeColor != null) {
            g.drawRect(drawnX, drawnY,
                    this.rectangle.width, this.rectangle.height,
                    this.stroke, this.strokeColor);
        }

        // draw the label
        if (this.labelColor != null) {
            g.fillRect(drawnX, drawnY,
                    this.rectangle.width, this.rectangle.height,
                    this.labelColor);
        }

        // draw the text (centre it on the label)
        int[] dimensions = g.getTextBounds(this.text);
        int tWidth = dimensions[0];
        int tHeight = dimensions[1];

        int textX = drawnX + this.rectangle.width / 2 - tWidth / 2;
        int textY = drawnY + this.rectangle.height / 2 - tHeight / 2;

        g.drawText(this.text, textX, textY, this.textColor);
    }
}
