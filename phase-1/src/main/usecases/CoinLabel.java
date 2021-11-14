package usecases;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CoinLabel extends TextLabel implements PropertyChangeListener {
    
    public CoinLabel(Rectangle r, String text, String tag) {
        super(r, text, tag);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.setText("Coins: " + evt.getNewValue());
    }
}

