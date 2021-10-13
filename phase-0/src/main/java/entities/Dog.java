package java.entities;

import java.usecases.DogMover;

/**
 * This class represents a dog.
 * @author Juntae
 */
public class Dog {
    private int coins;
    private int level;
    private double exp;
    // dog position
    private int x;
    private int y;

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
    public int getCoins() {
        return this.coins;
    }
    public int getLevel() {
        return this.level;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public void setCoins(int coin) {
        this.coins = coin;
    }
    public void setExp(double exp) {
        this.exp = exp;
    }
}
