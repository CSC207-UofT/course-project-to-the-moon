package utility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * A helper class to load sprite frames from a folder.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class SpriteFrameLoader {
    /**
     * Given the folder containing a sprite's frames, load the frames into a BufferedImage array.
     * The frames should be named number starting from 0, and returns them in order.
     *
     * @param folderName The name of the folder containing the frames.
     * @return The sorted frames as a BufferedImage array.
     */
    public BufferedImage[] loadFramesFromFolder(String folderName) {
        File[] frameFiles = this.loadFrameFilesFromFolder(folderName);
        BufferedImage[] frames = new BufferedImage[frameFiles.length];

        //Populate frames with BufferedImages
        for (int i = 0; i < frames.length; i++) {
            try {
                frames[i] = ImageIO.read(frameFiles[i]);
            } catch (IOException e) {
                System.err.println("There was an error loading frame " + i);
            }
        }

        return frames;
    }

    /**
     * A helper method to load all the frame image files into an array.
     * @param folderName The name of the folder containing the frame files.
     */
    private File[] loadFrameFilesFromFolder(String folderName) {
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

        return frameFiles;
    }
}
