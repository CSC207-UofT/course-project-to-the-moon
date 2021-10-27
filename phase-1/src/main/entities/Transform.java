package entities;

/**
 * This class holds and edits coordinates.
 * @author Arip Paydari
 * @since 21 October 2021
 */
public class Transform {
    double x,y; // coordinates in WORLD POSITION!!!! NOT relative to whatever surface it's drawn on!!!

    /**
     * Initializes a new Transform object based on the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Transform(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the Transform to the new coordinates.
     * @param x The new x coordinate.
     * @param y The new y coordinate.
     */
    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Translates the Transform by a given displacement.
     * @param dx The x displacement.
     * @param dy The y displacement
     */
    public void translateBy(double dx, double dy){
        this.x += dx;
        this.y += dy;
    }

    /**
     * Smoothly move this Transform to the given coordinates.
     * @param x The new x coordinate.
     * @param y The new y coordinate.
     * @param time The time in seconds to move this Transform.
     * @throws InterruptedException If there is an error while tweening.
     */
    public void tweenTo(double x, double y, double time) throws InterruptedException {
        int delay = 10;
        int numIterations = (int) ((time * 1000) / delay);

        int currX = (int) this.getX();
        int currY = (int) this.getY();

        // these represent x and y velocities
        double dx = (x - currX) /  numIterations;
        double dy = (y - currY) / numIterations;

        for (int i = 0; i < numIterations; i++) {
            this.translateBy(dx, dy);

            Thread.sleep(delay);
        }
    }

    /**
     * Return the x coordinate.
     * @return The x coordinate.
     */
    public double getX(){
        return this.x;
    }

    /**
     * Return the y coordinate.
     * @return The y coordinate.
     */
    public double getY(){
        return this.y;
    }
}
