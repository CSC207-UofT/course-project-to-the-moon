import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import adaptors.Economy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import usecases.Bank;
import usecases.mainhub.MinerButton;

/**
 * This is the test class for class Economy.
 * @author Jimin Song
 * @since 4 December 2021
 */

public class TestEconomy {
    private Economy economy;
    private MinerButton item;
    private Bank bank;
    private ArrayList<MinerButton> items;

    @Before
    public void beginTests(){
        economy = new Economy();
        bank = new Bank();
        item = new MinerButton(new Rectangle(90, 30, 130, 100),
                "Buy Computer", "Computer", bank, 50, 10, 10);
    }

    @After
    public void endTests(){};

    @Test
    public void testAddItem(){
        economy.addItem(item);
        items = economy.getItems();
        assert (items.get(0) instanceof MinerButton);
    }

//    @Test
//    public void testUpdateItem(){
//        int oldCost = item.getCost();
//        economy.addItem(item);
//        economy.run();
//        System.out.println(oldCost);
//        assert (oldCost != item.getCost());
//    }
}
