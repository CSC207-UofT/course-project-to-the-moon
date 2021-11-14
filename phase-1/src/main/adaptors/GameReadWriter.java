package adaptors;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import usecases.GameState;

public class GameReadWriter {
    private DogGameController controller;
    private final GameState gs = new GameState();
    private String filepath;

    public GameReadWriter(DogGameController controller, String filepath){
        this.controller = controller;
        this.filepath = filepath;
    }

    public enum SaveResult {
        SUCCESS, FAILURE
    }

    public void saveGame() {
        gs.putBankInfo("DCPS", this.controller.getBank().getDCPS());
        gs.putBankInfo("Coins", this.controller.getBank().getCoin());
        //gs.putStages(this.controller.getStages());
        try {
            this.saveToFile(gs);
            System.out.print("Saved!");
        } catch (IOException e) {
            System.out.println("Saving failed. " + e.getMessage());
        }
    }

    public void saveToFile(Object GameState) throws IOException {
        OutputStream file = new FileOutputStream(this.filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(GameState);
        output.close();
    }

    public GameState readFromFile() throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(this.filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        GameState saveFile = (GameState) input.readObject();
        input.close();
        System.out.println("File read!");
        return saveFile;
    }
}
