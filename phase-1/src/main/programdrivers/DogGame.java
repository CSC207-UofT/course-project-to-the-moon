//package programdrivers;

import adaptors.*;
import usecases.Bank;
import usecases.DogGameObject;
import usecases.SpriteFacade;
import usecases.Stage;
import usecases.HUDObject;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame = null;

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
        DogGameController controller = new DogGameController();

        // create the Bank to store currency
        Bank bank = new Bank();
        controller.setBank(bank);

        // create the HUD to display currency, etc
        HUDObject HUD = new HUDObject(5, 15, bank);

        DogGameFrameLoader frameLoader = new DogGameFrameLoader();

        // Create the main stage
        Stage mainStage = new Stage();

        // create the default dog object
        BufferedImage[] dogFrames = frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog");
        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);
        DogGameObject defaultDog = new DogGameObject(0, 0, dogSprite, controller);
        mainStage.addGameObject(defaultDog);

        // try with 2 dogs!!!

        
        BufferedImage[] dogFrames2 = frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog");
        SpriteFacade dogSprite2 = new SpriteFacade(dogFrames2, 2);
        DogGameObject defaultDog2 = new DogGameObject(100, 150, dogSprite2, controller);
        mainStage.addGameObject(defaultDog2);

        Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
        Camera camera = new Camera(mainStage, bounds);

        controller.addFrameLoader(frameLoader);
        controller.addStage("MAIN", mainStage);
        controller.setActiveStage("MAIN");

        panel.addController(controller);
        panel.addCamera(camera);
        panel.addHUD(HUD);
        
        // create game system

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
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
