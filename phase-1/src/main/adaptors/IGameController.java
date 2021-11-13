package adaptors;

import usecases.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * An interface that represents a controller.
 * @author Andy Wang
 * @since 30 October 2021
 */
public interface IGameController {
    void addCamera(ICamera c);
    ICamera getCamera();

    void mouseClicked(MouseEvent e);
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);

    boolean getKeyPressed(int c);

    void addStage(String type, Stage s);
    void setActiveStage(String type);
    Stage getActiveStage();
    Stage getStage(String name);
}
