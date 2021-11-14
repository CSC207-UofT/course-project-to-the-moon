import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programdrivers.DogGame;
import usescases.TextButton;
import adaptors.IGameController;

import java.awt.*;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestTextButton {
    private DogGame testGame;
    private DogManager manager;
    private Dog testDog;
    private DogGameController controller;
    private TextButtoon testTextButton;
    private Rectangle testRectangle;
    private IGameController testIController;

    @Before
    public void begin(){
        testRectangle = new Rectangle(50, 30, 20, 10);
        testTextButton = new TextButton(testRectangle, "text", "tag", testIController);
    }

    @After
    public void endTests(){}

    @Test
    public void testIsClickedPass(){
        int mouseX = 60;
        int mouseY = 35;
        assert (testTextButton.isClicked(mouseX, mouseY));
   }

    @Test
    public void testIsClickedFailX(){
        int mouseX = 100;
        int mouseY = 35;
        assert (!testTextButton.isClicked(mouseX, mouseY));
    }

    @Test
    public void testIsClickedFailY(){
        int mouseX = 55;
        int mouseY = 5;
        assert (!testTextButton.isClicked(mouseX, mouseY));
    }

    @Test
    public void testIsClickedFailXY(){
        int mouseX = 100;
        int mouseY = 200;
        assert (!testTextButton.isClicked(mouseX, mouseY));
    }
}



