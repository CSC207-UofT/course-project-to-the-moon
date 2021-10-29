package adaptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usecases.DogGameObject;
import usecases.ShopObject;
import usecases.Shoppable;

/**
 * Responsible for managing the economy system of the game
 * @author Juntae Park
 * @since 29 October 2021
 */

public class Economy implements Shopper{
    public static int netCoin;
    public static int passiveDPCS;
    private List<DogGameObject> dogObjs;
    private HashMap<String, ShopObject> ShopMenu;

    public Economy () {
        netCoin = 0;
        passiveDPCS = 0;
        this.dogObjs = new ArrayList<DogGameObject>();
    }

    @Override
    public void purchaseMiner(Shoppable item) {
        netCoin -= item.getCost();
        passiveDPCS += ((ShopObject) item).getDCPS();

        item.increasePrice(1);
    }

    @Override
    public void purchaseHat(Shoppable hat, DogGameObject dogObj) {
        netCoin -= hat.getCost();
        // todo: associate hat with dogObj

        hat.increasePrice(1);
    }

    public void addDogObj(DogGameObject dog) {
        this.dogObjs.add(dog);
    }

    public void updateCoins() {
        for(DogGameObject dog : dogObjs) {
            netCoin += dog.getCoinsEarnedFromLastPet();
        }
    }

}
