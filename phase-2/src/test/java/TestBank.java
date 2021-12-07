import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecases.Bank;

/**
 * This is the test class.
 * @author Jimin Song
 * @since 5 December 2021
 */

public class TestBank {
    private Bank bank;

    @Before
    public void begin(){
        bank = new Bank();
    }

    @After
    public void endTests(){}

    @Test
    public void testUpdateCoin(){
        int old = bank.getCoin();
        bank.updateCoins(5);
        assert (old+5 == bank.getCoin());
    }

    @Test
    public void testIncreaseDCPs(){
        int old = bank.getDCPS();
        bank.increaseDCPS(1);
        assert (old+1 == bank.getDCPS());
    }

    @Test
    public void testPurchaseSuccess(){
        bank.updateCoins(100);
        int old = bank.getCoin();
        assert (bank.makePurchase(50));
        assert (old - 50 == bank.getCoin());
    }

    @Test
    public void testPurchaseFail(){
        bank.updateCoins(10);
        int old = bank.getCoin();
        assert (!bank.makePurchase(50));
        assert (old == bank.getCoin());
    }
}
