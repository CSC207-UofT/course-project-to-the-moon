import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.DogManager;

import java.awt.*;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestManager {
    private DogManager manager;

    @Before
    manager = new DogManager();
    }

    @After
    public void endTests(){}

    @Test
    public void testStartingPosition(){
        assert(manager.getX() == 0
                && manager.getY() == 0);
    }

    @Test
    public void testClickedPosition(){
        // Since the sprite's initial position is (0,0)
        // (10,10) will be on the dog.
        assert manager.clicked(10,10);
    }
    @Test
    public void testClickedPositionFails(){
        // Since the sprite's initial position is (0,0)
        // And the size of the sprite is 200x200
        // (250, 250) will not be on the dog
        // and clicked should fail.
        assert !manager.clicked(250,250);
    }
}



