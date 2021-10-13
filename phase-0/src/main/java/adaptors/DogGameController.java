package java.adaptors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.usecases.DogManager;
import java.usecases.Clickable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a controller for the dog game. It processes mouse input.
 * @author Andy Wang
 * @since 9 October 2021
 */
public class DogGameController implements MouseListener {
    // stores all the clickable objects (managers)
    private final ArrayList<Clickable> clickables = new ArrayList<>();
    // god dammit IntelliJ if I add a final keyword will you finally be happy????

    /**
     * Initializes a new controller for the dog game, that process inputs.
     */
    public DogGameController() {
        this.clickables.add(new DogManager());
    }

    public List<Clickable> getClickables() {
        return this.clickables;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Juntae implemented this part, edited by Andy
        for (Clickable clickable : this.clickables) {
            // find the clickable thing that you clicked on
            if (clickable.clicked(e.getX(), e.getY())) {
                clickable.act(); // make it act
            }
        }
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
