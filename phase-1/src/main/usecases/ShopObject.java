package usecases;

/**
 * This class represents an item in the shop.
 * @author Fatimeh Hassan, edited by Juntae
 */

public class ShopObject implements Shoppable {
    private int cost;
    private int DCPS;
    private int EXPS;

    public ShopObject(int cost, int DCPS, int EXPS) {
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

    @Override public int getCost() {
        return this.cost;
    }

    @Override
    public void increasePrice(int addPrice) {
        //increase the cost of this item by addPrice
        this.cost += addPrice;
    }

}
