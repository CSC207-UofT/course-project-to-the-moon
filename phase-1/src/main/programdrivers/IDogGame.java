package programdrivers;

import usecases.Stage;

public interface IDogGame {
    default Stage createShopStage() {
        Stage shopStage = new Stage("Shop");
        return shopStage;
    }
}
