package usecases.interfaces;

import java.io.IOException;

/**
 * This interface is to be implemented on all clickable entities
 * @author Juntae Park
 * @since 12 October 2021
*/

public interface Clickable {
     boolean isClicked(int mouseX, int mouseY);
     void onClick();
}
