package usecases;

import adaptors.IGameController;

import java.awt.*;

/**
 * A class that represents the home button to return to the main stage.
 * @author Fatimeh Hassan
 * @since 12 November 2021
 */
public class HomeButton extends TextLabel implements Clickable{
    private IGameController controller;

    public HomeButton(Rectangle r, String text, String tag, IGameController controller) {
        super(r, text, tag);
        this.controller = controller;

        this.setStrokeWidth(2);
        this.setStrokeColor(Color.WHITE);
    }

    @Override
    public boolean isClicked(int mouseX, int mouseY) {
        double x = this.getX();
        double y = this.getY();
        int width = (int) super.rectangle.getWidth();
        int height = (int) super.rectangle.getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    @Override
    public void onClick() {
        controller.setActiveStage("Main");
    }
}