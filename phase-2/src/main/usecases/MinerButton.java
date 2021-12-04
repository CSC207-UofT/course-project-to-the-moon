package usecases;

import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A class to represent a Dogecoin miner button.
 * @author Fatimeh Hassan
 * @since 12 November 2021
 */

public class MinerButton extends TextButton {
    private int cost;
    private final int costIncrease;
    private final int dcps;
    private final Bank bank;

    /**
     * Initialize a new miner button.
     *
     * @param r            The bounds of the button.
     * @param text         The text of the button.
     * @param tag          The tag of the button.
     * @param bank         The bank to control.
     * @param cost         The initial cost of the miner.
     * @param costIncrease How much the cost increases per purchase.
     * @param dcps         The dogecoin per second that this miner gives.
     */
    public MinerButton(Rectangle r, String text, String tag, Bank bank, int cost, int costIncrease, int dcps) {
        super(r, text, tag, null);
        this.bank = bank;
        this.cost = cost;
        this.costIncrease = costIncrease;
        this.dcps = dcps;

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }

    @Override
    public void onClick() {
        if (bank.makePurchase(cost)) {
            bank.increaseDCPS(dcps);
            this.cost += costIncrease;

            String name = this.text;
            setText("Purchased!");

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            setText(name);
                        }
                    },
                    2000
            );
        }
    }
}



