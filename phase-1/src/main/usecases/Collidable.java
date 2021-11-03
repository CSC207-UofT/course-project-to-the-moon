package usecases;
import java.awt.Rectangle;

/**
 * To be implemented on all collidable objects.
 * @author Juntae Park
 * @since 29 October 2021
 */

public interface Collidable {
    Rectangle getHitBox();
    
}
