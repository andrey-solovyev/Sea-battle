package company.AllLogic;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import company.Arms.Arms;
import company.Arms.Common;
import company.Arms.Ship;
import company.Field.Cell;

import java.util.ArrayList;
import java.util.Arrays;

@JsonAutoDetect
public class SaveGameJson {
private Cell[][] playerOne=new Cell[10][10];
    private Cell[][] playerTwo=new Cell[10][10];
    private Arms playerOneArms=new Arms();
    private Arms playerTwoArms=new Arms();
    private boolean haveUser=false;
    private boolean lastShotOne=false;
    private String one=new String("Robot");
    private String two=new String("Robot");
/*
    public Common[] getPlayerOneArms() {
        return playerOneArms;
    }

    public void setPlayerOneArms(Common[] playerOneArms) {
        this.playerOneArms = playerOneArms;
    }

    public Common[] getPlayerTwoArms() {
        return playerTwoArms;
    }

    public void setPlayerTwoArms(Common[] playerTwoArms) {
        this.playerTwoArms = playerTwoArms;
    }*/

    public SaveGameJson() {
    }

    public Arms getPlayerOneArms() {
        return playerOneArms;
    }

    public void setPlayerOneArms(Arms playerOneArms) {
        this.playerOneArms = playerOneArms;
    }

    public Arms getPlayerTwoArms() {
        return playerTwoArms;
    }

    public void setPlayerTwoArms(Arms playerTwoArms) {
        this.playerTwoArms = playerTwoArms;
    }

    public boolean isLastShotOne() {
        return lastShotOne;
    }

    public void setLastShotOne(boolean lastShotOne) {
        this.lastShotOne = lastShotOne;
    }

    public boolean isHaveUser() {
        return haveUser;
    }

    public void setHaveUser(boolean haveUser) {
        this.haveUser = haveUser;
    }

    public Cell[][] getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Cell[][] playerOne) {
        this.playerOne = playerOne;
    }

    public Cell[][] getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Cell[][] playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }


}
