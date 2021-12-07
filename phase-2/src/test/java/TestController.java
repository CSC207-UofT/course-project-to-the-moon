import adaptors.Camera;
import adaptors.DogGameFrameLoader;
import usecases.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import adaptors.DogGameController;
import usecases.mainhub.DogGameObject;
import usecases.SpriteFacade;
import usecases.Stage;
import adaptors.ISoundPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 4 December 2021
 */
public class TestController {
    private DogGameFrameLoader loader;
    private DogGameController testController;
    private DogGameObject dogObject;
    BufferedImage[] dogFrames;
    private Bank newBank;
    private Stage stage;
    private ISoundPlayer isoundPlayer;
    private Camera camera;

    @Before
    public void begin(){
        Rectangle r = new Rectangle(200, 430, 60, 20);
        Stage stage = new Stage("Main");
        camera = new Camera(stage, r);
        testController = new DogGameController();
        testController.addCamera(camera);
        loader = new DogGameFrameLoader();
        newBank = new Bank();
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-2/src/sprites/dog");
        dogObject = new DogGameObject(0,0,new SpriteFacade(dogFrames),newBank, testController);
        isoundPlayer = new ISoundPlayer() {@Override public void play(String name, int loopCount) {}
            @Override public void stop(String name) {}};
        stage = new Stage("Main");
        stage.addGameObject(dogObject);
        testController.addSoundPlayer(isoundPlayer);
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

}



