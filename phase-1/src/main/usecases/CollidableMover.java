package usecases;

import entities.Dog;
import entities.Sprite;
import entities.Transform;

import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * COPY-PASTED FROM DOGMOVER, NEED TO REMOVE TWEEN TO & CHANGE SPECIFICS OF RUN METHOD DEPENDING ON WHAT TYPE OF
 * OBJECT IT IS.
*/
public class CollidableMover implements Mover {
    private final Collidable obj;
    // the size of the boundaries
    private final Stage stage;

    /**
     * Initializes a new CollidableMover that checks for collision every time object is moved.
     * is allowed to be.
     * @param c The object, must implement Collidable
     * @param width The width of the boundary.
     * @param height The height of the boundary.
     * @param stage The stage the object is set in.
     */
    public CollidableMover(Collidable c, Stage stage){
        this.obj = c;
        this.stage = stage;
    }

    
    @Override
    public void run(Transform t) {
        //the new coordinates we want to move this object to
        double dx = 0; //placeholder
        double dy = 0; //placeholder

        // THE BELOW TWO LINES ARE THE CORE OF COLLIDABLE MOVER
        boolean collided = this.stage.placeMeeting(this.obj, t.getX()+dx, t.getY()+dy);
        t.translateBy(t.getDx(), t.getDy());
        
        if (collided) { 
            // optional; can execute specific things if collision is detected  
        } 
    }

}