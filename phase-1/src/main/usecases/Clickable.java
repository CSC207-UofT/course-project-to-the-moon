package usecases;

/**
 * This interface is to be implemented on all clickable entities
 * @author Juntae Park
 * @since 12 October 2021
*/

public interface Clickable {
     boolean clicked(int mouseX, int mouseY);
     void act();
}
