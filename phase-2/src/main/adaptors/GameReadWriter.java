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
import java.time.LocalDate;

import entities.GameState;
import usecases.*;
import usecases.mainhub.DogGameObject;


/**
 * A class to read and write data.
 * 
 * @author Juntae
 * @since 13 November 2021
 */
public class GameReadWriter {
    private String filepath;
    private Bank bank;
    private DogGameObject dogObj;

    private GameState gs = new GameState();

    /**
     * Initialize a new GameReadWrite.
     * @param filepath The filepath to save to.
     */
    public GameReadWriter(String filepath) throws IOException{
        this.filepath = filepath;
    }

    public void addBank(Bank bank){
        this.bank = bank;
    }

    public void addDog(DogGameObject dgo) {
        this.dogObj = dgo;
    }

    public GameState getGameState(){
        return this.gs;
    }

    /**
     * Updates the gamestate with given parameters/
     * @param DCPS the DCPS value to save
     * @param Coins the coin value to save
     */
    private void updateGameState(int DCPS, int Coins, LocalDate date, int exp) {
        gs.save("DCPS", DCPS);
        gs.save("Coins", Coins);
        gs.save("Date", date);
        gs.save("Exp", exp);
    }

    /**
     * Saves the game.
     * @throws IOException Throws if there's an error saving.
     */
    public void saveGame(boolean newFile) throws IOException {
        if (newFile){
            this.updateGameState(0, 0, LocalDate.now(), 0);
        }
        else {
            this.updateGameState(this.bank.getDCPS(), 
                                    this.bank.getCoin(),
                                    LocalDate.now(),
                                    this.dogObj.getDog().getExp());
        }
        this.saveToFile(gs);
        
    }

    /**
     * Saves the game to a file
     * @param GameState The GameState to save.
     * @throws IOException Throws if there's an error saving.
     */
    private void saveToFile(Object GameState) throws IOException {
        try {
            OutputStream file = new FileOutputStream(this.filepath);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);

            output.writeObject(GameState);
            output.close();
        } catch (IOException e) {
            System.out.println("Saving failed. " + e.getMessage());
        }
        
    }

    /**
     * Reads a GameState from a file.
     * @return The save file.
     * @throws IOException Throws when there's an error reading.
     * @throws ClassNotFoundException Throws when the save file class isn't found.
     */
    public void readFromFile() throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(this.filepath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        
        this.gs = (GameState) input.readObject();
        input.close();

        this.bank.updateCoins((int) gs.getState().get("Coins"));
        this.bank.setDCPS((int) gs.getState().get("DCPS"));
        this.dogObj.updateDog((int) gs.getState().get("Coins"), (int) gs.getState().get("Exp"));
    }
}
