package usecases;
import java.awt.*;

public class CoinLabel extends TextLabel implements CoinListener {
    
    public CoinLabel(Rectangle r, String text, String tag) {
        super(r, text, tag);
    }

    @Override
    public void update(int newCoin) {
        setText("Coins: " + String.valueOf(newCoin));
    }
}

