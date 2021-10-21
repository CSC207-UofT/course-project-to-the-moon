package entities;

/**
 * This class represents an item in the shop.
 * @author Fatimeh Hassan
 */

public class ShopItem {
    private final String name;
    private final int cost;

    public ShopItem(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }
}
