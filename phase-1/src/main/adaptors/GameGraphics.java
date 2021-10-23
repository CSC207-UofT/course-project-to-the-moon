package adaptors;

import java.awt.Image;
import java.awt.Color;

/**
 * An interface to adapt the java.awt.Graphics class so as not to violate dependencies.
 * @author Andy Wang
 * @since 23 October 2021
 */
public interface GameGraphics {
    public void drawImage(Image i, int x, int y);
    public void drawText(String s, int x, int y);
    public void setColor(Color color);
}
