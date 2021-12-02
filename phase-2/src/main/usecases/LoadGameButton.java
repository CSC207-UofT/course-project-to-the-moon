package usecases;
import adaptors.IGameController;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoadGameButton  extends TextButton{
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
        
        this.filepath = filepath;
        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
        this.setLabelColor(null);
        this.setTextColor(Color.WHITE);
    }   

    @Override
    public void onClick() throws IOException, ClassNotFoundException {
        //if savefile doesnt exist, create a blank one
        File savefile = new File(this.filepath);
        
        if(!savefile.exists()){
            this.setText("No previous save file detected!");
        }
        else {
            super.controller.loadFromFile();
            super.controller.setActiveStage("Main");
        }
    }
}