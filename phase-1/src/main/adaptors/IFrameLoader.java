package adaptors;

import java.awt.image.BufferedImage;

/**
 * An interface for classes that load frames from a folder.
 * @author Andy Wang
 * @since 23 October 2021
 */
public interface IFrameLoader {
    public BufferedImage[] loadFramesFromFolder(String folderName);
}
