package usecases;

import adaptors.IGameController;

import java.awt.*;

/**
 * A class representing a text button.
 * @author Andy Wang
 * @since 12 November 2021
 */
public class TextButton extends TextLabel implements Clickable {
    protected IGameController controller;
    /**
     * Initializes a new TextButton.
     * @param r The rectangle representing the bounds and location of this button.
     * @param text The text to display.
     * @param tag The tag of this label.
     * @param controller The controller controlling this button.
     */
    public TextButton(Rectangle r, String text, String tag, IGameController controller) {
        super(r, text, tag);
        this.controller = controller;
    }

    @Override
    public boolean isClicked(int mouseX, int mouseY) {
        double x = this.getX();
        double y = this.getY();
        int width = super.rectangle.width;
        int height = super.rectangle.height;

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    @Override
    public void onClick() {

    }
}
