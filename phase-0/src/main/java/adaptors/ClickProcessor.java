package main.java.adaptors;
import main.java.usecases.Clickable;

/**
 * This class processes the click and returns the object that was clicked on
 * @author Juntae Park
 * @since 12 October 2021
 */

public class ClickProcessor {
    private Clickable[] managerList;

    public ClickProcessor(Clickable[] managerList) {
        this.managerList = managerList;
    }

    public Clickable getClicked(int mouseX, int mouseY) {
        for(Clickable manager : this.managerList) {
            if (manager.clicked(mouseX, mouseY)) {return manager;}
        }
        return null;
    }

    
    // To-Do: implement quadtree for more efficient click detection


}
