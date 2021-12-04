import adaptors.DogGameFrameLoader;
import usecases.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import adaptors.DogGameController;
import usecases.mainhub.DogGameObject;
import usecases.SpriteFacade;
import usecases.Stage;

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
    private Bank newBank;
    private Stage stage;

    @Before
    public void begin(){
        testController = new DogGameController();
        loader = new DogGameFrameLoader();
        newBank = new Bank();
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-1/src/sprites/dog");
        dogObject = new DogGameObject(0,0,new SpriteFacade(dogFrames),newBank);
        stage = new Stage("Main");
        stage.addGameObject(dogObject);
        testController.addStage("Main", stage);
        testController.setActiveStage("Main");

    }

    @After
    public void endTests(){}

    @Test
    public void testInitialControllerList(){
        // The first clickable initialized
        assert(testController.getActiveStage() != null);

    }

    @Test
    public void testMouseClicked(){
        testController.mouseClicked(10, 10);
        assert(newBank.getCoin() ==1);

    }

    @Test
    public void testInitialGetPressed(){
        assert !testController.getKeyPressed(1);
    }

    @Test
    public void testKeyPressed(){
        testController.keyPressed(2);
        assert testController.getKeyPressed(2);
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



