import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entities.ShopItem;

import java.awt.*;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 13 November 2021
 */
public class TestShop {
    private ShopItem item;

    @Before
    item = new ShopItem("Bone", 10);
    }

    @After
    public void endTests(){}

    @Test
    public void testShopItemCost(){
        assert item.getCost() == 10;
    }
    @Test
    public void testShopItemName(){
        assert item.getName().equals("Bone");
    }
}



