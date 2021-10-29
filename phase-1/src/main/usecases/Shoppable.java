package usecases;

/**
 * To be implemented for all objects that can be bought
 * @author Juntae Park
 * @since 29 October 2021
 */

public interface Shoppable {
    public int getCost();
    public void increasePrice(int addPrice);
}
