package usecases;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class to store and operate on currency (Dogecoin).
 * @author Andy Wang
 * @since 30 October 2021
 */
public class Bank {
    private int coins;
    private int dcps; //dogecoin per second
    private CoinListener observer = null;

    public Bank() {
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                coins += dcps;
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * Changes the amount of coins by the given amount and notifies observers.
     * @param c The amount of coins to increase it by.
     */
    public void updateCoins(int c) {
        this.coins += c;

        this.observer.update(this.coins);
    }

    /**
     * Increases the dogecoin per second by the given amount.
     * @param dcps The amount of dogecoin per second to increase it by.
     */
    public void increaseDCPS(int dcps) {
        this.dcps += dcps;
    }

    /**
     * Makes a transaction, and returns whether it was successful.
     * @return Whether the transaction was successful. True iff coins >= cost.
     */
    public boolean makePurchase(int cost) {
        if (this.coins >= cost) {
            this.updateCoins(-cost);
            return true;
        } return false;
    }

    public int getCoins(){
        return this.coins;
    }

    public void addObserver(CoinListener cl) {
        this.observer = cl;
    }
}