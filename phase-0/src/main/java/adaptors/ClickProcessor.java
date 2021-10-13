package main.java.adaptors;
import main.java.usecases.DogManager;

/**
 * This class processes the click and returns the object that was clicked on
 * @author Juntae Park
 * @since 12 October 2021
 */

public class ClickProcessor {
    private DogManager manager;

    public ClickProcessor() {
        this.manager = new DogManager();
    }

    public boolean DogClicked(int mouseX, int mouseY) {
        // ONLY checks if dog was clicked, need to expand
        int[][] loc = manager.getLocation();
        return ( (loc[0][0]  <= mouseX && mouseX <= loc[0][1]) && (loc[1][0] <= mouseY && mouseY <= loc[1][1]));
    }
    

}
