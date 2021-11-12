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
    private JFrame miniFrame = null;
    final private int jumpHeight = 100;

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
        MiniGameButton miniButton = new MiniGameButton(new Rectangle(200, 450, 50, 20),
                "Minigame", "Minigame", this.controller);
        miniButton.setLabelColor(null);
        miniButton.setTextColor(Color.GREEN);

        mainStage.addTextLabel(shop);
        return mainStage;
    }

    private Stage createShopStage(){
        Stage shopStage = new Stage("Shop");
        return shopStage;
    }
    private void initializeMiniFrame(int w, int h) {
        miniFrame = new JFrame();

        miniFrame.setSize(w, h);
        miniFrame.setResizable(false);
        miniFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miniFrame.setLocationRelativeTo(null);
        miniFrame.setFocusable(true);
        miniFrame.requestFocus();
    }

    private Stage createMinigameStage(){
        // Assume (300, 500)
        Stage minigameStage = new Stage("Minigame");
        // PlatformDogGameObject miniDog = new PlatformDogGameObject();
        PlatformGameObject bottomPlatform = new PlatformGameObject(0, 10000, 300, 50);
        minigameStage.addGameObject(bottomPlatform);
        addRandomPlatforms(minigameStage);
        return minigameStage;
    }

    private void addRandomPlatforms(Stage minigameStage){
        Random random = new Random();
        int previousY = 10000;
        for(int i= 0; i< 100; i++){
            PlatformGameObject newPlatform;

            // What should the width of each platform be?
            do {
                int rX = random.nextInt(276);
                // Random number between 51 and 151
                int rY = random.nextInt(jumpHeight) + 51;
                int newY = previousY - rY;
                // Length of the platform is 25 x 50
                newPlatform = new PlatformGameObject(rX, newY, 25, 50);

                previousY = newY;

            }while(!(minigameStage.placeMeeting(newPlatform, newPlatform.getX(), newPlatform.getY())));
            // do-while is just to make sure that no platforms overlap,
            // but it's probably not necessary
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