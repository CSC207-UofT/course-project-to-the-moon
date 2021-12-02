package frameworkanddrivers;

import adaptors.*;
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
 * @author Andy Wang, Fatimeh Hassan
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame = null;

    private final Bank bank = new Bank();
    private final DogGameFrameLoader frameLoader = new DogGameFrameLoader();
    private final ControllerBuilder builder = new ControllerBuilder();
    private final DogGameController controller = builder.getController();
   // private final GameReadWriter gReadWriter = new GameReadWriter(controller, "phase-1/src/save/savefile.ser");

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

        DogGameJPanel panel = new DogGameJPanel(WIDTH, HEIGHT);

        // Create the main stage
        Stage mainStage = builder.createMainStage();
        Stage shopStage = builder.createShopStage();
        Stage minigameSelectionStage = this.createMinigameSelectionStage();

        Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
        ICamera camera = builder.createCamera();

        controller.addBank(bank);
        controller.addFrameLoader(frameLoader);
        controller.addStage("Main", mainStage);
        controller.addStage("Shop", shopStage);
        controller.addStage("MinigameSelectionStage", minigameSelectionStage);
        controller.addCamera(camera);
        controller.setActiveStage("Main");

        panel.setFocusable(true);
        panel.addController(controller);
        panel.addCamera(camera);
        panel.requestFocus();

        mainFrame.add(panel);

        //read save file
        //this.readSaveFile();
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

        //this.initializeGameSaver();
    }

//    private void initializeGameSaver() {
//        mainFrame.addWindowListener(new WindowAdapter(){
//            @Override
//            public void windowClosing(WindowEvent e) {
//                gReadWriter.saveGame();
//                System.exit(0);
//            }
//        });
//    }
//    private void readSaveFile() throws IOException, ClassNotFoundException {
//        GameState savedState = this.gReadWriter.readFromFile();
//        this.bank.updateCoins((int) savedState.getState().get("Coins"));
//        this.bank.setDCPS((int) savedState.getState().get("DCPS"));
//    }

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
