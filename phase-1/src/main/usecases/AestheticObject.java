package usecases;

import adaptors.IGameGraphics;
import java.awt.image.BufferedImage;
import entities.Transform;

/**
 * Hats for doggies
 * @author Juntae Park
 * @since 29 October 2021
 */
public class AestheticObject extends AbstractObject implements Drawable, Mover, Shoppable{
    private int cost;

    public AestheticObject(double x, double y, int cost) {
        super(x,y);
        this.cost = cost;
    }

    @Override
    public void run(Transform t) {
        //To-do: make this object follow the position of the dog to which it is assigned to
    };

    @Override 
    public void draw(IGameGraphics g, int offsetX, int offsetY) {
        /*
        BufferedImage frame = this.getSprite().getCurrentFrame();
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);

        g.drawText("exp: " + Integer.toString(this.myDog.getExp()),
                drawnX + 30, drawnY + 95, Color.WHITE);
        */
    }

    @Override public int getCost() {
        return this.cost;
    }

    @Override public void increasePrice(int addPrice) {
        this.cost += addPrice;
    }
}
