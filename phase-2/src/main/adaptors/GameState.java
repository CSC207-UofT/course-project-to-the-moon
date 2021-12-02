package adaptors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameState implements Serializable {
    private static final long serialVersionUID = -6038689297233097833L;
    private final Map<String, Object> gameState = new HashMap<>();

    public void save(String name, Object obj) {
        gameState.put(name, obj);
    }

    public Map<String, Object> getState() {
        return this.gameState;
    }
    
}
