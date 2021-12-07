package usecases.dinominigame;

import adaptors.IGameController;
import adaptors.IGameGraphics;
import usecases.*;
import usecases.interfaces.Collidable;
import usecases.interfaces.Drawable;
import usecases.object.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A class which manages a single dog object
 * Within the minigame.
 * @author Aria Paydari
 * @since 23 November 2021
 */
public class DinoDogGameObject extends GameObject implements Drawable, Collidable {
    ArrayList<SpriteFacade> spriteList;

    /**
     * Initializes a new DogGameObject at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param spriteList The Arraylist containing the two sprites of the dog.
     * @param bank The bank to update.
     * @param stage The minigame stage.
     * @param controller The controller controlling this DogGameObject.
     */
    public DinoDogGameObject(int x, int y, ArrayList<SpriteFacade> spriteList, Bank bank, Stage stage,
                             IGameController controller){
        super(x, y, "DinoDogGameObject", spriteList.get(0), controller);

        this.spriteList =spriteList;
        DinoDogMover dogMover = new DinoDogMover(this, bank, stage, controller);
        this.addMover(dogMover);
    }

    @Override
    public void draw(IGameGraphics g, int offsetX, int offsetY) {
        BufferedImage frame = this.getCurrentFrame();
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);

        // draw the hitbox -- only for debugging
//        Rectangle hitbox = this.getHitBox();
//        g.fillRect(drawnX, drawnY + 5 * (this.getHeight() / 6), hitbox.width, hitbox.height, Color.GREEN);
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle((int) this.getX(), (int) this.getY(),
                this.getWidth(), this.getHeight());
    }

    @Override
    public Rectangle getHitBoxAtCoords(int x, int y) {
        return new Rectangle(x, y,
                this.getWidth(), this.getHeight());
    }

    public void switchSprite(boolean ducked){
        int val = ducked? 1 : 0;
        super.setSprite(this.spriteList.get(val));
    }

}
