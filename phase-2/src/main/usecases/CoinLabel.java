package usecases;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A label to display coins.
 * @author Juntae
 * @since 12 November 2021
 */
public class CoinLabel extends TextLabel implements PropertyChangeListener {

    public CoinLabel(Rectangle r, String text, String tag) {
        super(r, text, tag);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.setText("Coins: " + evt.getNewValue());
    }
}

