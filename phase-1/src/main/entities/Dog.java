package entities;

/**
 * This class represents a dog.
 * @author Juntae
 */
public class Dog {
    private int coinsEarned;
    private int exp;

    //getters and setters
    public int getCoins() {
        return this.coinsEarned;
    }

    public int getExp() {
        return this.exp;
    }

    public void setCoins(int coin) {
        this.coinsEarned = coin;
    }
    public void setExp(int exp) {
        this.exp = exp;
    }
}
