package usecases;

import adaptors.IFrameLoader;
import adaptors.IGameController;
import usecases.dinominigame.DinoDogGameObject;
import usecases.platformerminigame.PlatformDogGameObject;
import usecases.platformerminigame.PlatformGameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a factory to build the minigames for the dog game.
 * @author Aria, Jimin, Praket
 * @since 4 December 2021
 */
public class MinigameStageFactory {
    private int lastPlatform_y;
    private final IGameController controller;

    /**
     * Initialize a new Minigame Stage Factory
     * @param controller The controller to use (dependency inversion).
     */
    public MinigameStageFactory(IGameController controller){
        this.controller = controller;
    }

    /**
     * The main factory method to create a minigame stage based on the string given.
     * @param name The name of the minigame.
     * @param fl The frame loader to use.
     * @param bank The bank to use.
     * @return The minigame stage.
     */
    public Stage getMinigameStage(String name, IFrameLoader fl, Bank bank) {
        if (name.equals("Platformer")) {
            return this.createPlatformerStage(fl, bank);
        } else if (name.equals("Dino")) {
            return this.createDinoStage(fl, bank);
        } return null;
    }

    /**
     * A method which creates the platformer stage.
     * @param frameLoader The frame loader to use.
     * @param bank The bank to use.
     * @return The platformer stage.
     */
    private Stage createPlatformerStage(IFrameLoader frameLoader, Bank bank) {
        // Assume (300, 500)
        Stage platformerStage = new Stage("Platformer");
        // Add background sprite.
        // “R/Pixelart - Space Background.” Reddit, https://www.reddit.com/r/PixelArt/comments/f1wg26/space_background/.
        BufferedImage[] bgFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/minigame_bg");
        int bg_y = 0;
        for (int i = 0; i < 30; i++) {
            SpriteFacade bgSprite = new SpriteFacade(bgFrames, 2);
            PlatformGameObject bgObject = new PlatformGameObject(0, bg_y, "Background", bgSprite);
            platformerStage.addGameObject(bgObject);
            bg_y -= 640;
        }

        PlatformDogGameObject miniDog = createPlatformDog(platformerStage, frameLoader, bank);
        platformerStage.addGameObject(miniDog);

        // add the first few platforms
        addRandomPlatforms(platformerStage, frameLoader);

        // Add moon at the top
        // “Full Moon Stock Image. Image of Astronaut, Satelite, Orbit - 22403.”
        // Dreamstime, 10 Sept. 2004, https://www.dreamstime.com/stock-photos-full-moon-image22403.
        BufferedImage[] moonFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/moon");
        SpriteFacade moonSprite = new SpriteFacade(moonFrames, 2);
        PlatformGameObject moonObject = new PlatformGameObject(100, this.lastPlatform_y - 180, "Moon", moonSprite);
        platformerStage.addGameObject(moonObject);

        return platformerStage;
    }

    /**
     * Create a dino minigame stage, like the Google Chrome dino minigame.
     * @param frameLoader The frame loader to use.
     * @param bank The bank to use.
     * @return The dini minigame stage.
     */
    private Stage createDinoStage(IFrameLoader frameLoader, Bank bank){
        // Assume (300, 500)
        Stage dinoStage = new Stage("Dino");

        // Add background to the dinogame
        // IceColdClaws. (n.d.). Backdrop: Grass field by icecoldclaws on DeviantArt. by IceColdClaws on DeviantArt.
        // Retrieved December 8, 2021, from https://www.deviantart.com/icecoldclaws/art/Backdrop-Grass-Field-635951043.
        BufferedImage[] bgFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/dinogame_bg");
        int bg_x = 0;
        for (int i = 0; i < 100; i++) {
            SpriteFacade bgSprite = new SpriteFacade(bgFrames, 1);
            PlatformGameObject bgObject = new PlatformGameObject(bg_x, 0, "Background", bgSprite);
            dinoStage.addGameObject(bgObject);
            bg_x += 500;
        }

        DinoDogGameObject miniDog = createDinoDog(dinoStage, frameLoader, bank);
        dinoStage.addGameObject(miniDog);

        // add the first few platforms
        addHorizontalPlatforms(dinoStage, frameLoader);

        return dinoStage;
    }

    /**
     * Helper method to create a dog for the platformer minigame.
     * @param stage The stage to use.
     * @return The dog.
     */
    private PlatformDogGameObject createPlatformDog(Stage stage, IFrameLoader frameLoader, Bank bank) {
        // create the platformer dog object
        BufferedImage[] dogFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/dog_shrunk");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        return new PlatformDogGameObject(100, 210, dogSprite, bank, stage, this.controller);
    }
    /**
     * Helper method to create a dog for the dino minigame.
     * @param stage The stage to use.
     * @return The dino dog.
     */
    private DinoDogGameObject createDinoDog(Stage stage, IFrameLoader frameLoader, Bank bank) {
        // create the dino dog object
        BufferedImage[] dogFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/mini_dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        BufferedImage[] duckingFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/dog_duck");
        SpriteFacade duckingDog = new SpriteFacade(duckingFrames, 2);

        ArrayList<SpriteFacade> dogSprites = new ArrayList<>();
        dogSprites.add(dogSprite);
        dogSprites.add(duckingDog);

        return new DinoDogGameObject(100, 363, dogSprites, bank, stage, this.controller);
    }

    /**
     * A method which takes a minigame stage,
     * and adds 100 random platforms to it,
     * which all have a horizontal distance from MIN_PLATFORM_DISTANCE to MAX_PLATFORM_DISTANCE.
     * @param platformerStage the stage that is added to.
     */
    private void addHorizontalPlatforms(Stage platformerStage, IFrameLoader frameLoader){
        final int MAX_PLATFORM_DISTANCE = 270;
        final int MIN_PLATFORM_DISTANCE = 220;
        final int NUM_PLATFORMS = 100;

        Random random = new Random();
        int previousX = 70;  // the Y-coordinate of the previous platform

        BufferedImage[] platFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/horizontal_platform");
        SpriteFacade platformSprite = new SpriteFacade(platFrames);


        synchronized (platformerStage) {
            for (int i = 0; i < NUM_PLATFORMS - 2; i++) {
                PlatformGameObject newPlatform;

                int rX = random.nextInt(MAX_PLATFORM_DISTANCE - MIN_PLATFORM_DISTANCE + 1) + MIN_PLATFORM_DISTANCE;
                // Random number between MIN_PLATFORM_DISTANCE and MAX_PLATFORM_DISTANCE
                int rY = random.nextInt(75) +290;
                int newX = previousX + rX;
                // start is 300 y

                newPlatform = new PlatformGameObject(newX, rY, "Platform", platformSprite);
                previousX = newX;

                platformerStage.addGameObject(newPlatform);
            }
        }

        BufferedImage[] winningPlatFrames = frameLoader.loadFramesFromFolder(
                "phase-2/src/sprites/finish_line");
        SpriteFacade winningPlatformSprite = new SpriteFacade(winningPlatFrames);
        PlatformGameObject finish_flag = new PlatformGameObject(27000,
                200, "flag", winningPlatformSprite);

        platformerStage.addGameObject(finish_flag);
    }
    private void addRandomPlatforms(Stage platformerStage, IFrameLoader frameLoader){
        final int MAX_PLATFORM_DISTANCE = 100;
        final int MIN_PLATFORM_DISTANCE = 40;
        final int NUM_PLATFORMS = 100;

        Random random = new Random();
        int previousY = 300;  // the y-coordinate of the previous platform

        BufferedImage[] platFrames = frameLoader.loadFramesFromFolder("phase-2/src/sprites/platform");
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

        BufferedImage[] winningPlatFrames = frameLoader.loadFramesFromFolder(
                "phase-2/src/sprites/winning_platform");
        SpriteFacade winningPlatformSprite = new SpriteFacade(winningPlatFrames);
        PlatformGameObject winningPlatform = new PlatformGameObject(random.nextInt(250),
                previousY - MAX_PLATFORM_DISTANCE, "WinningPlatform", winningPlatformSprite);

        this.lastPlatform_y = previousY - MAX_PLATFORM_DISTANCE;
        platformerStage.addGameObject(winningPlatform);
    }
}
