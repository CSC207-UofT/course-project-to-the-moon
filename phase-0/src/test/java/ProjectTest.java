import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programdrivers.DogGame;

import java.awt.*;

/**
 * This is the test class.
 * @author Aria
 * @since 13 October 2021
 */
public class ProjectTest {
    private DogGame testGame;
    @Before
    public void begin(){
        testGame= new DogGame();

    }

    @After
    public void endTests(){}

    @Test
    public void testSize(){
        assert (testGame.getFrame().getHeight() == 500
        && testGame.getFrame().getWidth() == 300);
    }

}