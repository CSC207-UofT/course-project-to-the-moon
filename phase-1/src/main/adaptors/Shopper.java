package adaptors;
import usecases.DogGameObject;
import usecases.Shoppable;

public interface Shopper {
    public void purchaseMiner(Shoppable miner);
    public void purchaseHat(Shoppable hat, DogGameObject dogObj);
}
