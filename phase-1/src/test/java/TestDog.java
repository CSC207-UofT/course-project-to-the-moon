import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entities.Dog;

import java.awt.*;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestDog {
    private Dog testDog;

    @Before
    testDog = new Dog();
    }

    @After
    public void endTests(){}

    @Test
    public void testDogTranslate(){
        testDog.translate(23.0, 25.0);
        assert testDog.getX() == 23.0
                && testDog.getY() == 25.0;
    }
}



