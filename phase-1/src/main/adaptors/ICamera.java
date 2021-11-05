package adaptors;
import java.util.List;

import usecases.Collidable;

public interface ICamera {
    public List<Collidable> getCollidablesInBounds();
}
