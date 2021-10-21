package entities;

/**
 * This class holds coordinates for another object
 * such as dog, which can be changed
 * and it is saved here to avoid code duplication
 * @author Arip Paydari
 * @since 21 October 2021
 */
public class Transform {
    double x,y;

    public Transform(double x, double y){
        this.x =x;
        this.y = y;

    }

    public void moveTo(double x, double y){
        this.x =x;
        this.y =y;

    }

    public void translateBy(double x, double y){
        this.x += x;
        this.y +=y;

    }

    private void tweenTo(double x, double y, double timer){
        int delay = 10;
        int numIterations = (int) ((timer * 1000) / delay);

        int currX = (int) this.getX();
        int currY = (int) this.getY();

        // these represent x and y velocities of the dog
        double dx = (x - currX) /  numIterations;
        double dy = (y - currY) / numIterations;

        for (int i = 0; i < numIterations; i++) {
            this.translateBy(dx, dy);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Something went wrong while moving!");
            }
        }

    }

    public double getX(){return this.x;}

    public double getY(){return this.y;}
}
