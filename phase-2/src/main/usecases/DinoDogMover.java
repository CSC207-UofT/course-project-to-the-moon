package usecases;

import adaptors.ICamera;
import adaptors.IGameController;
import entities.Transform;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class controls the PlatformerDogObject, with gravity.
 *
 * Use the left and right keys to move.
 *
 * @author Aria Paydari
 * @since 24 November 2021
 */
public class DinoDogMover implements Mover {
    private final DinoDogGameObject dog ;
    private final IGameController controller ;
    private final Bank bank;
    private final Stage dinoStage;
    private final ICamera camera;
    private final Timer timer = new Timer();

    private final int JUMP_SPEED = -20;
    private final int RUN_SPEED = 2;
    private final float GRAVITY = .8F;
    private boolean jumped =false;
    private float dy = 0;



    private boolean won;

    /**
     * Initializes a new PlatformerDogMover to give the player control of the platformer dog.
     * @param dog The PlatformerDogGameObject object to change.
     * @param bank The bank to update.
     * @param controller The controller to use.
     */
    public DinoDogMover(DinoDogGameObject dog, Bank bank, IGameController controller) {
        dog.getSprite().flip();
        this.dog = dog;
        this.bank = bank;
        this.controller = controller;
        this.camera = controller.getCamera();
        dinoStage = this.controller.getStage("Dino");
    }

    /**
     * Continuously moves the dog; it moves to a random location, idles for a bit, then moves again.
     */
    @Override
    public void run(Transform t) {
        TimerTask moverTask = new TimerTask() {
            @Override
            public void run() {
                // Initial is (70, 200)
                if(getSpace() && !jumped){
                    dy = JUMP_SPEED;
                    jumped = true;
                }
                else if (t.getY() < 200){
                    dy += GRAVITY;
                }
                else{
                    dy=0;
                    jumped = false;
                }
                t.translateBy(RUN_SPEED, dy);
                updateXPositionAndCamera(t);

                // Flip the sprite if the player is moving right
                // or flip the sprite back to face left if the player moves from right to left

            }
        };

        timer.scheduleAtFixedRate(moverTask, 10, 25);
    }

    // return 1 if left is pressed, 0 otherwise
    private boolean getSpace() {
        return controller.getKeyPressed(KeyEvent.VK_SPACE);
    }

    // return 1 if right is pressed, 0 otherwise
    private boolean getDownDegree() {
        return controller.getKeyPressed(KeyEvent.VK_RIGHT);
    }


    private boolean hitPlatform() {

        boolean hitNormalPlatform = dinoStage.placeMeeting(dog, dog.getX() + 1, dog.getY(),
                "Platform") ;
        boolean onWinningPlatform = (dinoStage.placeMeeting(dog, dog.getX(), dog.getY() + 1,
                "WinningPlatform") && dy > 0);

        if (onWinningPlatform) {
            won = true;
        }
        return (hitNormalPlatform || onWinningPlatform);
    }



    // update x position
    // this method also ends the game if the dog goes too low, or if the dog stands on the winning platform
    private void updateXPositionAndCamera(Transform t) {

        Transform cameraTransform = camera.getTransform();

        // Only move the camera right if the dog's x position is close to camera's x position


        cameraTransform.translateBy( RUN_SPEED, 0);




        // end the game if you win
        if (won) {
            controller.setActiveStage("Main");
            int coinsEarned = 50000 + 100 * bank.getDCPS();
            bank.updateCoins(coinsEarned);
            System.out.println("u won " + coinsEarned + " coins!");
            timer.cancel();
        }
        // Ends game if you hit a platform
        else if (hitPlatform()){
            controller.setActiveStage("Main");
            System.out.println("u lost the game lmao");
            timer.cancel();

        }
    }
}