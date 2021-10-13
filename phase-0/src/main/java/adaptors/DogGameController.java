package main.java.adaptors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import main.java.adaptors.ClickProcessor;
import main.java.usecases.DogManager;
import main.java.usecases.Clickable;

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
        this.processor = new ClickProcessor(new Clickable[]{
            this.DogManager
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Juntae implemented this part. ONLY CHECKS DOG CLICKED, need to expand
        Clickable clickedManager = this.processor.getClicked(e.getX(), e.getY());
        if(clickedManager instanceof Clickable) {clickedManager.act();}

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
