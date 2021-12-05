import adaptors.DogGameFrameLoader;
import usecases.Bank;
import entities.Sprite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.mainhub.DogGameObject;
import usecases.mainhub.DogMover;
import usecases.SpriteFacade;

import java.awt.image.BufferedImage;

/**
 * This is the test class to test DogGameObject.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestDogGameObject {
    private Bank testBank;
    private DogGameObject testDogGameObject;
    private DogMover testDogMover;
    private SpriteFacade testSpriteFacade;
    private DogGameFrameLoader loader;


    @Before
    public void begin(){
        loader = new DogGameFrameLoader();
        BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-1/src/sprites/dog");
        testBank = new Bank();
        testSpriteFacade = new SpriteFacade(dogFrames);
        testDogGameObject = new DogGameObject(5, 4, testSpriteFacade, testBank, null);
        testDogMover = new DogMover(new Sprite(dogFrames), 10, 20);
    }

    @After
    public void endTests(){}


    @Test
    public void testIsClickedPass(){
        // test isClicked() worked.
        int x = 10;
        int y = 10;
        assert (testDogGameObject.isClicked(x, y));
    }
    @Test
    public void testIsClickedFailX(){
        // test isClicked() failed if x is out of range.
        int x = 200;
        int y = 100;
        assert (!testDogGameObject.isClicked(x, y));
    }

    @Test
    public void testIsClickedFailY(){
        // test isClicked() failed if y is out of range.
        int x = 100;
        int y = 200;
        assert (!testDogGameObject.isClicked(x, y));
    }

    @Test
    public void testIsClickedFailXY(){
        // test isClicked() failed if both x and y are out of range.
        int x = 200;
        int y = 0;
        assert (!testDogGameObject.isClicked(x, y));
    }

//    @Test
//    public void testOnClick(){
//        // test onClick() methods work
//        // set coins and exp
//        // calls onClick() method
//        testDogGameObject.onClick();
//        assert (.getCoins() == 11);
//    }
//
//    @Test
//    public void testUpdated(){
//        // test coins and exp are updated properly.
//
//        int earnedCoin = 5;
//        int earnedExp = 1;
//        //testDogGameObject.updateDog(earnedCoin, earnedExp);
//        assert ();
//    }

}