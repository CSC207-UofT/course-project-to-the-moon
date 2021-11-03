package usecases;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents all text labels and collidable game objects on the screen.
 * @author Juntae Park
 * @since 30 October 2021
*/

public class Stage {
    List<GameObject> gameObjects;
    List<TextLabel> textLabels;

    public Stage() {
        this.gameObjects = new ArrayList<GameObject>();
        this.textLabels = new ArrayList<TextLabel>();
    }

    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public List<TextLabel> getTextLabels() {
        return this.textLabels;
    }

    public void addGameObject(GameObject go) {
        this.gameObjects.add(go);
    }

    public void addTextLabel(TextLabel tl) {
        this.textLabels.add(tl);
    }

}
