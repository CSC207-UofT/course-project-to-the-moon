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

/**
 * A class to read and write data. In phase 2 we'll separate this into two classes
 * to obey single responsibility.
 *
 *
 *
 *
 * THIS WILL BE REWRITTEN LATER
 * @author Juntae
 * @since 13 November 2021
 */
public class GameReadWriter {
    private final DogGameController controller;
    private final GameState gs = new GameState();
    private final String filepath;

    /**
     * Initialize a new GameReadWrite.
     * @param controller The controller to save.
     * @param filepath The filepath to save to.
     */
    public GameReadWriter(DogGameController controller, String filepath){
        this.controller = controller;
        this.filepath = filepath;
    }

    /**
     * Saves the game.
     */
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

    /**
     * Saves the game to a file
     * @param GameState The GameState to save.
     * @throws IOException Throws if there's an error saving.
     */
    private void saveToFile(Object GameState) throws IOException {
        OutputStream file = new FileOutputStream(this.filepath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        output.writeObject(GameState);
        output.close();
    }

    /**
     * Reads a GameState from a file.
     * @return The save file.
     * @throws IOException Throws when there's an error reading.
     * @throws ClassNotFoundException Throws when the save file class isn't found.
     */
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
