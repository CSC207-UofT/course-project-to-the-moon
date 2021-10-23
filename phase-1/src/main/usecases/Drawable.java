package usecases;

import adaptors.GameGraphics;

/**
 * This interface represents something that can be drawn.
 * @author Andy Wang
 * @since 13 October 2021
*/
public interface Drawable {
     public void draw(GameGraphics g, int x, int y);
}
