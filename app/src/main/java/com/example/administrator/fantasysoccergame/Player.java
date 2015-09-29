package com.example.administrator.fantasysoccergame;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by oney18 on 9/28/2015.
 */
public class Player implements Serializable{
    private String name;
    private int portraitID;
    private int offense;
    private int defense;
    private int goalkeeping;
    private ArrayList<String> positionsPlayed;

    /* -- ctor -- */
    public Player(String name, int offense, int defense, int goalkeeping){
        this.name = name;
        this.offense = offense;
        this.defense = defense;
        this.goalkeeping = goalkeeping;
        this.positionsPlayed = new ArrayList<String>();
    }

    public void setPortrait(int portraitID){
        this.portraitID = portraitID;
    }

    public int getPortraitID(){
        return this.portraitID;
    }

    public String getName(){
        return this.name;
    }

    public int getOffense(){
        return this.offense;
    }

    public int getDefense(){
        return this.defense;
    }

    public int getGoalkeeping(){
        return this.goalkeeping;
    }

    /* -- Adds positions played by player -- */
    public void addPosPlayed(int posID) {
        switch (posID) {
            case 1:
                if(!positionsPlayed.contains("Striker")) {
                    positionsPlayed.add("Striker");
                }
                break;
            case 2:
                if(!positionsPlayed.contains("Defender")) {
                    positionsPlayed.add("Defender");
                }
                break;
            case 3:
                if(!positionsPlayed.contains("Goaltender")) {
                    positionsPlayed.add("Goaltender");
                }
                break;
        }
    }

    /* -- Checks if this player is eual to passed player -- */
    public boolean isEqual(Player player){
       if(this.name.equals(player.getName())){
           return true;
       }
        return false;
    }

    public ArrayList<String> getPositionsPlayed(){
        return this.positionsPlayed;
    }

}
