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
 * @author Andy Wang
 * @since 12 November 2021
 */
public class PlatformerDogMover implements Mover {
    private PlatformDogGameObject dog = null;
    private IGameController controller = null;
    private Stage minigameStage = null;
    private ICamera camera = null;
    private final Timer timer = new Timer();

    private final int JUMP_SPEED = 13;
    private final int WALK_SPEED = 5;
    private final float GRAVITY = .6F;

    private float dx = 0;
    private float dy = 0;

    private boolean won;

    /**
     * Initializes a new PlatformerDogMover to give the player control of the platformer dog.
     * @param dog The PlatformerDogGameObject object to change.
     * @param controller The controller to use.
     */
    public PlatformerDogMover(PlatformDogGameObject dog, IGameController controller) {
        this.dog = dog;
        this.controller = controller;
        this.camera = controller.getCamera();
        minigameStage = this.controller.getStage("Minigame");
    }

    /**
     * Continuously moves the dog; it moves to a random location, idles for a bit, then moves again.
     */
    @Override
    public void run(Transform t) {
        TimerTask moverTask = new TimerTask() {
            @Override
            public void run() {
                // The logic here is courtesy of
                // https://www.youtube.com/watch?v=izNXbMdu348

                int move = getRightDegree() - getLeftDegree();
                dx = move * WALK_SPEED;
                dy += GRAVITY;

                // HANDLE JUMPING
                if (onPlatform()) {
                    dy = -JUMP_SPEED;
                }

                t.translateBy(dx, 0);
                handleYCollisions(t);
                updateYPositionAndCamera(t);

                // Flip the sprite if the player is moving right
                // or flip the sprite back to face left if the player moves from right to left
                if (((move == 1) && !dog.getSprite().flipped()) ||
                        ((move == -1) && dog.getSprite().flipped())) {
                    dog.getSprite().flip();
                }
            }
        };

        timer.scheduleAtFixedRate(moverTask, 10, 25);
    }

    // return 1 if left is pressed, 0 otherwise
    private int getLeftDegree() {
        if (controller.getKeyPressed(KeyEvent.VK_LEFT)) {
            return 1;
        } return 0;
    }

    // return 1 if right is pressed, 0 otherwise
    private int getRightDegree() {
        if (controller.getKeyPressed(KeyEvent.VK_RIGHT)) {
            return 1;
        } return 0;
    }

    // return if the dog is standing on a platform
    private boolean onPlatform() {
        boolean onNormalPlatform = (minigameStage.placeMeeting(dog, dog.getX(), dog.getY() + 1,
                "Platform") && dy > 0);
        boolean onWinningPlatform = (minigameStage.placeMeeting(dog, dog.getX(), dog.getY() + 1,
                "WinningPlatform") && dy > 0);

        if (onWinningPlatform) {
            won = true;
        }
        return (onNormalPlatform || onWinningPlatform);
    }

    /**
     * If it will collide vertically, move it so that it becomes pixel perfect.
     *
     * This only detects collision if dy is positive.
     */
    private void handleYCollisions(Transform t) {
        if (dy > 0) {
            if (minigameStage.placeMeeting(dog, dog.getX(), dog.getY() + dy, "Platform")) {
                // make it pixel perfect
                while (!minigameStage.placeMeeting(dog, dog.getX(), dog.getY() + Math.signum(dy), "Platform")) {
                    t.translateBy(0, Math.signum(dy));
                }

                dy = 0;
            }
        }
    }

    // update y position
    // this method also ends the game if the dog goes too low, or if the dog stands on the winning platform
    private void updateYPositionAndCamera(Transform t) {
        final int CAMERA_Y_THRESHOLD = 100;
        t.translateBy(0, dy);

        Transform cameraTransform = camera.getTransform();

        // Only move the camera up if the dog's y position is close to camera's y position
        double distance = t.getY() - cameraTransform.getY();
        if (distance < CAMERA_Y_THRESHOLD) {
            cameraTransform.translateBy(0, distance - CAMERA_Y_THRESHOLD);
        }

        // end the game if you fall off
        if (t.getY() > cameraTransform.getY() + 500) {
            controller.setActiveStage("Main");
            System.out.println("u lost the game lmao");
            timer.cancel();
        }

        // end the game if you win
        if (won) {
            controller.setActiveStage("Main");
            System.out.println("u won the game lmao");
            timer.cancel();
            //TODO: give you coins for winning
        }
    }
}