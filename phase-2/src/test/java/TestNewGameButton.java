import adaptors.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.SpriteFacade;
import usecases.mainhub.DogGameObject;
import usecases.mainhub.NewGameButton;
import usecases.Stage;
import usecases.Bank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**

 * This is the test class.
 * @author Jimin Song
 * @since 6 December 2021
 */

public class TestNewGameButton {
    private NewGameButton testNewGameButton;
    private Stage stage;
    private Rectangle rectangle;
    private DogGameController controller;
    private Camera camera;
    private Bank bank;
    private DogGameFrameLoader fl;
    private ISoundPlayer isoundPlayer;
    private GameReadWriter gameReadWriter;
    private DogGameObject dogGameObject;

    @Before
    public void begin() throws IOException {
        rectangle = new Rectangle(200, 430, 60, 20);
        gameReadWriter = new GameReadWriter("phase-2/src/save/savefile.ser");
        stage = new Stage("Main");
        camera = new Camera(stage, rectangle);
        bank = new Bank();
        controller = new DogGameController();
        gameReadWriter.addBank(bank);
        fl = new DogGameFrameLoader();
        BufferedImage[] dogFrames = fl.loadFramesFromFolder("phase-2/src/sprites/dog");
        dogGameObject = new DogGameObject(0,0,new SpriteFacade(dogFrames), bank, controller);
        gameReadWriter.addDog(dogGameObject);
        isoundPlayer = new ISoundPlayer() {@Override public void play(String name, int loopCount) {}
            @Override public void stop(String name) {}};
        controller.addStage("Main", stage);
        controller.addReadWriter(gameReadWriter);
        controller.addSoundPlayer(isoundPlayer);
        controller.addFrameLoader(fl);
        controller.addBank(bank);
        controller.addCamera(camera);
        testNewGameButton = new NewGameButton(rectangle, "text", "tag", controller);

    }

    @After
    public void endTests(){}

    @Test
    public void testNewGameButton(){
        testNewGameButton.onClick();
        assert (controller.getActiveStage().getStageName() == "Main");
    }

}