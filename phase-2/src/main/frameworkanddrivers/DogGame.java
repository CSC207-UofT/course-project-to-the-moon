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
    private final ControllerBuilder builder = new ControllerBuilder(frameLoader);
    private final String saveFilePath = "phase-2/src/save/savefile.ser";
    private final GameReadWriter gReadWriter = new GameReadWriter(this.saveFilePath);

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

        DogGameController controller = builder.getController();
        //controller.addStage("StartStage", startStage);
        //controller.setActiveStage("StartStage");

        DogGameJPanel panel = new DogGameJPanel(WIDTH, HEIGHT);
        panel.setFocusable(true);
        panel.addController(controller);
        panel.addCamera(controller.getCamera());
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
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
