import adaptors.DogGameController;
import adaptors.DogGameFrameLoader;
import adaptors.ISoundPlayer;
import usecases.Bank;
import frameworkanddrivers.DogGameJPanel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.mainhub.DogGameObject;
import usecases.SpriteFacade;
import usecases.Stage;

import java.awt.image.BufferedImage;

/**
 * This is the test class.
 * @author Aria edited by Jimin
 * @since 4 December 2021
 */

public class TestDogGameJPanel {
    private DogGameFrameLoader loader;
    private DogGameController testController;
    private DogGameObject dogObject;
    private BufferedImage[] dogFrames;
    private DogGameJPanel panel;
    private ISoundPlayer isoundPlayer;


    @Before
    public void begin(){
        testController = new DogGameController();
        loader = new DogGameFrameLoader();
        isoundPlayer = new ISoundPlayer() {@Override public void play(String name, int loopCount) {}
            @Override public void stop(String name) {}};
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-2/src/sprites/dog");
        dogObject = new DogGameObject(0,0,new SpriteFacade(dogFrames),new Bank(), null);
        testController.addSoundPlayer(isoundPlayer);
        testController.addStage("Main", new Stage("Main"));
        testController.setActiveStage("Main");
        panel = new DogGameJPanel(100, 100);


    }

    @After
    public void endTests(){}

    @Test
    public void testAddController(){
        panel.addController(testController);
        assert (panel.getKeyListeners().length == 1 && panel.getMouseListeners().length ==1);
    }


}
