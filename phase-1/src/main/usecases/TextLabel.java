package usecases;

import adaptors.IGameController;

public class TextLabel extends AbstractObject {
    private String text;

    public TextLabel(int x, int y, String text, IGameController igc) {
        super(x,y);
        this.text = text;
        //igc?
    }
}
