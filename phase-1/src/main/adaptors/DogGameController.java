package adaptors;

import java.awt.event.MouseEvent;

import usecases.*;

import java.util.HashMap;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang, edited by Juntae
 * @since 9 October 2021
 */
public class DogGameController implements IGameController {
    private HashMap<String, Stage> stages = new HashMap();
    private Stage activeStage = null;
    private IFrameLoader frameLoader = null; // don't worry about the local var thing, for we might access it later
    private Bank bank;

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
    @Override
    public void mouseClicked(MouseEvent e) {
        // loop through game objects in the stage
        for (GameObject go : this.activeStage.getGameObjects()) {
            if (go instanceof Clickable) {
                int x = e.getX();
                int y = e.getY();

                // check if the mouse is on the object
                if (((Clickable) go).clicked(x, y)) {
                    ((Clickable) go).act();
                }
            }
        }

        // loop through text and ui elements in the stage
        for (TextLabel tl : this.activeStage.getTextLabels()) {
            if (tl instanceof Clickable) {
                int x = e.getX();
                int y = e.getY();

                // check if the mouse is on the object
                if (((Clickable) tl).clicked(x, y)) {
                    ((Clickable) tl).act();
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
     * @param s The new stage to add.
     */
    @Override
    public void addStage(String type, Stage s) {
        this.stages.put(type, s);
    }

    /**
     * Sets the active stage. This is the stage whose objects are checked for user interaction.
     * @param type The stage to set as the active one.
     */
    @Override
    public void setActiveStage(String type) {
        this.activeStage = this.stages.get(type);
    }

    /**
     * Returns the active stage.
     * @return The active stage.
     */
    @Override
    public Stage getActiveStage() {
        return this.activeStage;
    }

    /**
     * Adds the bank system to this controller
     * @param b The bank system to add
     */
    @Override
    public void setBank(Bank b) {
        this.bank = b;
    }

    /**
     * Return the bank.
     * @return The bank.
     */
    @Override
    public Bank getBank() {
        return this.bank;
    }
}
