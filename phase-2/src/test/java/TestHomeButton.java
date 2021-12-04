import adaptors.DogGameFrameLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.mainhub.HomeButton;
import usecases.Stage;
import adaptors.DogGameController;
import adaptors.Camera;
import usecases.Bank;

import java.awt.*;

/**

 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */

public class TestHomeButton {
    private HomeButton testHomeButton;
    private Stage stage;
    private Rectangle rectangle;
    private DogGameController controller;
    private Camera camera;
    private Bank bank;
    private DogGameFrameLoader fl;

    @Before
    public void begin(){
        rectangle = new Rectangle(200, 430, 60, 20);
        stage = new Stage("Minigame");
        camera = new Camera(stage, rectangle);
        bank = new Bank();
        fl = new DogGameFrameLoader();
        controller = new DogGameController();
        controller.addStage("Minigame", stage);
        controller.addFrameLoader(fl);
        controller.addBank(bank);
        controller.addCamera(camera);
        controller.setActiveStage("Minigame");
        testHomeButton = new HomeButton(rectangle, "text", "tag", controller);

    }

    @After
    public void endTests(){}

    @Test
    public void testChangeStage(){
        // check stage changed or not
        testHomeButton.onClick();
        assert (stage != controller.getActiveStage());
    }

}