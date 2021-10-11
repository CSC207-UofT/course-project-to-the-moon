package main.java.drivers;

import main.java.adaptors.DogGameController;
import main.java.adaptors.DogGamePresenter;
import main.java.entities.Dog;
import main.java.usecases.DogMover;

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

        // Jimin Song changed this part
        // creating dog
        Dog dog = new Dog();
        // creating DogMover
        DogMover dog_mover = new DogMover(dog, WIDTH, HEIGHT);
        // move the dog randomly
        dog.dm = dog_mover;
        JPanel presenter = new DogGamePresenter(WIDTH, HEIGHT);
        ((DogGamePresenter) presenter).dog = dog;
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
