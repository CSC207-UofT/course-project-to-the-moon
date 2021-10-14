package programdrivers;

import adaptors.DogGameController;
import adaptors.DogGamePresenter;

import javax.swing.JFrame;
import java.io.IOException;

/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame;
    private final int WIDTH = 300;
    private final int HEIGHT = 500;

    /**
     * This is the main method. Run this to run the game.
     * @param args Unused.
     */
    public static void main(String[] args) throws IOException {
        DogGame dg = new DogGame();
        dg.start();
    }

    /**
     * Initialize a new dog game and all its frames.
     */
    public DogGame() {

        mainFrame = new JFrame();
        /* width of the frame */
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: if we decide to implement saving, change the default operation to something else
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);
        mainFrame.requestFocus();

        DogGamePresenter presenter = new DogGamePresenter(WIDTH, HEIGHT);
        DogGameController controller = new DogGameController();

        presenter.addController(controller);
        mainFrame.add(presenter);
    }
    // getter method for testing
    public JFrame getFrame(){ return this.mainFrame;}

    /**
     * Starts the dog game.
     */
    public void start() {
        mainFrame.setVisible(true);
    }
}
