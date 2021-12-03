package frameworkanddrivers;

import adaptors.*;
import usecases.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;


/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang, Fatimeh Hassan
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame = null;

    private final Bank bank = new Bank();
    private final DogGameFrameLoader frameLoader = new DogGameFrameLoader();
    private final DogGameController controller = new DogGameController();

    private final String saveFilePath = "phase-2/src/save/savefile.ser";
    GameReadWriter gReadWriter = new GameReadWriter(this.saveFilePath);

    /**
     * This is the main method. Run this to run the game.
     * @param args Unused.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DogGame dg = new DogGame();
        dg.start();
    }

    /**
     * Initialize a new dog game and all its frames.
     */
    public DogGame() throws IOException, ClassNotFoundException {
        int WIDTH = 300;
        int HEIGHT = 500;
        this.initializeMainFrame(WIDTH, HEIGHT);
        this.initializeGameSaver();

        DogGameJPanel panel = new DogGameJPanel(WIDTH, HEIGHT);

        // Create the game stages
        Stage mainStage = this.createMainStage();
        Stage shopStage = this.createShopStage();
        Stage minigameSelectionStage = this.createMinigameSelectionStage();
        Stage startStage = this.createStartStage();

        Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
        ICamera camera = new Camera(mainStage, bounds);

        controller.addBank(bank);
        controller.addFrameLoader(frameLoader);
        controller.addStage("StartStage", startStage);
        controller.addStage("Main", mainStage);
        controller.addStage("Shop", shopStage);
        controller.addStage("MinigameSelectionStage", minigameSelectionStage);
        controller.addCamera(camera);
        controller.setActiveStage("StartStage");

        panel.setFocusable(true);
        panel.addController(controller);
        panel.addCamera(camera);
        panel.requestFocus();

        mainFrame.add(panel);
    }

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
        mainFrame.setLocationRelativeTo(null);
    }

    private void initializeGameSaver() throws IOException {

        this.controller.addReadWriter(this.gReadWriter);
        this.gReadWriter.addBank(this.bank);

        mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                File savefile = new File(saveFilePath);
                
                try {
                    if(savefile.exists()) { gReadWriter.saveGame(false); }

                } catch (IOException er) {
                    System.out.println(er.getMessage());
                }

                System.exit(0);
            }
        });
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
     * Creates the Start stage.
     * @return The start stage.
     */
    private Stage createStartStage() {
        Stage startStage = new Stage("Start");
      
        NewGameButton newGame = new NewGameButton(new Rectangle(100, 100, 100, 40),
                "New Game", "NewGame", this.controller, this.saveFilePath);

        LoadGameButton loadGame = new LoadGameButton(new Rectangle(100, 250, 100, 40),
        "Load Game", "LoadGame", this.controller, this.saveFilePath);

        startStage.addTextLabel(newGame);
        startStage.addTextLabel(loadGame);

        return startStage;
    }

    /**
     * Creates the Main stage.
     * @return The main stage.
     */
    private Stage createMainStage() {
        Stage mainStage = new Stage("Main");

        DogGameObject dog = createDog();
        mainStage.addGameObject(dog);
        gReadWriter.addDog(dog);

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

        //the button to purchase the factory dogecoin miner
        MinerButton factory = new MinerButton(new Rectangle(90, 165, 130, 100),
                "Buy Factory", "Factory", this.bank, 5000, 1000 , 50);

        shopStage.addTextLabel(factory);

        //the button to purchase the lunar dog cafe dogecoin miner
        MinerButton lunarDogCafe = new MinerButton(new Rectangle(90, 300, 130, 100),
                "Buy Lunar Dog Cafe", "LunarDogCafe", this.bank, 10000, 5000, 100);

        shopStage.addTextLabel(lunarDogCafe);

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
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
