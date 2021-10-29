package adaptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import usecases.DogGameObject;
import usecases.ShopItem;

public class Economy {
    private int netCoins;
    private List<DogGameObject> dogObjs;
    private HashMap<String, ShopItem> inventory;

    public Economy () {
        this.netCoins = 0;
        this.dogObjs = new ArrayList<DogGameObject>();
        this.inventory = new HashMap<String, ShopItem>();
    }

    private void initializeInventory() {
        this.inventory.put("DogeCoinMiner", new ShopItem(5, 1, 1));
    }

    public void addDogObj(DogGameObject dog) {
        this.dogObjs.add(dog);
    }

    public int getCoins() {
        return this.netCoins;
    }

    public void updateCoins() {
        for(DogGameObject dog : dogObjs) {
            netCoins += dog.getCoinsEarnedFromLastPet();
        }
    }

}
