import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programdrivers.DogGame;
import adaptors.DogGameController;
import usecases.Stage;

import java.awt.*;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestController {
    private DogGame testGame;
    private DogManager manager;
    private Dog testDog;
    private DogGameController testController;

    @Before
    public void begin(){
        testController = new DogGameController();
    }

    @After
    public void endTests(){}

    @Test
    public void testInitialControllerList(){
        // The first clickable initialized
        // Must be a DogManager
        assert controller.getClickables().get(0) instanceof DogManager;

    }

    @Test
    public void testAddMinigame(){
        // test minigame is built and the number of platforms.
        testController.addMinigameStage();
        Stage testStage = testController.getStage("Minigame");
        assert (testStage.getGameObjects().size == 100);
    }

    @Test
    public void testCreateMiniDog(){
        // test minidog is created
        PlatformDogGameObject platform = testController.createMiniDog();
        assert (platform.getX() == 100 && platform.getY() == 210);
    }
}



