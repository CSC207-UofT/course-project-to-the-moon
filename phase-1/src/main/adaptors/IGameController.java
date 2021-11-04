package adaptors;

import entities.Bank;
import usecases.Stage;

import java.awt.event.MouseEvent;

/**
 * An interface that represents a controller.
 * @author Andy Wang
 * @since 30 October 2021
 */
public interface IGameController {
    public void mouseClicked(MouseEvent e);
    boolean getKeyPressed(char c);

    void addStage(String type, Stage s);
    void setActiveStage(String type);
    Stage getActiveStage();

    Bank getBank();
    void setBank(Bank b);
}
