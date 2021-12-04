package usecases;
import adaptors.IGameController;
import java.awt.*;

public class NewGameButton extends TextButton {
    /**
     * Initializes a new LoadGameButton.
     *
     * @param r    The rectangle representing the bounds and location of this button.
     * @param text The text to display.
     * @param tag  The tag of this label.
     * @param control The controller controlling this button.
     */
    public NewGameButton(Rectangle r, String text, String tag, IGameController control) {
        super(r, text, tag, control);
        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
        this.setLabelColor(null);
        this.setTextColor(Color.WHITE);
    }   

    @Override 
    public void onClick() {
        //if savefile doesnt exist, create a blank one
        super.controller.createNewFile();
        super.controller.setActiveStage("Main");
    }
}
