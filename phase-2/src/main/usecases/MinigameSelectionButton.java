package usecases;

import adaptors.IGameController;

import java.awt.*;

/**
 * A class that represents the button for the minigame selection.
 * @author Fatimeh Hassan
 * @since 29 November 2021
 */
public class MinigameSelectionButton extends TextButton {
    private final IGameController control;
    /**
     * Initializes a new MinigameSelectionButton.
     *
     * @param r    The rectangle representing the bounds and location of this button.
     * @param text The text to display.
     * @param tag  The tag of this label.
     * @param control The controller controlling this button.
     */
    public MinigameSelectionButton(Rectangle r, String text, String tag, IGameController control) {
        super(r, text, tag, control);
        this.control = control;

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.GREEN);
    }

    @Override
    public void onClick() {
        control.setActiveStage("MinigameSelectionStage");
    }
}