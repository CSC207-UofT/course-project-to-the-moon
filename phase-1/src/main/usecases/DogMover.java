package usecases;

import entities.Dog;
import entities.Sprite;
import entities.Transform;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class continuously moves the given dog randomly in a separate thread.
 * @author Jimin Song and Andy Wang
 * @since 10 October 2021
 */
public class DogMover implements Mover{
    private final Dog dog;
    private final Sprite dogSprite;
    // the size of the boundaries
    private final int width;
    private final int height;
    // random number generator
    private final Random rand = new Random();


    /**
     * Initializes a new DogMover that continuously moves a dog, given a boundary of where the dog
     * is allowed to be.
     * @param dog The dog to move.
     */
    public DogMover(Dog dog, Sprite dogSprite, int height, int width){
        this.dog = dog;
        this.dogSprite = dogSprite;
        this.width = width;
        this.height = height;

    }

    /**
     * Continuously moves the dog.
     */
    @Override
    public void run(Transform t) {
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
                if ((dog.getX() < newX) && !dogSprite.flipped()) {
                    dogSprite.flip();
                } else if ((dog.getX() > newX) && dogSprite.flipped()) {
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

        timer.scheduleAtFixedRate(moverTask, 1000, 5000);
    }
}
