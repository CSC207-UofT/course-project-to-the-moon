package adaptors;

import java.awt.image.BufferedImage;
import entities.Dog;
import usecases.Clickable;
import usecases.Drawable;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class represents a presenter for the dog game, responsible for drawing everything.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGamePresenter extends JPanel {
    private int width;
    private int height;

    private DogGameController controller;

    public Dog dog;

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
     * Adds a dog game controller to this presenter.
     * @param controller The controller to add.
     */
    public void addController(DogGameController controller) {
        super.addMouseListener(controller);
        this.controller = controller;
    }

    /**
     * Draws everything to the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        ArrayList<Clickable> clickables = (ArrayList<Clickable>) this.controller.getClickables();

        for (Clickable clickable : clickables) {
            if (clickable instanceof Drawable) {
                int x = ((Drawable) clickable).getX();
                int y = ((Drawable) clickable).getY();
                BufferedImage image = ((Drawable) clickable).getImage();
                g.drawImage(image, x, y, null);
            }
        }

        try {
            Thread.sleep (100); // delay between frames
            repaint();
        } catch (InterruptedException e) {
            System.out.println("Delaying between frames went wrong.");
        }
    }
}
