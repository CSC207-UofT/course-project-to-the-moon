import adaptors.DogGameFrameLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.MinigameButton;
import usecases.Stage;
import adaptors.DogGameController;
import adaptors.Camera;
import usecases.Bank;
import adaptors.DogGameFrameLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**

 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */

public class TestMinigameButton {
    private MinigameButton testMinigameButton;
    private Stage stage;
    private Rectangle rectangle;
    private DogGameController controller;
    private Camera camera;
    private Bank bank;
    private DogGameFrameLoader fl;

    @Before
    public void begin(){
        rectangle = new Rectangle(200, 430, 60, 20);
        stage = new Stage("Main");
        camera = new Camera(stage, rectangle);
        bank = new Bank();
        fl = new DogGameFrameLoader();
        controller = new DogGameController();
        controller.addStage("Main", stage);
        controller.addFrameLoader(fl);
        controller.addBank(bank);
        controller.addCamera(camera);
        controller.setActiveStage("Main");
        testMinigameButton = new MinigameButton(rectangle, "text", "tag", controller);

    }

    @After
    public void endTests(){}

    @Test
    public void testChangeStage(){
        // check stage changed or not
        testMinigameButton.onClick();
        assert (stage != controller.getActiveStage());
    }

}