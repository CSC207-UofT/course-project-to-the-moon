import entities.Shop;
import entities.ShopItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import programdrivers.DogGame;
import usecases.DogManager;

import java.awt.*;

/**
 * This is the test class.
 * @author Aria Paydari
 * @since 13 October 2021
 */
public class ProjectTest {
    private DogGame testGame;
    private DogManager manager;
    private ShopItem item;
    private Shop shop = new Shop();
    @Before
    public void begin(){
        testGame= new DogGame();
        manager = new DogManager();
        item = new ShopItem("Bone", 10);
        shop.addItem(item);

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
    public void testShopItemcost(){
        assert item.getCost() == 10;
    }
    @Test
    public void testShopItemName(){
        assert item.getName().equals("Bone");
    }
    // Potential test
//    @Test
//    public void testShopAddItem(){
//        assert shop.inventory
//    }
//

}