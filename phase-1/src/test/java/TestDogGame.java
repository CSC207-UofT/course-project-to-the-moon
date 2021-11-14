import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programdrivers.DogGame;

import java.awt.*;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestDogGame {
    private DogGame testGame;

    @Before
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



