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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TestCamera {
    private DogGameFrameLoader loader;
    private DogGameController testController;
    private DogGameObject dogObject;
    private Stage stage;
    private DogGameFrameLoader frameLoader;
    private Camera camera;
    BufferedImage[] dogFrames;

    @Before
    public void begin(){
        testController = new DogGameController();
        loader = new DogGameFrameLoader();
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-1/src/sprites/dog");
        dogObject = new DogGameObject(0,0,new SpriteFacade(dogFrames),new Bank(), null);
        stage = new Stage("Main");

        camera = new Camera(stage, new Rectangle(0, 0, 1000, 1000));
        frameLoader = new DogGameFrameLoader();

    }

    @After
    public void endTests(){}

    @Test
    public void testGetDrawableInBounds(){
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames);

        DogGameObject dogObj = new DogGameObject(0, 0, dogSprite ,new Bank(), null);
        PlatformGameObject platform = new PlatformGameObject(300, 400, "example", dogSprite);
        stage.addGameObject(dogObj);
        stage.addGameObject(platform);
        testController.addStage("Main", stage);
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
