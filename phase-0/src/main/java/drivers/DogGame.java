package main.java.drivers;

import main.java.adaptors.DogGameController;
import main.java.adaptors.DogGamePresenter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseListener;

/**
 * This class represents a dog game instance, making all the JFrames necessary to run it.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGame {
    private JFrame mainFrame;

    /**
     * This is the main method. Run this to run the game.
     * @param args Unused.
     */
    public static void main(String[] args) {
        DogGame dg = new DogGame();
        dg.run();
    }

    /**
     * Initialize a new dog game and all its frames.
     */
    public DogGame() {
        int WIDTH = 300;
        int HEIGHT = 500;

        mainFrame = new JFrame();
        /* width of the frame */
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: if we decide to implement saving, change the default operation to something else
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);
        mainFrame.requestFocus();

        JPanel presenter = new DogGamePresenter(WIDTH, HEIGHT);
        MouseListener controller = new DogGameController();

        presenter.addMouseListener(controller);
        mainFrame.add(presenter);
    }

    /**
     * Runs a new dog game.
     */
    public void run() {
        mainFrame.setVisible(true);
    }
}
