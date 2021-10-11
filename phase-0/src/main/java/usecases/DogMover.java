package main.java.usecases;

import main.java.entities.Dog;
import java.util.Random;

/**
 * This class continuously moves the given dog randomly in a separate thread.
 * @author Jimin Song
 * @since 10 October 2021
 */
public class DogMover {
    public Dog dog;
    // signs are for changing the direction of dog coordinates
    private int x_sign = 1;
    private int y_sign = 1;
    // the size of the frame
    private int w;
    private int h;
    private Random rand = new Random();

    public DogMover(Dog dog, int w, int h){
        this.dog = dog;
        this.w = w;
        this.h = h;
    }

    public void randomLocation(){
        float chance = rand.nextFloat();
        // chance is the chance of the dog movings.
        if (chance <= 0.1){
            int delta_x = rand.nextInt(10);
            int delta_y = rand.nextInt(10);

            if (this.dog.x + this.dog.d_width + delta_x*x_sign>= this.w){
                this.x_sign = -1;
            }
            else if (this.dog.x + delta_x*x_sign <=0){
                this.x_sign = 1;
            }

            if (this.dog.y + this.dog.d_height + delta_y*y_sign >= this.h){
                this.y_sign = -1;
            }
            else if (this.dog.y + delta_y*y_sign <=0){
                this.y_sign = 1;
            }
            this.dog.moveDog(delta_x * x_sign, delta_y * y_sign);
        }
    }
}
