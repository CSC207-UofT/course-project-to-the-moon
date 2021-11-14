import adaptors.DogGameController;
import entities.Dog;
import entities.ShopItem;
import entities.Sprite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programdrivers.DogGame;
import usecases.CoinCalculator;
import usecases.DogManager;
import usecases.DogMover;

import java.awt.*;

/**
 * This is the test class.
 * @author Aria Paydari
 * @since 13 October 2021
 */
public class ProjectTest0 {
    private DogGame testGame;
    private DogManager manager;
    private ShopItem item;
    private Dog testDog;
    private DogGameController controller;
    private CoinCalculator calculator;

    @Before
    public void begin(){
        testGame= new DogGame();
        manager = new DogManager();
        item = new ShopItem("Bone", 10);
        testDog = new Dog();
        controller = new DogGameController();
        calculator = new CoinCalculator();


    }

    @After
    public void endTests(){}

    @Test
    public void testSize(){
        assert (testGame.getFrame().getHeight() == 500
        && testGame.getFrame().getWidth() == 300);
    }

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

    @Test
    public void testShopItemCost(){
        assert item.getCost() == 10;
    }
    @Test
    public void testShopItemName(){
        assert item.getName().equals("Bone");
    }

    @Test
    public void testDogTranslate(){
        testDog.translate(23.0, 25.0);
        assert testDog.getX() == 23.0
                && testDog.getY() == 25.0;
    }
    @Test
    public void testInitialControllerList(){
        // The first clickable initialized
        // Must be a DogManager
        assert controller.getClickables().get(0) instanceof DogManager;

    }

    @Test
    public void testCalculatorCalculateCoins(){
        testDog.setExp(20);
        // 20 experience points/10 + 1
        // Will be 3 coins
        assert calculator.calculateCoins(testDog) == 3;
    }


}




