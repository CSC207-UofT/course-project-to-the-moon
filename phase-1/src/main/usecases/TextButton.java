package usecases;

import programdrivers.IDogGame;

import java.awt.*;

public class TextButton extends TextLabel implements Clickable, IDogGame {
    /**
     * Initializes a new TextLabel.
     *
     * @param r    The rectangle representing the bounds and location of this label.
     * @param text The text to display.
     * @param tag  The tag of this label.
     */
    public TextButton(Rectangle r, String text, String tag) {
        super(r, text, tag);
    }

    @Override
    public boolean isClicked(int mouseX, int mouseY) {
        double x = this.getX();
        double y = this.getY();
        int width = (int) super.rectangle.getWidth();
        int height = (int) super.rectangle.getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    @Override
    public void onClick() {
        createShopStage();
    }
}
