package usecases;

/**
 * This interface is to be implemented on all clickable entities
 * @author Juntae Park
 * @since 12 October 2021
*/

public interface Clickable {
    public boolean clicked(int mouseX, int mouseY);
    public int[] getLocation();
    public void act();
}
