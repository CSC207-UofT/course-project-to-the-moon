package adaptors;

import java.awt.event.MouseEvent;

import entities.Bank;
import usecases.*;

import java.util.HashMap;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang, edited by Juntae
 * @since 9 October 2021
 */
public class DogGameController implements IGameController {
    private final HashMap<String, Stage> stages = new HashMap<>();
    private Stage activeStage = null;
    private IFrameLoader frameLoader = null; // don't worry about the local var thing, for we might access it later
    private Bank bank;
    private ICamera camera;

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
     * Adds the camera system to this controller
     * @param camera The camera to add
     */
    public void addCamera(ICamera camera) {
        this.camera = camera;
    }

    /**
     * Adds the bank system to this controller
     * @param b The bank system to add
     */
    public void setBank(Bank b) {
        this.bank = b;
    }

    /**
     * Return the bank.
     * @return The bank.
     */
    public Bank getBank() {
        return this.bank;
    }

    /**
     * Processes a mouse click on the screen.
     * @param e The given MouseEvent.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // loop through game objects in the stage
        for (GameObject go : this.activeStage.getGameObjects()) {
            if (go instanceof Clickable) {
                int x = e.getX();
                int y = e.getY();

                // check if the mouse is on the object
                if (((Clickable) go).isClicked(x, y)) {
                    ((Clickable) go).onClick();
                }
            }
        }

        // loop through text and ui elements in the stage
        for (TextLabel tl : this.activeStage.getTextLabels()) {
            if (tl instanceof Clickable) {
                int x = e.getX();
                int y = e.getY();

                // check if the mouse is on the object
                if (((Clickable) tl).isClicked(x, y)) {
                    ((Clickable) tl).onClick();
                }
            }
        }
    }

    /**
     * Return whether the given key is pressed.
     * @param c The key's character.
     * @return Whether the key is pressed.
     */
    @Override
    public boolean getKeyPressed(char c) {
        return true;
        // TODO: implement making the controller know which keys are pressed
    }

    /**
     * Adds a new stage to this controller.
     * @param name The name of the stage.
     * @param s The new stage to add.
     */
    @Override
    public void addStage(String name, Stage s) {
        this.stages.put(name, s);
    }
    /**
     * Sets the active stage. This is the stage whose objects are checked for user interaction.
     * @param name The name of the stage to set as the active one.
     */
    @Override
    public void setActiveStage(String name) {
        this.activeStage = this.stages.get(name);

        if (camera != null) {
            camera.setStage(this.activeStage);
        }
    }

    /**
     * Returns the active stage.
     * @return The active stage.
     */
    @Override
    public Stage getActiveStage() {
        return this.activeStage;
    }
}
