import adaptors.DogGameFrameLoader;
import adaptors.ISoundPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.dinominigame.DinoButton;
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

public class TestDinoButton {
    private DinoButton testDinoButton;
    private Stage stage;
    private Rectangle rectangle;
    private DogGameController controller;
    private Camera camera;
    private Bank bank;
    private DogGameFrameLoader fl;
    private ISoundPlayer isoundPlayer;
    private Stage dinoStage;

    @Before
    public void begin(){
        rectangle = new Rectangle(200, 430, 60, 20);
        stage = new Stage("Main");
        dinoStage = new Stage("Dino");
        camera = new Camera(stage, rectangle);
        bank = new Bank();
        fl = new DogGameFrameLoader();
        isoundPlayer = new ISoundPlayer() {@Override public void play(String name, int loopCount) {}
            @Override public void stop(String name) {}};
        controller = new DogGameController();
        controller.addStage("Main", stage);
        controller.addStage("Dino", dinoStage);
        controller.addSoundPlayer(isoundPlayer);
        controller.addFrameLoader(fl);
        controller.addBank(bank);
        controller.addCamera(camera);
        controller.setActiveStage("Main");
        testDinoButton = new DinoButton(rectangle, "text", "tag", controller);

    }

    @After
    public void endTests(){}

    @Test
    public void testDinoButton(){
        // to check the stage changed
        testDinoButton.onClick();
        assert (stage != controller.getActiveStage());
    }

}