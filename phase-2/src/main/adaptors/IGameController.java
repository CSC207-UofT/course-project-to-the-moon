package adaptors;

import java.io.IOException;

import usecases.DinoDogGameObject;
import usecases.Stage;

/**
 * An interface that represents a controller.
 * @author Andy Wang
 * @since 30 October 2021
 */
public interface IGameController {
    void addCamera(ICamera c);
    ICamera getCamera();

    void mouseClicked(int x, int y)  throws IOException,ClassNotFoundException;
    void keyPressed(int code);
    void keyReleased(int code);

    boolean getKeyPressed(int code);
    void setDinoSprite(DinoDogGameObject dino, boolean ducked);

    void addStage(String type, Stage s);
    void setActiveStage(String type);
    Stage getActiveStage();

    boolean loadFromFile();
    void createNewFile();
    GameReadWriter getReadWriter();
}
