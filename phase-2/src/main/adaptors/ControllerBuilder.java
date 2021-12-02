package adaptors;

import adaptors.DogGameController;
import adaptors.DogGameFrameLoader;
import adaptors.ICamera;
import entities.*;
import usecases.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;

public class ControllerBuilder {
    private Stage mainStage;
    private Stage shopStage;
    private ICamera camera;
    private final DogGameController controller = new DogGameController();
    private final DogGameFrameLoader frameLoader = new DogGameFrameLoader();
    private final Bank bank = new Bank();
    /**
     * Helper method to create a single default dog.
     * @return The dog.
     */
    public DogGameObject createDog() {
        // create the default dog object
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        return new DogGameObject(50, 100, dogSprite, this.bank);
    }

    /**
     * Creates the Main stage.
     * @return The main stage.
     */
    public Stage createMainStage() {
        Stage mainStage = new Stage("Main");

        DogGameObject dog = createDog();
        mainStage.addGameObject(dog);

        // create the coin label
        TextLabel coinLabel = new CoinLabel(new Rectangle(25, 15, 50, 20),
                "Coins: 0", "CoinLabel");
        coinLabel.setLabelColor(null);
        coinLabel.setTextColor(Color.WHITE);
        mainStage.addTextLabel(coinLabel);
        this.bank.addPropertyChangeListener((PropertyChangeListener) coinLabel);

        ShopButton shop = new ShopButton(new Rectangle(190, 400, 80, 20),
                "Shop", "Shop", this.controller);
        // You can change the coordinates of this button later

        MinigameSelectionButton minigameSelection = new MinigameSelectionButton(new Rectangle(190, 430,
                80, 20),
                "Minigames", "MinigameSelection", this.controller);

        mainStage.addTextLabel(shop);
        mainStage.addTextLabel(minigameSelection);
        return mainStage;
    }

    // create the shop
    public Stage createShopStage(){
        Stage shopStage = new Stage("Shop");

        // create the coin label
        TextLabel coinLabel = new CoinLabel(new Rectangle(25, 15, 50, 20),
                "Coins: 0", "CoinLabel");
        coinLabel.setLabelColor(null);
        coinLabel.setTextColor(Color.WHITE);
        shopStage.addTextLabel(coinLabel);
        this.bank.addPropertyChangeListener((PropertyChangeListener) coinLabel);

        // the button to purchase the computer dogecoin miner
        MinerButton computer = new MinerButton(new Rectangle(90, 30, 130, 100),
                "Buy Computer", "Computer", this.bank, 50, 10, 10);

        shopStage.addTextLabel(computer);

        //the button to purchase the factory dogecoin miner
        MinerButton factory = new MinerButton(new Rectangle(90, 165, 130, 100),
                "Buy Factory", "Factory", this.bank, 500, 100 , 100);

        shopStage.addTextLabel(factory);

        //the button to purchase the lunar dog cafe dogecoin miner
        MinerButton lunarDogCafe = new MinerButton(new Rectangle(90, 300, 130, 100),
                "Buy Lunar Dog Cafe", "LunarDogCafe", this.bank, 5000, 1000, 800);

        shopStage.addTextLabel(lunarDogCafe);

        HomeButton home = new HomeButton(new Rectangle(115, 430, 70, 20),
                "Return", "Home", this.controller);
        home.setLabelColor(null);
        home.setTextColor(Color.WHITE);

        shopStage.addTextLabel(home);

        return shopStage;
    }

    public ICamera createCamera(){
        return this.controller.getCamera();
    }

    public DogGameController getController(){
        return controller;
    }
}
