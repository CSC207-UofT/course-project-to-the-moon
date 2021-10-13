package main.java.entities;

import main.java.usecases.DogMover;

import java.awt.*;

public class Dog {
    private int coins;
    private int level;
    private double exp;
    // dog position
    public int x;
    public int y;
    public DogMover dm;
    // dog size
    public int d_width;
    public int d_height;

    public Dog() {
        this.coins = 0;
        this.level = 0;
        this.exp = 0.0;
        this.x = 0;
        this.y = 0;
        this.d_width = 70;
        this.d_height = 100;
    }

    public int getCoins() {
        return this.coins;
    }
    public int getLevel() {
        return this.level;
    }

    public void setCoins(int coin) {
        this.coins += coin;
    }
    public void setExp(double exp) {
        this.exp += exp;
    }

    // Jimin Song added this part
    // create moveDog method
    public void moveDog(int delta_x, int delta_y){
        this.x += delta_x;
        this.y += delta_y;
    }


    public void drawDog(Graphics g){
        //TODO: you should change this code to draw a dog.
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.d_width, this.d_height);
    }


}
