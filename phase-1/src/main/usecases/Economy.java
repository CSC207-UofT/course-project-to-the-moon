package usecases;

import java.util.Random;

import entities.Bank;

public class Economy {
    Bank bank;
    int[][] transMatrix;
    int currState;
    
    public Economy() {
        this.bank = new Bank();
        this.transMatrix = this.initializeTransMatrix();
        this.currState = 0; //start at dull market
    }

    public Bank getBank() {
        return this.bank;
    }

    private int[][] initializeTransMatrix() {
        //add funtionality to read csv file and generate entirely new chain
        return new int[][]{{54, 22, 24}, {32, 36, 32}, {34, 29, 37}};
    }

    //generate next step in random walk and return the market state
    private int updateState() {
        //0 = dull market / no price change, 1 = bull market / price increase, 2 = bear market / price decrease
        int dullRange = this.transMatrix[currState][0];
        int bullRange = dullRange + this.transMatrix[currState][1];
        int bearRange = bullRange + this.transMatrix[currState][2];

        Random rand = new Random();
        int randInt = rand.nextInt(101);

        if (randInt <= dullRange) {
            this.currState = 0;
        } else if ((dullRange < randInt) && (randInt <= bullRange)) {
            this.currState = 1;
        } else {
            this.currState = 2;
        }
        return this.currState;
    }

    private void updateTransMatrix() {
        
    }

}
