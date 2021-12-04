package usecases;
import adaptors.IGameController;
import java.awt.*;
import java.io.File;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadGameButton  extends TextButton {
    private final PropertyChangeSupport observable = new PropertyChangeSupport(this);
    private String filepath;

    /**
     * Initializes a new LoadGameButton.
     *
     * @param r    The rectangle representing the bounds and location of this button.
     * @param text The text to display.
     * @param tag  The tag of this label.
     * @param control The controller controlling this button.
     */
    public LoadGameButton(Rectangle r, String text, String tag, IGameController control, String filepath) {
        super(r, text, tag, control);

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
        this.setLabelColor(null);
        this.setTextColor(Color.WHITE);
        this.filepath = filepath;
    }   

    public void addPropertyChangeListener(PropertyChangeListener observer) {
        observable.addPropertyChangeListener(observer);
    }  

    @Override
    public void onClick() {
        //if savefile doesnt exist, create a blank one
        File savefile = new File(this.filepath);

        if(!savefile.exists()){
            this.setText("No previous save file detected!");
        }
        else {
            super.controller.loadFromFile();
            observable.firePropertyChange("Game Loaded", 0, true);
            super.controller.setActiveStage("Main");
        }
    }
}
