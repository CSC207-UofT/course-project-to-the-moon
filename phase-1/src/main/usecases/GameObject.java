package usecases;
import java.awt.Rectangle;

/**
 * A "manager" class that represents a collidable game object that the player can see and possibly
 * interact with.
 * @author Andy Wang, Juntae Park
 * @since 21 October 2021
 */
public class GameObject extends AbstractObject implements Collidable{
    private Mover mover = null;

    /**
     * Initializes a game object at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public GameObject(double x, double y) {
        super(x,y);
        
    }

    /**
     * Adds and runs an implementation of Mover to control the movement of this object.
     *
     * Preconditions: this.mover == null
     * @param m The mover to add.
     */
    public void addMover(Mover m) {
        if (this.mover == null) {
            this.mover = m;
        }
    }

    public Mover getMover() {
        return this.mover;
    }

    @Override public Rectangle getHitBox() {
        return new Rectangle((int) super.getX(), (int) super.getY(), super.getSprite().getWidth(), super.getSprite().getHeight());
    }
   
}
