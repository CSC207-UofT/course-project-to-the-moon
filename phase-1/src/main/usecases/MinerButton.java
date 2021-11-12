package usecases;

import entities.Bank;

import java.awt.*;

/**
 * A class to represent a Dogecoin miner button.
 * @author Fatimeh Hassan
 * @since 12 November 2021
 */

public class MinerButton extends TextLabel implements Clickable{
    private int cost;
    private int costIncrease;
    private int dcps;
    private Bank bank;

    public MinerButton(Rectangle r, String text, String tag, Bank bank, int cost, int costIncrease, int dcps) {
        super(r, text, tag);
        this.bank = bank;
        this.cost = cost;
        this.costIncrease = costIncrease;
        this.dcps = dcps;

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean isClicked(int mouseX, int mouseY) {
        double x = this.getX();
        double y = this.getY();
        int width = (int) super.rectangle.getWidth();
        int height = (int) super.rectangle.getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    @Override
    public void onClick() {
          if (bank.makePurchase(cost)) {
              bank.increaseDCPS(dcps);
              setCost((cost + costIncrease));
          }
    }
}
