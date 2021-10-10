package main.java.adaptors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGameController implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO: process the mouse input
        System.out.println("Clicked!");
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
