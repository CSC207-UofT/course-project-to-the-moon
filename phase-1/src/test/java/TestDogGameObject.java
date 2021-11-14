import entities.Dog;
import entities.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programdrivers.DogGame;
import usecases.DogGameObject;
import usecases.DogMover;
import usescases.SpriteFacade;

import java.awt.*;

/**
 * This is the test class to test DogGameObject.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestDogGameObject {
    private DogGame testGame;
    private Dog testDog;
    private Bank testBank;
    private DogGameObject testDogGameObject;
    private DogMover testDogMover;
    private SpriteFascade testSpriteFacade;



    @Before
    public void begin(){
        testGame= new DogGame();
        testDog = new Dog();
        testBank = new Bank();
        testDogGameObject = new DogGameObject(5, 4, testSpriteFacade, testBank);
        testDogMover = new testDogMover;
    }

    @After
    public void endTests(){}


    @Test
    public void testIsClicked(){
        int x = 100;
        int y = 100;
        assert (testDogGameObject.isClicked(x, y));
    }
    @Test
    public void testIsClickedFailX(){
        int x = 200;
        int y = 100;
        assert (!testDogGameObject.isClicked(x, y));
    }

    @Test
    public void testIsClickedFailY(){
        int x = 100;
        int y = 200;
        assert (!testDogGameObject.isClicked(x, y));
    }

    @Test
    public void testIsClickedFailXY(){
        int x = 200;
        int y = 0;
        assert (!testDogGameObject.isClicked(x, y));
    }

    @Test
    public void testOnClick(){
        testDog.setCoins(10);
        testDog.setExp(20);
        testDogGameObject.onClick();
        assert (testDog.getCoins() == 11);
    }

    @Test
    public void testUpdated(){
        // test coins and exp are updated properly.
        testDog.setCoins(10);
        testDog.setExp(20);
        int earnedCoin = 5;
        int earnedExp = 1;
        testDogGameObject.updateDog(earnedCoin, earnedExp);
        assert (testDog.getCoins() == 15 && testDog.getExp() == 21);
    }

}