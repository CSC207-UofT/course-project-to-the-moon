package main.java.entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Runnable;

public class Sprite implements Runnable{
    private final int FPS = 1;
    private int frameCounter;
    private ArrayList<BufferedImage> imageList;

    public Sprite() throws IOException {
        this.frameCounter =0;
        this.addImage("main/dog1.png");
    }

    /**
     *
     * @return returns the game's frames per second.
     */
    public int getFPS(){
        return FPS;
    }
    public void addImage(String directory) throws IOException{
        BufferedImage image = ImageIO.read(new File(directory));
        imageList.add(image);
    }

    @Override
    public void run() {
        frameCounter += FPS;

    }
}
