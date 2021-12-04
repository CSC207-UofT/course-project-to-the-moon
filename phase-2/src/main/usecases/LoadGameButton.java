package usecases;
import adaptors.IGameController;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadGameButton  extends TextButton {
    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);

    /**
     * Initializes a new LoadGameButton.
     *
     * @param r    The rectangle representing the bounds and location of this button.
     * @param text The text to display.
     * @param tag  The tag of this label.
     * @param control The controller controlling this button.
     */
    public LoadGameButton(Rectangle r, String text, String tag, IGameController control) {
        super(r, text, tag, control);

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
        this.setLabelColor(null);
        this.setTextColor(Color.WHITE);
    }   

    public void addPropertyChangeListener(PropertyChangeListener observer) {
        observable.addPropertyChangeListener(observer);
    }  

    @Override
    public void onClick() {
        boolean loaded = super.controller.loadFromFile();

        if(!loaded){
            this.setText("No previous save file detected!");
        }
        else {
            observable.firePropertyChange("Game Loaded", 0, true);
        }
    }
}
