package entities;

/**
 * This class represents a dog.
 * @author Juntae
 */
public class Dog {
    private int coinsEarned;
    private int exp;

    // The coins and exp calculator could be in their own use cases, however they would be
    // very small and would be Lazy Classes. Might as well just put them here.

    /**
     * Calculates the amount of coins earned from petting this dog.
     * @return The amount of coins earned.
     */
    public int calculateCoinsEarned() {
        return (this.exp / 1000) + 1;
    }

    /**
     * Calculates the EXP earned from petting this dog.
     * @return The EXP earned.
     */
    public int calculateExpEarned() {
        return (this.coinsEarned / 200) + 1;
    }

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
