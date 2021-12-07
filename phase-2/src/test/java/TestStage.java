import adaptors.DogGameFrameLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.Bank;
import usecases.SpriteFacade;
import usecases.Stage;
import usecases.mainhub.DogGameObject;
import usecases.mainhub.ObserverLabel;
import usecases.object.GameObject;
import usecases.object.TextLabel;
import adaptors.ControllerBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**

 * This is the test class.
 * @author Jimin Song
 * @since 6 December 2021
 */

public class TestStage {
    private Stage testStage;
    private List<GameObject> gameObjects;
    private List<TextLabel> textLabels;
    private DogGameObject dog;
    private DogGameFrameLoader loader;
    private TextLabel coinLabel;

    @Before
    public void begin(){
        testStage = new Stage("Main");
        loader = new DogGameFrameLoader();
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-2/src/sprites/dog");
        dog = new DogGameObject(0,0,new SpriteFacade(dogFrames),new Bank(), null);
        coinLabel = new ObserverLabel(new Rectangle(25, 15, 50, 20), "Coin", "CoinLabel");
    }

    @After
    public void endTests(){}

    @Test
    public void testAddGameObject(){
        testStage.addGameObject(dog);
        gameObjects = testStage.getGameObjects();
        assert (gameObjects.get(0) instanceof DogGameObject);
    }

    @Test
    public void testAddTextLabel(){
        testStage.addTextLabel(coinLabel);
        textLabels = testStage.getTextLabels();
        assert (textLabels.get(0).getTag() == "CoinLabel");
        assert (textLabels.get(0) instanceof ObserverLabel);
    }
}
