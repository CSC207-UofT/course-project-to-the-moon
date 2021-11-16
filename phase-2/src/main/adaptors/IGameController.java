package adaptors;

import usecases.Stage;

/**
 * An interface that represents a controller.
 * @author Andy Wang
 * @since 30 October 2021
 */
public interface IGameController {
    void addCamera(ICamera c);
    ICamera getCamera();

    void mouseClicked(int x, int y);
    void keyPressed(int code);
    void keyReleased(int code);

    boolean getKeyPressed(int code);

    void addStage(String type, Stage s);
    void setActiveStage(String type);
    Stage getActiveStage();
    Stage getStage(String name);
}
