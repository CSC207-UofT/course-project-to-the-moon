package usecases;

import java.awt.Color;
import java.awt.image.BufferedImage;

import adaptors.Economy;
import adaptors.IGameGraphics;

/**
 * Represents the HUD of the game that displays all relevant information
 * @author Juntae Park
 * @since 2 October 2021
 */

public class UIObject extends AbstractObject implements Drawable{
    private int coinDisplay;
    // To-do: decide if we want to display metrics other than coin. net exp maybe?

    public UIObject(double x, double y) {
        super(x,y);
        this.coinDisplay = 0;
    }


    @Override public void draw(IGameGraphics g, int offsetX, int offsetY) {
        this.coinDisplay = Economy.netCoin;

        //To-do: add sprite for the UI? Is it necessary? Maybe a cool looking box where the text will be placed?
        //BufferedImage frame = this.getSprite().getCurrentFrame();

        int drawnX = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        //g.drawImage(frame, drawnX, drawnY);

        g.drawText("NetCoins: " + this.coinDisplay, drawnX, drawnY, Color.WHITE);
    }


}
