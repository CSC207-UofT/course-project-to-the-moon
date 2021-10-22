package utility;

import usecases.GameObject;

import java.awt.image.BufferedImage;

/**
 * A helper factory that makes creating GameObjects easier.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class GameObjectFactory {
    private final SpriteFrameLoader loader = new SpriteFrameLoader();

    /**
     * Creates the corresponding GameObject given the name.
     * @param The name of the object.
     * @return The corresponding name of the object.
     */
    public GameObject create(String name) {
        if (name.equals("Dog")) {
            // Build a DogGameObject
//            DogGameObject dog = new DogGameObject(0, 0);
//            BufferedImage[] dogFrames = loader.loadFramesFromFolder("phase-1/sprites/dog");
//            dog.addSpriteFromFrames(dogFrames, 1);
//            dog.addMover(new DogMover());
//            return dog
            //TODO: make DogGameObject and DogMover, uncomment the above
        }
    }
}
