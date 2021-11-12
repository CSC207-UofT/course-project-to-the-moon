package programdrivers;

import adaptors.*;
import entities.Bank;

import usecases.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame = null;
    private final int MAX_PLATFORM_DISTANCE = 100;
    private final int MIN_PLATFORM_DISTANCE = 20;

    private final Bank bank = new Bank();
    private final DogGameFrameLoader frameLoader = new DogGameFrameLoader();
    private final DogGameController controller = new DogGameController();

    /**
     * This is the main method. Run this to run the game.
     * @param args Unused.
     */
    public static void main(String[] args){
        DogGame dg = new DogGame();
        dg.start();
    }

    /**
     * Initialize a new dog game and all its frames.
     */
    public DogGame() {
        int WIDTH = 300;
        int HEIGHT = 500;
        this.initializeMainFrame(WIDTH, HEIGHT);

        DogGameJPanel panel = new DogGameJPanel(WIDTH, HEIGHT);
        this.controller.setBank(bank);

        // Create the main stage
        Stage mainStage = this.createMainStage();
        Stage shopStage = this.createShopStage();
        Stage miniStage = this.createMinigameStage();

        Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
        ICamera camera = new Camera(mainStage, bounds);

        controller.addFrameLoader(frameLoader);
        controller.addStage("Main", mainStage);
        controller.addStage("Shop", shopStage);
        controller.addStage("Minigame", miniStage);
        controller.addCamera(camera);
        controller.setActiveStage("Main");

        panel.addController(controller);
        panel.addCamera(camera);

        mainFrame.add(panel);
    }
    // getter method for testing
    // public JFrame getFrame(){ return this.mainFrame;}

    /**
     * Initializes the main JFrame on which everything is drawn. Does not add a JPanel yet.
     * @param w The width of the JFrame.
     * @param h The height of the JFrame.
     */
    private void initializeMainFrame(int w, int h) {
        mainFrame = new JFrame();

        mainFrame.setSize(w, h);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: if we decide to implement saving, change the default operation to something else
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);
        mainFrame.requestFocus();
    }

    /**
     * Helper method to create a single default dog.
     * @return The dog.
     */
    private DogGameObject createDog() {
        // create the default dog object
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        return new DogGameObject(0, 0, dogSprite, this.controller, this.bank);
    }

    /**
     * Creates the Main stage.
     * @return The main stage.
     */
    private Stage createMainStage() {
        Stage mainStage = new Stage("Main");

        DogGameObject dog = createDog();
        mainStage.addGameObject(dog);

        // create the coin label
        TextLabel coinLabel = new TextLabel(new Rectangle(15, 15, 50, 20),
                "Coins: 0", "CoinLabel");
        coinLabel.setLabelColor(null);
        coinLabel.setTextColor(Color.WHITE);

        mainStage.addTextLabel(coinLabel);

        ShopButton shop = new ShopButton(new Rectangle(200, 400, 50, 20),
                "Shop", "Shop", this.controller);
        // You can change the coordinates of this button later

        shop.setLabelColor(null);
        shop.setTextColor(Color.WHITE);
        MinigameButton miniButton = new MinigameButton(new Rectangle(200, 430, 60, 20),
                "Minigame", "Minigame", this.controller);
        miniButton.setLabelColor(null);
        miniButton.setTextColor(Color.GREEN);

        mainStage.addTextLabel(shop);
        mainStage.addTextLabel(miniButton);
        return mainStage;
    }

    private Stage createShopStage(){
        Stage shopStage = new Stage("Shop");

        // a computer to mine dogecoin automatically
        MinerButton computer = new MinerButton(new Rectangle(90, 30, 120, 100),
                "Buy Computer", "Computer", this.bank, 50, 10, 5);

        shopStage.addTextLabel(computer);

        // a factory to mine a bunch of dogecoin
        MinerButton factory = new MinerButton(new Rectangle(90, 150, 120, 100),
                "Buy Factory", "Factory", this.bank, 500, 100 , 20);

        shopStage.addTextLabel(factory);

        MinerButton lunarDogCafe = new MinerButton(new Rectangle(90, 300, 120, 100),
                "Buy Lunar Dog Cafe", "LunarDogCafe", this.bank, 5000, 1000, 50);

        shopStage.addTextLabel(lunarDogCafe);

        HomeButton home = new HomeButton(new Rectangle(115, 430, 70, 20),
                "Return", "Home", this.controller);
        home.setLabelColor(null);
        home.setTextColor(Color.WHITE);

        shopStage.addTextLabel(home);

        return shopStage;
    }

    /**
     * A method which creates the minigame's stage
     * @return The minigame's Stage.
     */
    private Stage createMinigameStage(){
        // Assume (300, 500)
        Stage minigameStage = new Stage("Minigame");
        PlatformDogGameObject miniDog = createMiniDog();
        minigameStage.addGameObject(miniDog);

        BufferedImage[] platFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/platform");
        SpriteFacade platformSprite = new SpriteFacade(platFrames);

        // bottom platform is the floor
        PlatformGameObject bottomPlatform = new PlatformGameObject(0, 420,  platformSprite);
        minigameStage.addGameObject(bottomPlatform);

        addRandomPlatforms(minigameStage, platformSprite);

        return minigameStage;
    }

    /**
     * Helper method to create a single default dog.
     * @return The dog.
     */
    private PlatformDogGameObject createMiniDog() {
        // create the minigame dog object
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog_shrunk");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);

        return new PlatformDogGameObject(0, 0, dogSprite, this.controller);
    }

    /**
     * A method which takes a minigame stage,
     * and adds 100 random platforms to it,
     * which all have a horizontal distance from MIN_PLATFORM_DISTANCE to MAX_PLATFORM_DISTANCE.
     * @param minigameStage the stage that is added to.
     */
    private void addRandomPlatforms(Stage minigameStage, SpriteFacade platformSprite){
        Random random = new Random();
        int previousY = 420;  // the y-coordinate of the previous platform

        for(int i = 0; i < 100; i++){
            PlatformGameObject newPlatform;

            int rX = random.nextInt(200);
            // Random number between MIN_PLATFORM_DISTANCE and MAX_PLATFORM_DISTANCE
            int rY = random.nextInt(MAX_PLATFORM_DISTANCE - MIN_PLATFORM_DISTANCE + 1) + MIN_PLATFORM_DISTANCE;
            int newY = previousY - rY;

            newPlatform = new PlatformGameObject(rX, newY, platformSprite);
            previousY = newY;

            minigameStage.addGameObject(newPlatform);
        }
    }

    /**
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
