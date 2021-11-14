import adaptors.DogGameFrameLoader;
import entities.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import adaptors.DogGameController;
import usecases.DogGameObject;
import usecases.SpriteFacade;
import usecases.Stage;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestController {
    private DogGameFrameLoader loader;
    private DogGameController testController;
    private DogGameObject dogObject;
    BufferedImage[] dogFrames;

    @Before
    public void begin(){
        testController = new DogGameController();
        loader = new DogGameFrameLoader();
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-1/src/sprites/dog");
        dogObject = new DogGameObject(0,0,new SpriteFacade(dogFrames),new Bank());
        testController.addStage("Main", new Stage("Main"));
        testController.setActiveStage("Main");

    }

    @After
    public void endTests(){}

    @Test
    public void testInitialControllerList(){
        // The first clickable initialized
        assert(testController.getActiveStage() != null);

    }

//    @Test
//    public void testAddMinigame(){
//        // test minigame is built and the number of platforms.
//        testController.
//        Stage testStage = testController.getStage("Minigame");
//        assert (testStage.getGameObjects().size == 100);
//    }
//
//    @Test
//    public void testCreateMiniDog(){
//        // test minidog is created properly.
//        PlatformDogGameObject platform = testController.createMiniDog();
//        assert (platform.getX() == 100 && platform.getY() == 210);
//    }
}



