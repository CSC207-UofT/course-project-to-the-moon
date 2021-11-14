package usecases;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameState implements Serializable {
    private static final long serialVersionUID = -6038689297233097833L;
    private final Map<String, Object> gameState = new HashMap<>();

    public void putStages(HashMap<String, Stage> stages) {
        for (Map.Entry<String, Stage> set : stages.entrySet()){
            gameState.put(set.getKey(), set.getValue());   
        }   
    }

    public void putBankInfo(String name, int num) {
        gameState.put(name, num);
    }

    public Map<String, Object> getState() {
        return this.gameState;
    }
    
}
