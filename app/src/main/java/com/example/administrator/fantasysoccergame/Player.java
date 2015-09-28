package com.example.administrator.fantasysoccergame;

import android.graphics.drawable.Drawable;

/**
 * Created by oney18 on 9/28/2015.
 */
public class Player {
    private String name;
    private Drawable portrait;
    private int offense;
    private int defense;
    private int goalkeeping;

    public Player(String name, int offense, int defense, int goalkeeping){
        this.name = name;
        this.offense = offense;
        this.defense = defense;
        this.goalkeeping = goalkeeping;
        this.portrait = null;
    }

    public void setPortrait(Drawable portrait){
        this.portrait = portrait;
    }

    public Drawable getPortrait(){
        return this.portrait;
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
