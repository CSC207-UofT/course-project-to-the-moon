import adaptors.Camera;
import adaptors.DogGameController;
import adaptors.DogGameFrameLoader;
import usecases.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.*;
import usecases.mainhub.DogGameObject;
import usecases.platformerminigame.PlatformGameObject;
import adaptors.ISoundPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This is the test class.
 * @author Aria edited by Jimin
 * @since 4 December 2021
 */
public class TestCamera {
    private DogGameFrameLoader loader;
    private DogGameController testController;
    private DogGameObject dogObject;
    private ISoundPlayer isoundPlayer;
    private Stage stage;
    private DogGameFrameLoader frameLoader;
    private Camera camera;
    BufferedImage[] dogFrames;

    @Before
    public void begin(){
        testController = new DogGameController();
        loader = new DogGameFrameLoader();
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-2/src/sprites/dog");
        dogObject = new DogGameObject(0,0,new SpriteFacade(dogFrames),new Bank(), null);
        isoundPlayer = new ISoundPlayer() {@Override public void play(String name, int loopCount) {}
            @Override public void stop(String name) {}};
        stage = new Stage("Main");

        camera = new Camera(stage, new Rectangle(0, 0, 1000, 1000));
        frameLoader = new DogGameFrameLoader();

    }

    @After
    public void endTests(){}

    @Test
    public void testGetDrawableInBounds(){
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames);

        DogGameObject dogObj = new DogGameObject(0, 0, dogSprite ,new Bank(), null);
        PlatformGameObject platform = new PlatformGameObject(300, 400, "example", dogSprite);
        stage.addGameObject(dogObj);
        stage.addGameObject(platform);
        testController.addStage("Main", stage);
        testController.addSoundPlayer(isoundPlayer);
        testController.setActiveStage("Main");
        testController.addCamera(camera);
        camera.setStage(stage);


         ArrayList list = (ArrayList) camera.getDrawableObjectsInBounds();
         assert (list.get(0) instanceof DogGameObject  && list.get(1) instanceof PlatformGameObject);

    }

    @Test
    public void testInitialTextLabels(){
        assert camera.getTextLabels().size() ==0;
    }

    @Test
    public void testGetTransform(){
        assert camera.getTransform().getX() == 0 && camera.getTransform().getY() == 0;
    }
}
