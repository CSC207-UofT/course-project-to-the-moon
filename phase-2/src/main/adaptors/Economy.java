package adaptors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import usecases.mainhub.MinerButton;

public class Economy implements PropertyChangeListener {
    private ArrayList<MinerButton> items;
    private int[][] transMatrix;
    private int stateIndex;
    private int dCost;
    private GameReadWriter grw;
    
    /**
     * Initializes a new Economy class, responsible for generating and managing the random walk steps for the in-game shop
     * item prices. 
     */
    public Economy() {
        this.stateIndex = 0; //start at dull market
        this.transMatrix = this.initializeTransMatrix();
        this.items = new ArrayList<MinerButton>();

        this.run();
    }

    /**
     * Adds a GameReadWriter 
     * @param grw
     */
    public void addReadWriter(GameReadWriter grw){
        this.grw = grw;
    }

    /**
     * At a set interval, generates the next step in random walk and updates the item prices.
     */
    public void run() {
        Timer timer = new Timer();
        TimerTask banktask = new TimerTask() {
            @Override
            public void run() {
                updateState();
                for(MinerButton item : items) {
                    updateItem(item);
                }
            }
        };
        // 5 mins = 300000 miliseconds
        timer.scheduleAtFixedRate(banktask, 100, 2000);
    }

    /**
     * Updates the transition matrix given the price change direction from API call.
     * @param sign
     */
    public void updateMatrix(int sign){
        if (sign > 0){ 
            // price increase
            for (int i = 0; i < 2; i++){
                this.transMatrix[i][0] -= 1;
                this.transMatrix[i][1] += 2;
                this.transMatrix[i][2] -= 1;
            }
        } else if (sign < 0) {
            // price decrease
            for (int i = 0; i < 2; i++){
                this.transMatrix[i][0] -= 1;
                this.transMatrix[i][1] -= 1;
                this.transMatrix[i][2] += 2;
            }
        } else {
            //price stays same
            for (int i = 0; i < 2; i++){
                this.transMatrix[i][0] += 2;
                this.transMatrix[i][1] -= 1;
                this.transMatrix[i][2] -= 1;
            }

        }
    }

    /**
     * Adds a MinerButton item
     * @param item
     */
    public void addItem(MinerButton item) {
        this.items.add(item);
    }

    /**
     * Updates the cost of the given item, sets it to its max cost if the new increase would go past it.
     * @param item
     */
    public void updateItem(MinerButton item) {
        int newCost = item.getCost() + this.dCost;
        if(newCost > item.getMaxCost()){
            newCost = item.getMaxCost();
        }
        item.setCost(newCost);
    }

    // for test
    public ArrayList<MinerButton> getItems(){
        return this.items;
    }

    /**
     * Initializes the transition matrix used for random walk; values from historical dogecoin price changes.
     * @return
     */
    private int[][] initializeTransMatrix() {
        //preset values
        return new int[][]{{54, 22, 24}, {32, 36, 32}, {34, 29, 37}};
    }

    /**
     * Generate next step in random walk.
     */
    private void updateState() {
        //0 = dull market / no price change, 1 = bull market / price increase, 2 = bear market / price decrease
        int dullRange = this.transMatrix[stateIndex][0];
        int bullRange = dullRange + this.transMatrix[stateIndex][1];

        Random rand = new Random();
        int randInt = rand.nextInt(101);

        if (randInt <= dullRange) {
            this.stateIndex = 0;
            this.dCost = 0;
        } else if ((dullRange < randInt) && (randInt <= bullRange)) {
            this.stateIndex = 1;
            this.dCost = 10;
        } else {
            this.stateIndex = 2;
            this.dCost = -10;
        }
    }

    /**
     * If the game was loaded in a new day since it was last opened, makes API call.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LocalDate lastDate = (LocalDate) this.grw.getGameState().getState().get("Date");
        if(!lastDate.equals(LocalDate.now())){
            try {
                MarketAPI api = new MarketAPI();
                updateMatrix(api.getSign());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
