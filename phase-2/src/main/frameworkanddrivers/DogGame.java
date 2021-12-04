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
    private final ControllerBuilder builder = new ControllerBuilder(frameLoader);
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

        DogGameController controller = builder.getController();

        DogGameJPanel panel = new DogGameJPanel(WIDTH, HEIGHT);
        panel.setFocusable(true);
        panel.addController(controller);
        panel.addCamera(controller.getCamera());
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

    /**
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
