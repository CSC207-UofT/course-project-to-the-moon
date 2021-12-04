package adaptors;

import usecases.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;

/**
 * A class using the Builder pattern to build a DogGameController.
 * @author Praket, Andy
 * @since Dec 2 2021
 */
public class ControllerBuilder {
    private final Bank bank = new Bank();
    private final DogGameController controller;
    private final IFrameLoader frameLoader;
    private final GameReadWriter gReadWriter;

    /**
     * Initializes a new ControllerBuilder using the given IFrameLoader and camera bounds.
     * @param fl The IFrameLoader to use.
     * @param bounds The camera bounds.
     */
    public ControllerBuilder(IFrameLoader fl, Rectangle bounds, GameReadWriter gReadWriter) {
        this.frameLoader = fl;
        controller = new DogGameController();
        controller.addFrameLoader(fl);

        this.gReadWriter = gReadWriter;
        controller.addReadWriter(gReadWriter);
        controller.initializeEcon();

        Stage mainStage = createMainStage();
        Stage shopStage = createShopStage();
        Stage minigameStage = createMinigameSelectionStage();
        Stage startStage = createStartStage();

        ICamera camera = new Camera(mainStage, bounds);

        controller.addBank(this.bank);

        controller.addStage("Main", mainStage);
        controller.addStage("Shop", shopStage);
        controller.addStage("MinigameSelection", minigameStage);
        controller.addStage("Start", startStage);

        controller.addCamera(camera);
        controller.setActiveStage("Start");
    }

    /**
     * Return the controller built by this builder.
     * @return The DogGameController built by this builder.
     */
    public DogGameController getController(){
        return controller;
    }

    /**
     * Helper method to create a single default dog.
     * @return The dog.
     */
    private DogGameObject createDog() {
        // create the default dog object
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        return new DogGameObject(50, 100, dogSprite, this.bank);
    }

    /**
     * Creates the Main stage.
     * @return The main stage.
     */
    private Stage createMainStage() {
        Stage mainStage = new Stage("Main");

        DogGameObject dog = createDog();
        mainStage.addGameObject(dog);
        getController().getReadWriter().addDog(dog);

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
    private Stage createShopStage(){
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
        controller.getEcon().addItem(computer);

        // cost label for the computer
        TextLabel compLabel = new ObserverLabel(new Rectangle(130, 90, 50, 20),
                "Price: "+computer.getCost(), "ItemLabel");
        
        compLabel.setLabelColor(null);
        compLabel.setTextColor(Color.WHITE);
        shopStage.addTextLabel(compLabel);
        computer.addPropertyChangeListener((PropertyChangeListener) compLabel);

        //the button to purchase the factory dogecoin miner
        MinerButton factory = new MinerButton(new Rectangle(90, 165, 130, 100),
                "Buy Factory", "Factory", this.bank, 500, 100 , 100);

        shopStage.addTextLabel(factory);
        controller.getEcon().addItem(factory);

        // cost label for the factory
        TextLabel facLabel = new ObserverLabel(new Rectangle(130, 225, 50, 20),
                "Price: "+computer.getCost(), "ItemLabel");
        
        facLabel.setLabelColor(null);
        facLabel.setTextColor(Color.WHITE);
        shopStage.addTextLabel(facLabel);
        factory.addPropertyChangeListener((PropertyChangeListener) facLabel);

        //the button to purchase the lunar dog cafe dogecoin miner
        MinerButton lunarDogCafe = new MinerButton(new Rectangle(90, 300, 130, 100),
                "Buy Lunar Dog Cafe", "LunarDogCafe", this.bank, 5000, 1000, 800);

        shopStage.addTextLabel(lunarDogCafe);
        controller.getEcon().addItem(lunarDogCafe);

        // cost label for the cafe
        TextLabel cafeLabel = new ObserverLabel(new Rectangle(130, 360, 50, 20),
                "Price: "+computer.getCost(), "ItemLabel");
        
        cafeLabel.setLabelColor(null);
        cafeLabel.setTextColor(Color.WHITE);
        shopStage.addTextLabel(cafeLabel);
        lunarDogCafe.addPropertyChangeListener((PropertyChangeListener) cafeLabel);

        HomeButton home = new HomeButton(new Rectangle(115, 430, 70, 20),
                "Return", "Home", this.controller);
        home.setLabelColor(null);
        home.setTextColor(Color.WHITE);

        shopStage.addTextLabel(home);

        return shopStage;
    }

    // create the minigame selection stage
    private Stage createMinigameSelectionStage(){
        Stage minigameSelectionStage = new Stage("MinigameSelectionStage");

        // create the coin label
        TextLabel coinLabel = new CoinLabel(new Rectangle(25, 15, 50, 20),
                "Coins: 0", "CoinLabel");
        coinLabel.setLabelColor(null);
        coinLabel.setTextColor(Color.WHITE);
        minigameSelectionStage.addTextLabel(coinLabel);
        this.bank.addPropertyChangeListener((PropertyChangeListener) coinLabel);

        //create a button that leads to the platformer/doodle jump minigame
        PlatformerButton platformerButton = new PlatformerButton(new Rectangle(100, 100, 100, 100),
                "Platformer", "Platformer", this.controller);

        minigameSelectionStage.addTextLabel(platformerButton);

        //create a button that leads to the dino minigame
        DinoButton dinoButton = new DinoButton(new Rectangle(100, 250, 100, 100),
                "Dino", "Dino", this.controller);

        minigameSelectionStage.addTextLabel(dinoButton);


        HomeButton home = new HomeButton(new Rectangle(115, 430, 70, 20),
                "Return", "Home", this.controller);
        home.setLabelColor(null);
        home.setTextColor(Color.WHITE);

        minigameSelectionStage.addTextLabel(home);

        return minigameSelectionStage;
    }

    /**
     * Creates the Start stage.
     * @return The start stage.
     */
    private Stage createStartStage() {
        Stage startStage = new Stage("Start");

        NewGameButton newGame = new NewGameButton(new Rectangle(100, 100, 100, 40),
                "New Game", "NewGame", this.controller);

        LoadGameButton loadGame = new LoadGameButton(new Rectangle(100, 250, 100, 40),
                "Load Game", "LoadGame", this.controller, this.controller.getSaveFilePath());

        loadGame.addPropertyChangeListener((PropertyChangeListener) this.controller.getEcon());
        startStage.addTextLabel(newGame);
        startStage.addTextLabel(loadGame);

        return startStage;
    }
}