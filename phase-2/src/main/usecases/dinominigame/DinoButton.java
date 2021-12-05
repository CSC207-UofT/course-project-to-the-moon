package usecases.dinominigame;

import adaptors.IGameController;
import usecases.object.TextButton;

import java.awt.*;

/**
 * A class that represents the button for the shop.
 * @author Aria Paydari
 * @since 10 November 2021
 */
public class DinoButton extends TextButton {
    private final IGameController control ;

    /**
     * Initializes a button that takes you to the minigame.
     *
     * @param r    The rectangle representing the bounds and location of this button.
     * @param text The text to display.
     * @param tag  The tag of this button.
     * @param control The controller controlling this button.
     */
    public DinoButton(Rectangle r, String text, String tag, IGameController control) {
        super(r, text, tag, control);
        this.control = control;

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.GREEN);
        this.setTextColor(Color.WHITE);
    }

    @Override
    public void onClick() {
        control.setActiveStage("Dino");
    }
}
