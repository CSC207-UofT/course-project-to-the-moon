package usecases;

import entities.Dog;
import entities.Sprite;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class continuously moves the given dog randomly in a separate thread.
 * @author Jimin Song and Andy Wang
 * @since 10 October 2021
 */
public class DogMover {
    private Dog dog;
    private Sprite dogSprite;
    // these represent x and y velocities of the dog
    private float dx, dy;
    // the size of the boundaries
    private int width, height;
    // random number generator
    private Random rand = new Random();

    /**
     * Initializes a new DogMover that continuously moves a dog, given a boundary of where the dog
     * is allowed to be.
     * @param dog The dog to move.
     */
    public DogMover(Dog dog, Sprite dogSprite){
        this.dog = dog;
        this.dogSprite = dogSprite;
        this.width = 200;
        this.height = 200;

        // TODO: make it take in width and height as parameters
    }

    /**
     * Continuously moves the dog.
     */
    public void startMoving() {
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
                if ((dog.getX() < newX) && !dogSprite.isFlipped()) {
                    dogSprite.flip();
                } else if ((dog.getX() > newX) && dogSprite.isFlipped()) {
                    dogSprite.flip();
                }

                // move the dog to the new location
                moveDogTo(newX, newY, time);
            }
        };

        timer.scheduleAtFixedRate(moverTask, 1000, 5000);
    }
    /**
     * A helper method to move the dog to the given location, within the given time.
     * @param x The x-coordinate to move the dog to.
     * @param y The y-coordinate to move the dog to.
     * @param t The time it takes to move the dog in seconds.
     */
    private void moveDogTo(int x, int y, double t) {
        int delay = 10;
        int numIterations = (int) ((t * 1000) / delay);

        int currX = (int) this.dog.getX();
        int currY = (int) this.dog.getY();

        this.dx = (x - currX) / (float) numIterations;
        this.dy = (y - currY) / (float) numIterations;

        for (int i = 0; i < numIterations; i++) {
            this.dog.translate(this.dx, this.dy);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Something went wrong moving the dog");
            }
        }
        this.dx = 0;
        this.dy = 0;
    }
}
