package frameworkanddrivers;

import adaptors.*;
import entities.GameState;
import usecases.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.IOException;


/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame = null;

    private final Bank bank = new Bank();
    private final DogGameFrameLoader frameLoader = new DogGameFrameLoader();
    private final DogGameController controller = new DogGameController();
    private final GameReadWriter gReadWriter = new GameReadWriter("phase-1/src/save/savefile.ser");

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

        // Create the main stage
        Stage mainStage = this.createMainStage();
        Stage shopStage = this.createShopStage();

        Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
        ICamera camera = new Camera(mainStage, bounds);

        controller.addBank(bank);
        controller.addFrameLoader(frameLoader);
        controller.addStage("Main", mainStage);
        controller.addStage("Shop", shopStage);
        controller.addCamera(camera);
        controller.setActiveStage("Main");

        panel.setFocusable(true);
        panel.addController(controller);
        panel.addCamera(camera);
        panel.requestFocus();

        mainFrame.add(panel);

        //read save file
        this.readSaveFile();
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
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

    }

    private void initializeGameSaver() throws IOException{
        mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    gReadWriter.saveGame(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
    private void readSaveFile() throws IOException, ClassNotFoundException {
        GameState savedState = this.gReadWriter.readFromFile();
        this.bank.updateCoins((int) savedState.getState().get("Coins"));
        this.bank.setDCPS((int) savedState.getState().get("DCPS"));
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

        // create the coin label
        TextLabel coinLabel = new CoinLabel(new Rectangle(15, 15, 50, 20),
                "Coins: 0", "CoinLabel");
        coinLabel.setLabelColor(null);
        coinLabel.setTextColor(Color.WHITE);
        mainStage.addTextLabel(coinLabel);
        this.bank.addPropertyChangeListener((PropertyChangeListener) coinLabel);

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

    // create the shop
    private Stage createShopStage(){
        Stage shopStage = new Stage("Shop");

        // a computer to mine dogecoin automatically
        MinerButton computer = new MinerButton(new Rectangle(90, 30, 120, 100),
                "Buy Computer", "Computer", this.bank, 50, 10, 10);

        shopStage.addTextLabel(computer);

        // a factory to mine a bunch of dogecoin
        MinerButton factory = new MinerButton(new Rectangle(90, 165, 120, 100),
                "Buy Factory", "Factory", this.bank, 500, 100 , 100);

        shopStage.addTextLabel(factory);

        MinerButton lunarDogCafe = new MinerButton(new Rectangle(90, 300, 120, 100),
                "Buy Lunar Dog Cafe", "LunarDogCafe", this.bank, 5000, 1000, 800);

        shopStage.addTextLabel(lunarDogCafe);

        HomeButton home = new HomeButton(new Rectangle(115, 430, 70, 20),
                "Return", "Home", this.controller);
        home.setLabelColor(null);
        home.setTextColor(Color.WHITE);

        shopStage.addTextLabel(home);

        return shopStage;
    }

    /**
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
