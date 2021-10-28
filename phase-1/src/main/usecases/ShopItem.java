package usecases;

/**
 * This class represents an item in the shop.
 * @author Fatimeh Hassan, edited by Juntae
 */

public class ShopItem {
    private final int cost;
    private final int DCPS;
    private final int EXPS;

    public ShopItem(int cost, int DCPS, int EXPS) {
        this.cost = cost;
        this.DCPS = DCPS;
        this.EXPS = EXPS;
    }

    public int getDCPS() {
        return this.DCPS;
    }

    public int getEXPS() {
        return this.EXPS;
    }

    public int getCost() {
        return this.cost;
    }
}
