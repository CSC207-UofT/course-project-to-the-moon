package adaptors;

import usecases.*;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang, edited by Juntae
 * @since 9 October 2021
 */
public class DogGameController implements IGameController {
    private final HashMap<String, Stage> stages = new HashMap<>();
    private final ArrayList<Integer> keysPressedList = new ArrayList<>();
    private Stage activeStage = null;
    private ICamera camera = null;
    private IFrameLoader frameLoader = null;
    private Bank bank = null;
    private int lastPlatform_y;

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
     */
    @Override
    public void mouseClicked(int x, int y) {
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
            this.addPlatformerStage();
        }
        else if(name.equals("Dino")){
            this.addDinoStage();

        }
        else {
            // remove it whenever the controller switches from the minigame stage
            // hopefully garbage collection does its thing
            this.stages.remove("Platformer");
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
     * Returns a stage given a name.
     * @return The stage with the given name.
     */
    @Override
    public Stage getStage(String name) {
        return this.stages.get(name);
    }

    // TODO: maybe in phase 2, delegate the minigame to its own class?

    /**
     * A method which creates the minigame's stage.
     */
    private void addPlatformerStage(){
        // Assume (300, 500)
        Stage platformerStage = new Stage("Platformer");

        // Add background sprite.
        // “R/Pixelart - Space Background.” Reddit, https://www.reddit.com/r/PixelArt/comments/f1wg26/space_background/.
        BufferedImage[] bgFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/minigame_bg");
        int bg_y = 0;
        for (int i=0; i<30; i++) {
            SpriteFacade bgSprite = new SpriteFacade(bgFrames, 2);
            PlatformGameObject bgObject = new PlatformGameObject(0, bg_y, "Background", bgSprite);
            platformerStage.addGameObject(bgObject);
            bg_y -= 640;
        }

        this.stages.put("Platformer", platformerStage);

        PlatformDogGameObject miniDog = createMiniDog();
        platformerStage.addGameObject(miniDog);

        // add the first few platforms
        addRandomPlatforms(platformerStage);

        // Add moon at the top
        // “Full Moon Stock Image. Image of Astronaut, Satelite, Orbit - 22403.” Dreamstime, 10 Sept. 2004, https://www.dreamstime.com/stock-photos-full-moon-image22403.
        BufferedImage[] moonFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/moon");
        SpriteFacade moonSprite = new SpriteFacade(moonFrames, 2);
        PlatformGameObject moonObject = new PlatformGameObject(100, this.lastPlatform_y - 180, "Moon", moonSprite);
        platformerStage.addGameObject(moonObject);
    }
    private void addDinoStage(){
        // Assume (300, 500)
        Stage dinoStage = new Stage("Dino");
        this.stages.put("Dino", dinoStage);

        DinoDogGameObject miniDog = createDinoDog();
        dinoStage.addGameObject(miniDog);

        // add the first few platforms
        addHorizontalPlatforms(dinoStage);
    }

    /**
     * Helper method to create a single default dog.
     * @return The dog.
     */
    private PlatformDogGameObject createMiniDog() {
        // create the minigame dog object
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/dog_shrunk");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        return new PlatformDogGameObject(100, 210, dogSprite, bank,this);
    }
    /**
     * Helper method to create a single default dino dog.
     * @return The dino dog.
     */
    private DinoDogGameObject createDinoDog() {
        // create the minigame dog object
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/mini_dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        return new DinoDogGameObject(100, 210, dogSprite, bank,this);
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

    /**
     * A method which takes a minigame stage,
     * and adds 100 random platforms to it,
     * which all have a horizontal distance from MIN_PLATFORM_DISTANCE to MAX_PLATFORM_DISTANCE.
     * @param platformerStage the stage that is added to.
     */
    private void addHorizontalPlatforms(Stage platformerStage){
        final int MAX_PLATFORM_DISTANCE = 270;
        final int MIN_PLATFORM_DISTANCE = 220;
        final int NUM_PLATFORMS = 100;

        Random random = new Random();
        int previousY = 300;  // the Y-coordinate of the previous platform
        int previousX = 70;  // the Y-coordinate of the previous platform


        BufferedImage[] platFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/horizontal_platform");
        SpriteFacade platformSprite = new SpriteFacade(platFrames);



        synchronized (platformerStage) {
            for (int i = 0; i < NUM_PLATFORMS - 2; i++) {
                PlatformGameObject newPlatform;

                int rX = random.nextInt(MAX_PLATFORM_DISTANCE - MIN_PLATFORM_DISTANCE + 1) + MIN_PLATFORM_DISTANCE;
                // Random number between MIN_PLATFORM_DISTANCE and MAX_PLATFORM_DISTANCE
                int rY = random.nextInt(50) +160;
                int newX = previousX + rX;
                // start is 300 y

                newPlatform = new PlatformGameObject(newX, rY, "Platform", platformSprite);
                previousX = newX;

                platformerStage.addGameObject(newPlatform);
            }
        }

        BufferedImage[] winningPlatFrames = this.frameLoader.loadFramesFromFolder(
                "phase-1/src/sprites/winning_platform");
        SpriteFacade winningPlatformSprite = new SpriteFacade(winningPlatFrames);
        PlatformGameObject winningPlatform = new PlatformGameObject(random.nextInt(250),
                previousY - MAX_PLATFORM_DISTANCE, "WinningPlatform", winningPlatformSprite);

        platformerStage.addGameObject(winningPlatform);
    }
    private void addRandomPlatforms(Stage platformerStage){
        final int MAX_PLATFORM_DISTANCE = 100;
        final int MIN_PLATFORM_DISTANCE = 40;
        final int NUM_PLATFORMS = 100;

        Random random = new Random();
        int previousY = 300;  // the y-coordinate of the previous platform

        BufferedImage[] platFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/platform");
        SpriteFacade platformSprite = new SpriteFacade(platFrames);

        // the first platform should be under the dog
        PlatformGameObject firstPlatform = new PlatformGameObject(70, previousY, "Platform", platformSprite);
        platformerStage.addGameObject(firstPlatform);

        synchronized (platformerStage) {
            for (int i = 0; i < NUM_PLATFORMS - 2; i++) {
                PlatformGameObject newPlatform;

                int rX = random.nextInt(220);
                // Random number between MIN_PLATFORM_DISTANCE and MAX_PLATFORM_DISTANCE
                int rY = random.nextInt(MAX_PLATFORM_DISTANCE - MIN_PLATFORM_DISTANCE + 1) + MIN_PLATFORM_DISTANCE;
                int newY = previousY - rY;

                newPlatform = new PlatformGameObject(rX, newY, "Platform", platformSprite);
                previousY = newY;

                platformerStage.addGameObject(newPlatform);
            }
        }

        BufferedImage[] winningPlatFrames = this.frameLoader.loadFramesFromFolder(
                "phase-1/src/sprites/winning_platform");
        SpriteFacade winningPlatformSprite = new SpriteFacade(winningPlatFrames);
        PlatformGameObject winningPlatform = new PlatformGameObject(random.nextInt(250),
                previousY - MAX_PLATFORM_DISTANCE, "WinningPlatform", winningPlatformSprite);

        this.lastPlatform_y = previousY - MAX_PLATFORM_DISTANCE;
        platformerStage.addGameObject(winningPlatform);
    }

    public Bank getBank() {
        return this.bank;
    }

}
