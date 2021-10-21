package usecases;

import entities.Transform;

/**
 * An interface for things that control the movement of Transforms in a separate thread.
 * @author Andy Wang
 * @since 21 October 2021
 */
public interface Mover {
    public void run(Transform t);
}
