package adaptors;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import entities.Transform;
import usecases.Drawable;
import usecases.TextLabel;

import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * This class represents a presenter for the dog game, responsible for drawing everything.
 * @author Andy Wang, Juntae Park
 * @since 9 October 2021
 */
public class DogGameJPanel extends JPanel{
    private final int width;
    private final int height;
    private ICamera camera = null;

    /**
     * Initialize a new JPanel.
     * @param w The width of the JFrame that this is attached to.
     * @param h The height of the JFrame that this is attached to.
     */
    public DogGameJPanel(int w, int h) {
        width = w;
        height = h;
    }

    /**
     * Add a new Camera to this JPanel, so that the JPanel knows what to draw.
     * @param c The new Camera to add.
     */
    public void addCamera(ICamera c) {
        this.camera = c;
    }

    /**
     * Adds a dog game controller to this presenter.
     * @param controller The controller to add.
     */
    public void addController(DogGameController controller) {
        super.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.mouseClicked(e);
            }

            // all of these are unneeded lol
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        super.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                controller.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                controller.keyReleased(e);

            }
        });
    }

    /**
     * Draws everything to the screen.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // adapt the Graphics
        DogGameGraphics dg = new DogGameGraphics(g);

        //draw objects
        Transform cameraTransform = camera.getTransform();
        int cameraX = (int) cameraTransform.getX();
        int cameraY = (int) cameraTransform.getY();

        for (Drawable drawable : this.camera.getDrawableObjectsInBounds()) {
            drawable.draw(dg, cameraX, cameraY);
        }

        // draw the GUI
        for (TextLabel label : this.camera.getTextLabels()) {
            label.draw(dg, 0, 0);
        }
        try {
            Thread.sleep (50); // delay between frames
            repaint();
        } catch (InterruptedException e) {
            System.out.println("Delaying between frames went wrong.");
        } 
    }
}
