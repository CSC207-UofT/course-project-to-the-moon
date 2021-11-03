package usecases;

import java.awt.Color;
import java.awt.image.BufferedImage;

import adaptors.IGameGraphics;

/**
 * Represents the HUD of the game that displays all relevant information
 * @author Juntae Park
 * @since 2 October 2021
 */

public class HUDObject extends AbstractObject implements Drawable{
    private Bank bank;
    // To-do: decide if we want to display metrics other than coin. net exp maybe?

    public HUDObject(double x, double y, Bank bank) {
        super(x,y);
        this.bank = bank;
    }

    @Override public void draw(IGameGraphics g, int offsetX, int offsetY) {

        //To-do: add sprite for the UI? Is it necessary? Maybe a cool looking box where the text will be placed?
        //BufferedImage frame = this.getSprite().getCurrentFrame();

        int drawnX = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        //g.drawImage(frame, drawnX, drawnY);

        g.drawText("NetCoins: " + this.bank.getCoin(), drawnX, drawnY, Color.WHITE);
    }


}
