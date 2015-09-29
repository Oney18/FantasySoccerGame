package com.example.administrator.fantasysoccergame;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by oney18 on 9/28/2015.
 */
public class Team implements Serializable {
    private String name;
    private ArrayList<Player> players;
    private int offense;
    private int defense;
    private int goalkeeping;
    private int average;
    private int logoID; //ID of the drawable

    public Team(String name){
        this.name = name;
        this.players = new ArrayList<Player>();

        //stats determined by players; 0 to begin as no players
        this.offense = 0;
        this.defense = 0;
        this.goalkeeping = 0;
        this.average = 0;
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

    public int getAverage(){
        return this.average;
    }

    public int getLogoID(){
        return this.logoID;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void setStats(){
        int off = 0;
        int def = 0;
        int goalKeep = 0;

        for(int i = 0; i < players.size(); i++)
        {
            off += players.get(i).getOffense();
            def += players.get(i).getDefense();
            goalKeep += players.get(i).getGoalkeeping();
        }

        this.offense = off / players.size();
        this.defense = def / players.size();
        this.goalkeeping = goalKeep / players.size();
        this.average = (this.offense + this.defense + this.average) / 3;
    }

    public void setLogo(int logoID){
        this.logoID = logoID;
    }

    public void removePlayer(Player player){
        players.remove((Player) player);
    }
}
