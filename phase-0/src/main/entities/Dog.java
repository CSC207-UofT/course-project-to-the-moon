package entities;

/**
 * This class represents a dog.
 * @author Juntae
 */
public class Dog {
    private int coinsEarned;
    private int exp;
    // dog position
    private double x;
    private double y;

    /**
     * This method translate the dog by a certain amount.
     * @param dx The x-amount to translate by.
     * @param dy The y-amount to translate by.
     */
    public void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    //getters and setters
    public int getCoins() { return this.coinsEarned; }
    
    public int getExp() { return this.exp; }

    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    public void setCoins(int coin) {
        this.coinsEarned = coin;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
}
