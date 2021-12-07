import adaptors.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.*;
import usecases.mainhub.*;
import usecases.object.TextLabel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 4 December 2021
 */

public class TestControllerBuilder {
    private Bank bank;
    private DogGameController controller;
    private DogGameFrameLoader frameLoader;
    private ISoundPlayer isoundPlayer;
    private GameReadWriter gameReadWriter;
    private ControllerBuilder builder;
    private Camera camera;

    @Before
    public void begin() throws IOException {
        bank = new Bank();
        Rectangle r = new Rectangle(200, 430, 60, 20);
        Stage stage = new Stage("Main");
        camera = new Camera(stage, r);
        Rectangle cameraBounds = new Rectangle(0, 0, 300, 500);
        isoundPlayer = new ISoundPlayer() {@Override public void play(String name, int loopCount) {}
            @Override public void stop(String name) {}};
        gameReadWriter = new GameReadWriter("phase-2/src/save/savefile.ser");
        frameLoader = new DogGameFrameLoader();
        builder = new ControllerBuilder(frameLoader, cameraBounds, gameReadWriter, isoundPlayer);
        controller = new DogGameController();
        controller.addCamera(camera);
        frameLoader = new DogGameFrameLoader();
        controller.addFrameLoader(frameLoader);
        controller.addSoundPlayer(isoundPlayer);
        controller.addReadWriter(gameReadWriter);
//        controller.addBank(builder.getController().getBank());
        controller.initializeEcon();
    }

    @After
    public void endTests(){}

    @Test
    public void testCreateDog(){
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-2/src/sprites/dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);
        DogGameObject dog = new DogGameObject(50, 100, dogSprite, this.bank, this.controller);
        assert (dog.getX() == 50 & dog.getY() == 100);
    }

    @Test
    public void testMainStage(){
        Stage mainStage = new Stage("Main");
        TextLabel coinLabel = new ObserverLabel(new Rectangle(25, 15, 50, 20),
                "Coins: 0", "CoinLabel");
        coinLabel.setLabelColor(null);
        coinLabel.setTextColor(Color.WHITE);
        mainStage.addTextLabel(coinLabel);
        bank.addPropertyChangeListener((PropertyChangeListener) coinLabel);
        ShopButton shop = new ShopButton(new Rectangle(190, 400, 80, 20),
                "Shop", "Shop", this.controller);
        MinigameSelectionButton minigameSelection = new MinigameSelectionButton(new Rectangle(190, 430,
                80, 20), "Minigames", "MinigameSelection", this.controller);
        mainStage.addTextLabel(shop);
        mainStage.addTextLabel(minigameSelection);
        assert (shop.getX() == 190 & shop.getY()==400);
    }


}
