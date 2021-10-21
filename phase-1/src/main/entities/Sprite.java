package entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class represents a sprite to be drawn to a window.
 * @author Aria, edited by Andy Wang
 * @since 12 October 2021
 */
public class Sprite {
    private BufferedImage[] frames; // the frames of the sprite
    private boolean isFlipped = false; // if the sprite is currently flipped (facing right)
    private int currentFrame = 0; // index of the current frame

    /**
     * Initialize a sprite given the name of a single image. This should be used for non-animated sprites.
     * @param fileName The name of the image file.
     */
    public Sprite(String fileName) {
        try {
            File frameFile = new File(fileName);
            frames = new BufferedImage[1];
            frames[0] = ImageIO.read(frameFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes a sprite given a folder of sprites, and the frames per second.
     * @param folderName The name of the folder containing the frames.
     * @param fps The animation speed in frames per second.
     */
    public Sprite(String folderName, int fps) {
        this.loadFrames(folderName);

        Timer animator = new Timer();
        TimerTask frameUpdateTask = new TimerTask() {
            @Override
            public void run() {
                updateFrame();
            }
        };
        animator.scheduleAtFixedRate(frameUpdateTask, 0, 1000 / fps);
    }

    /**
     * This method flips the sprite horizontally.
     */
    public void flip() {
        for (int i = 0; i < frames.length; i++) {
            BufferedImage orig = this.frames[i];
            BufferedImage flipped = new BufferedImage(orig.getWidth(), orig.getHeight(), BufferedImage.TYPE_INT_ARGB);

            //Flip each image by writing pixels to a new image
            for (int x = 0; x < orig.getWidth(); x++){
                for (int y = 0; y < orig.getHeight(); y++){
                    flipped.setRGB(orig.getWidth() - 1 - x, y, orig.getRGB(x, y));
                }
            } this.frames[i] = flipped;
        } this.isFlipped = !this.isFlipped;
    }

    /**
     * The isFlipped method returns whether the sprite is facing right.
     * @return Whether the sprite is facing right.
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * The currentFrame method returns the current frame as a BufferedImage.
     * @return The current frame as a BufferedImage
     */
    public BufferedImage getCurrentFrame() {
        return this.frames[this.currentFrame];
    }

    /**
     * The getWidth method returns the width of the current frame in pixels.
     * @return The width of the current frame in pixels.
     */
    public int getWidth() {
        return this.frames[0].getWidth(); // all sprites should have the same dimensions
    }

    /**
     * The getHeight method returns the height of the current frame in pixels.
     * @return The height of the current frame in pixels.
     */
    public int getHeight() {
        return this.frames[0].getHeight(); // all sprites should have the same dimensions
    }

    /**
     * A helper method to load all the frames into an array.
     * @param folderName The name of the folder containing the frames.
     */
    private void loadFrames(String folderName) {
        File folder = new File(folderName);
        System.out.println(folder.getAbsolutePath());
        File[] frameFiles = folder.listFiles();
        assert(frameFiles != null);

        // Sort the frame files by name in ascending order
        Arrays.sort(frameFiles, (f1, f2) -> {
            // originally was going to use an anonymous comparator, but IntelliJ said I could use a lambda instead
            // so here we are lol
            // f1 and f2 are files to be compared
            String n1 = f1.getName();
            String n2 = f2.getName();

            //Remove the ".png" at the end
            n1 = n1.substring(0, n1.length() - 4);
            n2 = n2.substring(0, n2.length() - 4);

            return Integer.compare(Integer.parseInt(n1), Integer.parseInt(n2));
        });

        this.frames = new BufferedImage[frameFiles.length];

        //Populate frames with BufferedImages
        for (int i = 0; i < frames.length; i++) {
            try {
                frames[i] = ImageIO.read(frameFiles[i]);
            } catch (IOException e) {
                System.out.println("There was an error loading frame " + i);
            }
        }
    }

    /**
     * A helper method to update the frame to the next one in the animation.
     */
    private void updateFrame() {
        this.currentFrame++;
        this.currentFrame %= frames.length; // if currentFrame goes out of bounds it becomes zero again
    }
}