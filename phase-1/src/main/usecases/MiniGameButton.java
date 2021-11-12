package usecases;

import adaptors.IGameController;

import java.awt.*;

/**
 * A class that represents the button for the shop.
 * @author Aria Paydari
 * @since 101November 2021
 */
public class MiniGameButton extends TextLabel implements Clickable{
    private IGameController control;
    /**
     * Initializes a new TextLabel.
     *
     * @param r    The rectangle representing the bounds and location of this label.
     * @param text The text to display.
     * @param tag  The tag of this label.
     */
    public MiniGameButton(Rectangle r, String text, String tag, IGameController control) {
        super(r, text, tag);
        this.control = control;

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.GREEN);
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
        control.setActiveStage("Minigame");
    }
}
