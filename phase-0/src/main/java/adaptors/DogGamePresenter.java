package main.java.adaptors;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This class represents a presenter for the dog game, responsible for drawing everything.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGamePresenter extends JPanel {
    private int width;
    private int height;

    /**
     * Initialize a new presenter.
     * @param w The width of the JFrame that this is attached to.
     * @param h The height of the JFrame that this is attached to.
     */
    public DogGamePresenter(int w, int h) {
        width = w;
        height = h;
    }

    /**
     * Draws everything to the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        // TODO: make it draw the game objects
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);

        try {
            Thread.sleep (100); // delay between frames
            repaint();
        } catch (InterruptedException e) {
            System.out.println("Delaying between frames went wrong.");
        }
    }
}
