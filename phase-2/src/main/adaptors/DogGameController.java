package adaptors;

import usecases.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang, edited by Juntae
 * @since 9 October 2021
 */
public class DogGameController implements IGameController {
    private final String saveFilePath = "phase-2/src/save/savefile.ser";

    private final HashMap<String, Stage> stages = new HashMap<>();
    private final ArrayList<Integer> keysPressedList = new ArrayList<>();
    private Economy econ;
    private Stage activeStage = null;
    private ICamera camera = null;
    private IFrameLoader frameLoader = null;
    private Bank bank = null;
    private int lastPlatform_y;
    private GameReadWriter readWriter;
    private MinigameStageFactory factory = new MinigameStageFactory(frameLoader, stages, lastPlatform_y, bank,
            this);

    public void initializeEcon(){
        this.econ = new Economy();
        econ.addReadWriter(this.readWriter);
    }

    public Economy getEcon(){
        return this.econ;
    }

    /**
     * Adds a gameReadWriter
     * @param grw
     */
    public void addReadWriter(GameReadWriter grw){
        this.readWriter = grw;
    }

    @Override
    public GameReadWriter getReadWriter() {
        return this.readWriter;
    }

    /**
     * Loads the gamestate from the past ser file
     */
    @Override
    public boolean loadFromFile() {
        try {
            File savefile = new File(saveFilePath);

            if(!savefile.exists()){
                return false;
            }
            else {
                this.readWriter.readFromFile();
                this.setActiveStage("Main");
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Loads the gamestate from the past ser file
     */
    @Override
    public void createNewFile() {
        try {
            this.readWriter.saveGame(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a FrameLoader.
     * @param fl The FrameLoader to add.
     */
    public void addFrameLoader(IFrameLoader fl) {
        this.frameLoader = fl;
    }

    /**
     * Adds a bank.
     * @param bank The bank to add.
     */
    public void addBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Adds the camera system to this controller
     * @param camera The camera to add
     */
    @Override
    public void addCamera(ICamera camera) {
        this.camera = camera;
    }

    /**
     * Returns the controller's camera.
     * @return The controller's camera.
     */
    @Override
    public ICamera getCamera() {
        return this.camera;
    }

    /**
     * Processes a mouse click on the screen.
     * @param x The x-coordinate of the click.
     * @param y The y-coordinate of the click.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void mouseClicked(int x, int y) throws IOException,ClassNotFoundException {
        // loop through game objects in the stage
        for (GameObject go : this.activeStage.getGameObjects()) {
            if (go instanceof Clickable) {
                // check if the mouse is on the object
                if (((Clickable) go).isClicked(x, y)) {
                    ((Clickable) go).onClick();
                }
            }
        }

        // loop through text and ui elements in the stage
        for (TextLabel tl : this.activeStage.getTextLabels()) {
            if (tl instanceof Clickable) {
                // check if the mouse is on the object
                if (((Clickable) tl).isClicked(x, y)) {
                    ((Clickable) tl).onClick();
                }
            }
        }
    }

    /**
     * Return whether the given key is pressed.
     * @param c The keycode to check for.
     * @return Whether the key is pressed.
     */
    @Override
    public boolean getKeyPressed(int c) {
        return this.keysPressedList.contains(c);
    }

    /**
     * Processes when a key is pressed.
     * @param code The keycode to process.
     */
    @Override
    public void keyPressed(int code) {
        if (!this.getKeyPressed(code)) {
            // if statement so it doesn't get added multiple times
            this.keysPressedList.add(code);
        }
    }

    /**
     * Process when a key is released.
     * @param code The keycode to process.
     */
    @Override
    public void keyReleased(int code){
        // Remove does nothing if the character isn't in the list
        // needs to be an Integer object, or else it's interpreted as an index
        this.keysPressedList.remove(Integer.valueOf(code));
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
        // create the platformer stage everytime the active stage gets set to platformer
        if (name.equals("Platformer")) {
            factory.addPlatformerStage();
        }
        else if(name.equals("Dino")){
            factory.addDinoStage();

        }
        else {
            // remove it whenever the controller switches from the minigame stage
            // hopefully garbage collection does its thing
            this.stages.remove("Platformer");
            this.stages.remove("Dino");
        }

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



    /**
     * A method which changes the sprite.
     */
    @Override
    public void setDinoSprite(DinoDogGameObject dino, boolean ducked){
        SpriteFacade dogSprite;
        if(ducked){
            BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/dog_duck");
             dogSprite = new SpriteFacade(dogFrames, 2);
        }
        else{
            BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/mini_dog");
             dogSprite = new SpriteFacade(dogFrames, 2);
        }

        dino.setSprite(dogSprite);
    }

    public Bank getBank() {
        return this.bank;
    }

}
