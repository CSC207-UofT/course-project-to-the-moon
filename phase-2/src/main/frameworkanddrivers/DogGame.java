package frameworkanddrivers;

import adaptors.*;
import usecases.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;


/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang, Fatimeh Hassan
 * @since 9 October 2021
 */
public class DogGame {
    private final String saveFilePath = "phase-2/src/save/savefile.ser";

    private JFrame mainFrame = null;

    private final Bank bank = new Bank();
    private final GameReadWriter gReadWriter = new GameReadWriter(saveFilePath);
    private final DogGameController controller;

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

        DogGameFrameLoader frameLoader = new DogGameFrameLoader();
        Rectangle cameraBounds = new Rectangle(0, 0, WIDTH, HEIGHT);
        ControllerBuilder builder = new ControllerBuilder(frameLoader, cameraBounds, gReadWriter);

        this.controller = builder.getController();

        DogGameJPanel panel = new DogGameJPanel(WIDTH, HEIGHT);
        panel.setFocusable(true);
        panel.addController(controller);
        panel.addCamera(controller.getCamera());
        panel.requestFocus();

        mainFrame.add(panel);

        this.initializeGameSaver();
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

    // initializes the game saver class
    private void initializeGameSaver() throws IOException {
        this.controller.addReadWriter(this.gReadWriter);
        this.gReadWriter.addBank(this.bank);

        mainFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                File saveFile = new File(saveFilePath);
                
                try {
                    if (saveFile.exists()) { gReadWriter.saveGame(false); }

                } catch (IOException er) {
                    System.out.println(er.getMessage());
                }

                System.exit(0);
            }
        });
    }

    /**
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
