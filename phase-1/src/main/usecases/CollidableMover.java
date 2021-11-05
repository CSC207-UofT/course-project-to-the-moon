package usecases;

import usecases.CollisionSystem;
import entities.Dog;
import entities.Sprite;
import entities.Transform;

import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class continuously moves the given dog randomly in a separate thread.
 * @author Jimin Song, Andy Wang, Juntae Park
 * @since 10 October 2021
 */
public class CollidableMover implements Mover {
    private final Collidable obj;
    // the size of the boundaries
    private final int width;
    private final int height;
    private Stage stage;

    /**
     * Initializes a new DogMover that continuously moves a dog, given a boundary of where the dog
     * is allowed to be.
     * @param dogSprite The dog's sprite.
     * @param width The width of the boundary.
     * @param height The height of the boundary.
     */
    public CollidableMover(Collidable c, int width, int height, Stage stage){
        this.obj = c;
        this.width = width;
        this.height = height;
        this.stage = stage;
    }

    /**
     * COPY-PASTED FROM DOGMOVER, NEED TO CHANGE
     */
    @Override
    public void run(Transform t) {
        Random rand = new Random();

        Timer timer = new Timer();
        TimerTask moverTask = new TimerTask() {
            @Override
            public void run() {
                // choose a random location to move to
                int newX = rand.nextInt(width);
                int newY = rand.nextInt(height);

                // choose the time it takes
                double time = 1 + rand.nextDouble() * 2;

                // REMOVED FLIP FEATURE FOR TESTING PURPOSES

                // move the dog to the new location
                try {
                    tweenTo(t, newX, newY, time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.scheduleAtFixedRate(moverTask, 1000, 7000);
    }

    /**
     * Smoothly move this Transform to the given coordinates.
     * @param x The new x coordinate.
     * @param y The new y coordinate.
     * @param time The time in seconds to move this Transform.
     * @throws InterruptedException If there is an error while tweening.
     */
    public void tweenTo(Transform t, double x, double y, double time) throws InterruptedException {
        int delay = 10;
        int numIterations = (int) ((time * 1000) / delay);

        int currX = (int) t.getX();
        int currY = (int) t.getY();

        // these represent x and y velocities
        double dx = (x - currX) /  numIterations;
        double dy = (y - currY) / numIterations;
        t.setDx(dx);
        t.setDy(dy);

        for (int i = 0; i < numIterations; i++) {
            boolean collided = CollisionSystem.placeMeeting(stage.getGameObjects(), this.obj, t.getX()+dx, t.getY()+dy);
            t.translateBy(t.getDx(), t.getDy());
            
            System.out.print("Moving...\n");

            if (collided) { 
                System.out.print("Collided!\n");
                break; 
            } // break loop and move somewhere else

            Thread.sleep(delay);
        }

        t.setDx(0);
        t.setDy(0);
    }
}