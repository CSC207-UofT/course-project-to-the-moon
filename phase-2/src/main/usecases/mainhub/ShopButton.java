package usecases.mainhub;

import adaptors.IGameController;
import usecases.object.TextButton;

import java.awt.*;

/**
 * A class that represents the button for the shop.
 * @author Praket Kanaujia
 * @since 10 November 2021
 */
public class ShopButton extends TextButton {
    private final IGameController control;
    /**
     * Initializes a new ShopButton.
     *
     * @param r    The rectangle representing the bounds and location of this button.
     * @param text The text to display.
     * @param tag  The tag of this label.
     * @param control The controller controlling this button.
     */
    public ShopButton(Rectangle r, String text, String tag, IGameController control) {
        super(r, text, tag, control);
        this.control = control;

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.CYAN);
        this.setLabelColor(null);
        this.setTextColor(Color.CYAN);
    }

    @Override
    public void onClick() {
        control.setActiveStage("Shop");
    }
}
