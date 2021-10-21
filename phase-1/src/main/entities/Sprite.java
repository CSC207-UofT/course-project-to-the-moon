package entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Arrays;

/**
 * This class represents a sprite. It stores its frames as a BufferedImage array.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class Sprite implements Serializable {
    private BufferedImage[] frames; // the frames of the sprite
    private BufferedImage currentFrame; // the current frame to be drawn
    private boolean flipped = false; // if the sprite is currently flipped (facing right)
    private int currentFrameIndex = 0; // index of the current frame
    // each frame should have the same dimensions
    private int width = 0;
    private int height = 0;

    /**
     * Initializes a sprite given an array of its frames as BufferedImages.
     * @param frames An array of BufferedImages.
     */
    public Sprite(BufferedImage[] frames) {
        this.frames = frames;
        this.currentFrame = frames[0];
        this.width = frames[0].getWidth();
        this.height = frames[0].getHeight();
    }

    /**
     * Increments the sprite's current frame to the next frame in the sequence.
     */
    public void incrementFrame() {
        this.currentFrameIndex++;
        this.currentFrameIndex %= this.frames.length; // if currentFrame goes out of bounds it becomes zero again
        this.currentFrame = this.frames[this.currentFrameIndex];
    }

    /**
     * Flips the sprite horizontally.
     */
    public void flip() {
        for (int i = 0; i < this.frames.length; i++) {
            BufferedImage orig = this.frames[i];
            BufferedImage flipped = new BufferedImage(orig.getWidth(), orig.getHeight(), BufferedImage.TYPE_INT_ARGB);

            //Flip each image by writing pixels to a new image
            for (int x = 0; x < orig.getWidth(); x++){
                for (int y = 0; y < orig.getHeight(); y++){
                    flipped.setRGB(orig.getWidth() - 1 - x, y, orig.getRGB(x, y));
                }
            } this.frames[i] = flipped;
        } this.flipped = !this.flipped;
    }

    /**
     * Returns whether the sprite is facing right.
     * @return Whether the sprite is facing right.
     */
    public boolean flipped() {
        return this.flipped;
    }

    /**
     * Returns the current frame as a BufferedImage.
     * @return The current frame as a BufferedImage
     */
    public BufferedImage getCurrentFrame() {
        return this.currentFrame;
    }

    /**
     * Returns the width of the current frame in pixels.
     * @return The width of the current frame in pixels.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the current frame in pixels.
     * @return The height of the current frame in pixels.
     */
    public int getHeight() {
        return this.height;
    }

//    /**
//     * A helper method to load all the frames into an array.
//     * @param folderName The name of the folder containing the frames.
//     */
//    private void loadFrames(String folderName) {
//        File folder = new File(folderName);
//        System.out.println(folder.getAbsolutePath());
//        File[] frameFiles = folder.listFiles();
//        assert(frameFiles != null);
//
//        // Sort the frame files by name in ascending order
//        Arrays.sort(frameFiles, (f1, f2) -> {
//            // originally was going to use an anonymous comparator, but IntelliJ said I could use a lambda instead
//            // so here we are lol
//            // f1 and f2 are files to be compared
//            String n1 = f1.getName();
//            String n2 = f2.getName();
//
//            //Remove the ".png" at the end
//            n1 = n1.substring(0, n1.length() - 4);
//            n2 = n2.substring(0, n2.length() - 4);
//
//            return Integer.compare(Integer.parseInt(n1), Integer.parseInt(n2));
//        });
//
//        this.frames = new BufferedImage[frameFiles.length];
//
//        //Populate frames with BufferedImages
//        for (int i = 0; i < frames.length; i++) {
//            try {
//                frames[i] = ImageIO.read(frameFiles[i]);
//            } catch (IOException e) {
//                System.out.println("There was an error loading frame " + i);
//            }
//        }
//    }
}