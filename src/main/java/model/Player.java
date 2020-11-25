package model;

import java.util.ArrayList;
import java.util.List;


public class Player {
    private Field field;
    private int score;
    private List<Sheep> listOfSheep = new ArrayList<Sheep>();

    public Player(int score) {
        this.field = new Field();
        this.score = score;
    }

    public Field getField() {
        return field;
    }

    public int getScore(){return score;}

    public void setScore(int score){this.score += score;}

    public List<Sheep> getListOfSheep() {
        return listOfSheep;
    }
}