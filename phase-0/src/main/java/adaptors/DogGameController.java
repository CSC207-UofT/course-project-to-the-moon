package main.java.adaptors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import main.java.adaptors.ClickProcessor;
import main.java.usecases.DogManager;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGameController implements MouseListener {
    private ClickProcessor processor;
    private DogManager DogManager;

    public DogGameController() {
        this.DogManager = new DogManager();
        this. processor = new ClickProcessor();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Juntae implemented this part. ONLY CHECKS DOG CLICKED, need to expand
        if (processor.DogClicked(e.getX(), e.getY())) {
            DogManager.pet();
        };

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
