package usecases.mainhub;
import usecases.object.TextLabel;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A label to display coins.
 * @author Juntae
 * @since 12 November 2021
 */
public class ObserverLabel extends TextLabel implements PropertyChangeListener {

    public ObserverLabel(Rectangle r, String text, String tag) {
        super(r, text, tag);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(super.getTag() == "CoinLabel"){
            super.setText("Coins: " + evt.getNewValue());
        }
        else if(super.getTag() == "ItemLabel"){
            super.setText("Cost: " + evt.getNewValue());
        }
        
    }
}

