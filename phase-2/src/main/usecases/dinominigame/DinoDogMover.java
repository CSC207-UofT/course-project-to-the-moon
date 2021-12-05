package usecases.dinominigame;

import adaptors.ICamera;
import adaptors.IFrameLoader;
import adaptors.IGameController;
import entities.Transform;
import usecases.Bank;
import usecases.interfaces.Mover;
import usecases.Stage;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class controls the DinoDogObject, with gravity.
 *
 * Use the space bar to jump,
 * and the down key to duck.
 *
 * @author Aria Paydari
 * @since 23 November 2021
 */
public class DinoDogMover implements Mover {
    private final DinoDogGameObject dog ;
    private final IGameController controller ;
    private final IFrameLoader loader;
    private final Bank bank;
    private final Stage dinoStage;
    private final ICamera camera;
    private final Timer timer = new Timer();

    private final int JUMP_SPEED = 12;
    private final int RUN_SPEED = 4;
    private final float GRAVITY = .8F;
    private boolean jumped =false;
    private boolean ducked = false;
    private float dy = 0;

    private boolean won;

    /**
     * Initializes a new PlatformerDogMover to give the player control of the platformer dog.
     * @param dog The PlatformerDogGameObject object to change.
     * @param bank The bank to update.
     * @param stage The stage to use.
     * @param controller The controller to use.
     */
    public DinoDogMover(DinoDogGameObject dog, Bank bank, Stage stage, IGameController controller,
                        IFrameLoader loader) {
        this.dog = dog;
        this.bank = bank;
        this.controller = controller;
        this.camera = controller.getCamera();
        this.loader = loader;
        dinoStage = stage;
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
                // Checks if it's in the air
                // And the jump hasn't been initiated.
                if(getSpace() && !jumped){
                    dy = -JUMP_SPEED;
                    jumped = true;
                    controller.playSound("jump.wav", 0);
                }
                // This case is when the dog is in the air
                // And it's being brought down by gravity
                else if (t.getY() < 200){
                    dy += GRAVITY;
                }
                else{

                    jumped = false;
                    if (getDown()){
                        if(!ducked){


                            dy = 10;
                            ducked = true;
                            dog.switchSprite(true);
                        }
                        else{
                            dy=0;
                        }

                        // dog.setSprite();
                    }
                    else{
                        if(ducked){
                            t.translateBy(0, -10);
                            dog.switchSprite(false);

                        }
                        dy=0;

                        ducked = false;
                    }
                }

                t.translateBy(RUN_SPEED, dy);
                updateXPositionAndCamera(t);

                // Flip the sprite if the player is moving right
                // or flip the sprite back to face left if the player moves from right to left

            }
        };

        timer.scheduleAtFixedRate(moverTask, 10, 25);
    }

    // returns whether space is being pressed.
    private boolean getSpace() {
        return controller.getKeyPressed(KeyEvent.VK_SPACE);
    }
    // returns whether the down key is being pressed.
    private boolean getDown() {
        return controller.getKeyPressed(KeyEvent.VK_DOWN);
    }




    private boolean hitPlatform() {

        synchronized (dinoStage) {
            boolean hitNormalPlatform = dinoStage.placeMeeting(dog, dog.getX() + 1, dog.getY(),
                    "Platform");
            boolean onWinningPlatform = (dinoStage.placeMeeting(dog, dog.getX(), dog.getY() + 1,
                    "WinningPlatform") && dy > 0);

            if (onWinningPlatform) {
                won = true;
            }
            return (hitNormalPlatform || onWinningPlatform);
        }
    }



    // update x position
    // this method also ends the game if the dog goes too low, or if the dog stands on the winning platform
    private void updateXPositionAndCamera(Transform t) {

        Transform cameraTransform = camera.getTransform();

        // Only move the camera right if the dog's x position is close to camera's x position


        cameraTransform.translateBy( RUN_SPEED, 0);




        // end the game if you win
        if (won) {
            controller.playSound("win.wav", 0);
            controller.setActiveStage("Main");
            int coinsEarned = 50000 + 100 * bank.getDCPS();
            bank.updateCoins(coinsEarned);
            System.out.println("u won " + coinsEarned + " coins!");
            timer.cancel();
        }
        // Ends game if you hit a platform
        else if (hitPlatform()){
            controller.playSound("loss.wav", 0);
            controller.setActiveStage("Main");
            System.out.println("u lost the game lmao");
            timer.cancel();

        }
    }
}