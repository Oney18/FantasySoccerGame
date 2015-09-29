package com.example.administrator.fantasysoccergame;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by oney18 on 9/28/2015.
 */
public class Player implements Serializable{
    private String name;
    private int portraitID;
    private int offense;
    private int defense;
    private int goalkeeping;

    public Player(String name, int offense, int defense, int goalkeeping){
        this.name = name;
        this.offense = offense;
        this.defense = defense;
        this.goalkeeping = goalkeeping;
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
}
