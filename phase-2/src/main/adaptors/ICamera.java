package adaptors;

import entities.Transform;
import usecases.Drawable;
import usecases.Stage;
import usecases.TextLabel;

import java.util.List;

/**
 * An interface that represents a camera.
 * @author Praket Kanaujia
 * @since 10 November 2021
 */

public interface ICamera {
    List<Drawable> getDrawableObjectsInBounds();
    List<TextLabel> getTextLabels();

    void setStage(Stage stage);

    Transform getTransform();
}
