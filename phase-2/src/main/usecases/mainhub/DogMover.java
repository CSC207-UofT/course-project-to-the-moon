package usecases.mainhub;


import entities.Sprite;
import entities.Transform;
import usecases.interfaces.Mover;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class continuously moves the given dog randomly in a separate thread.
 * @author Jimin Song, Andy Wang, Juntae Park
 * @since 10 October 2021
 */
public class DogMover implements Mover {
    private final Sprite dogSprite ;
    // the size of the boundaries
    private final int width;
    private final int height;

    /**
     * Initializes a new DogMover that continuously moves a dog, given a boundary of where the dog
     * is allowed to be.
     * @param dogSprite The dog's sprite.
     * @param width The width of the boundary.
     * @param height The height of the boundary.
     */
    public DogMover(Sprite dogSprite, int width, int height){
        this.dogSprite = dogSprite;
        this.width = width;
        this.height = height;
    }

    /**
     * Continuously moves the dog; it moves to a random location, idles for a bit, then moves again.
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

                // flip the dog accordingly
                if ((t.getX() < newX) && !dogSprite.flipped()) {
                    dogSprite.flip();
                } else if ((t.getX() > newX) && dogSprite.flipped()) {
                    dogSprite.flip();
                }

                // move the dog to the new location
                try {
                    t.tweenTo(newX, newY, time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.scheduleAtFixedRate(moverTask, 1000, 7000);
    }
}