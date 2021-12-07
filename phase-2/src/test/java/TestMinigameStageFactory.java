import adaptors.*;
import usecases.Bank;
import usecases.MinigameStageFactory;
import usecases.Stage;
import usecases.dinominigame.DinoDogGameObject;
import usecases.platformerminigame.PlatformDogGameObject;
import usecases.platformerminigame.PlatformGameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 5 December 2021
 */

public class TestMinigameStageFactory {
    private IGameController controller;
    private IFrameLoader iframeLoader;
    private Bank bank;
    private MinigameStageFactory minigameStageFactory;
    private Camera camera;

    @Before
    public void begin(){
        Rectangle r = new Rectangle(200, 430, 60, 20);
        Stage stage = new Stage("Main");
        camera = new Camera(stage, r);
        controller = new DogGameController();
        controller.addCamera(camera);
        iframeLoader = new DogGameFrameLoader();
        bank = new Bank();
        minigameStageFactory = new MinigameStageFactory(controller);
    }

    @After
    public void endTests(){}

    @Test
    public void testPlatformerStage(){
        Stage stage = minigameStageFactory.createPlatformerStage(iframeLoader, bank);
        assert (0 < stage.getGameObjects().size());
    }

    @Test
    public void testDinoStage(){
        Stage stage = minigameStageFactory.createDinoStage(iframeLoader, bank);
        assert (0 < stage.getGameObjects().size());
    }
}
