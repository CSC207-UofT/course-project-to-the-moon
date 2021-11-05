//package programdrivers;

import adaptors.*;
import entities.Bank;
import org.w3c.dom.Text;
import usecases.DogGameObject;
import usecases.SpriteFacade;
import usecases.Stage;
import usecases.TextLabel;
import usecases.CollidableDogGameObject;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;


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

        Rectangle bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
        Camera camera = new Camera(mainStage, bounds);

        controller.addFrameLoader(frameLoader);
        controller.addStage("Main", mainStage);
        controller.setActiveStage("Main");

        // Create collidable dogs
        CollidableDogGameObject defaultDog = this.createCollidableDog(0,0, true);
        mainStage.addGameObject(defaultDog);
        CollidableDogGameObject defaultDog2 = this.createCollidableDog(200,300, true);
        mainStage.addGameObject(defaultDog2);


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
        DogGameObject defaultDog = new DogGameObject(0, 0, dogSprite, this.controller, this.bank); 

        return defaultDog;
    }

    //TESTING COLLISION
    private CollidableDogGameObject createCollidableDog(int x, int y, boolean move) {
        // create the default dog object
        BufferedImage[] dogFrames = this.frameLoader.loadFramesFromFolder("phase-1/src/sprites/dog");

        SpriteFacade dogSprite = new SpriteFacade(dogFrames, 2);
        CollidableDogGameObject defaultDog = new CollidableDogGameObject(x, y, dogSprite, this.controller, this.bank, move);

        return defaultDog;
    }

    /**
     * Creates the Main stage.
     * @return The main stage.
     */
    private Stage createMainStage() {
        Stage mainStage = new Stage("Main");
        
        // create the coin label
        TextLabel coinLabel = new TextLabel(new Rectangle(15, 15, 50, 20),
                "Coins: 0", "CoinLabel");
        coinLabel.setLabelColor(null);
        coinLabel.setTextColor(Color.WHITE);

        mainStage.addTextLabel(coinLabel);

        return mainStage;
    }

    /**
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
