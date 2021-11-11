package adaptors;

import java.awt.Image;
import java.awt.Color;

/**
 * An interface to adapt the java.awt.Graphics class so as not to violate dependencies.
 * @author Andy Wang
 * @since 23 October 2021
 */
public interface IGameGraphics {
    public void drawImage(Image i, int x, int y);
    public void drawText(String s, int x, int y, Color c);
    public void fillRect(int x, int y, int w, int h, Color c);
    public void drawRect(int x, int y, int w, int h, int stroke, Color c);
    public int[] getTextBounds(String s);
}
