package adaptors;

import usecases.Bank;
import usecases.StageType;

import java.awt.event.MouseEvent;

/**
 * An interface that represents a controller.
 * @author Andy Wang
 * @since 30 October 2021
 */
public interface IGameController {
    public void mouseClicked(MouseEvent e);
    boolean getKeyPressed(char c);

    void addStage(Stage s);
    void setActiveStage(StageType type);
    Stage getActiveStage();

    Bank getBank();
    void setBank(Bank b);
}
