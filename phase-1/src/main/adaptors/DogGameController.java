package adaptors;

import java.awt.event.MouseEvent;
import usecases.Clickable;
import usecases.DogGameObject;
import usecases.GameObject;
import java.util.List;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang, edited by Juntae
 * @since 9 October 2021
 */
public class DogGameController {
    private List<GameObject> mainStage = null; // In the future, we may have a List of stages instead
    private IFrameLoader frameLoader = null; // don't worry about the local var thing, for we might access it later

    /**
     * Adds a new stage to this controller.
     * @param s The new stage to add.
     */
    public void addStage(List<GameObject> s) {
        this.mainStage = s;
        // TODO: n the future, make this add the stage to the list of stages
    }

    /**
     * Adds a new implementation of IFrameLoader for this controller to use.
     * @param fl The implementation of IFrameLoader to add.
     */
    public void addFrameLoader(IFrameLoader fl) {
        // It takes in an interface, not a concrete class! So it's not a dependency violation!
        // Modern problems require modern solutions
        this.frameLoader = fl;
    }

    /**
     * Processes a mouse click on the screen.
     * @param e The given MouseEvent.
     */
    public void mouseClicked(MouseEvent e) {
        // loop through game objects in the stage
        for (GameObject go : this.mainStage) {
            if (go instanceof Clickable) {
                int x = e.getX();
                int y = e.getY();

                // check if the mouse is on the object
                if (((Clickable) go).clicked(x, y)) {
                    ((Clickable) go).act();

                    if (go instanceof DogGameObject) {
                        System.out.println("Got " + ((DogGameObject) go).getCoinsEarnedFromLastPet());
                        // TODO: make the coins storage class, when we make that, increase by this amount
                    }
                }
            }
        }
    }
}
