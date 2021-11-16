package usecases;

import adaptors.IGameController;

import java.awt.*;

/**
 * A class that represents the home button to return to the main stage.
 * @author Fatimeh Hassan
 * @since 12 November 2021
 */
public class HomeButton extends TextButton {
    /**
     * Initialize a new button that takes you back to home.
     * @param r The bounds of the button.
     * @param text The text.
     * @param tag The tag of the button.
     * @param controller The controller controlling this button.
     */
    public HomeButton(Rectangle r, String text, String tag, IGameController controller) {
        super(r, text, tag, controller);

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
    }

    @Override
    public void onClick() {
        controller.setActiveStage("Main");
    }
}