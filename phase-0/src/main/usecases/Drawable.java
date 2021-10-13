package usecases;

import java.awt.image.BufferedImage;

/**
 * This interface represents something that can be drawn.
 * @author Andy Wang
 * @since 13 October 2021
*/
public interface Drawable {
    public int getX();
    public int getY();
    public BufferedImage getImage();
}
