import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.CoinCalculator;

import java.awt.*;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestCalculator {
    private CoinCalculator calculator;

    @Before
    calculator = new CoinCalculator();
    }

    @After
    public void endTests(){}

    @Test
    public void testCalculatorCalculateCoins(){
        testDog.setExp(20);
        // 20 experience points/10 + 1
        // Will be 3 coins
        assert calculator.calculateCoins(testDog) == 3;
    }
}



