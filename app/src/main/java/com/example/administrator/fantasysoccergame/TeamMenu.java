package com.example.administrator.fantasysoccergame;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class TeamMenu extends Activity {

    private TextView teamName;
    private ImageView teamPic;
    private SeekBar offenseVal;
    private SeekBar defenseVal;
    private SeekBar goalkVal;
    private SeekBar avgVal;
    private Button alterTeam;
    private Button addTeam;
    private Button playButton;
    private ListView teamList;
    private EditText newTeam;

    private HashMap<String, Team> listOfTeams;
    private ArrayList<String> listOfTeamNames;
    private ArrayAdapter<String> listAdapter;

    private HashMap<String, Player> listOfPlayers;
    private ArrayList<String> listOfPlayerNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_menu);



        teamName = (TextView) findViewById(R.id.teamName);
        teamPic = (ImageView) findViewById(R.id.teamPic);
        offenseVal = (SeekBar) findViewById(R.id.offenseVal);
        defenseVal = (SeekBar) findViewById(R.id.defenseVal);
        goalkVal = (SeekBar) findViewById(R.id.goalkVal);
        avgVal = (SeekBar) findViewById(R.id.avgVal);
        alterTeam = (Button) findViewById(R.id.alterTeamButton);
        addTeam = (Button) findViewById(R.id.addTeam);
        playButton = (Button) findViewById(R.id.playButton);
        teamList = (ListView) findViewById(R.id.teamList);
        newTeam = (EditText) findViewById(R.id.newTeamName);

        listOfTeams = new HashMap<String, Team>();
        listOfTeamNames = new ArrayList<String>();
        listOfPlayers = new HashMap<String, Player>();
        listOfPlayerNames = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listOfPlayerNames);
        teamList.setAdapter(listAdapter);


        this.initializePlayers();
        this.initializeTeams();




        teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
               updateScreen(listAdapter.getItem(position));
            }

        });

        offenseVal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; //disables the seek bar from being altered
            }
        });

        defenseVal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; //disables the seek bar from being altered
            }
        });

        goalkVal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; //disables the seek bar from being altered
            }
        });

        avgVal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; //disables the seek bar from being altered
            }
        });



    }

    private void updateScreen(String key) {
        Player temp = listOfPlayers.get(key);
        offenseVal.setProgress(temp.getOffense());
        defenseVal.setProgress(temp.getDefense());
        goalkVal.setProgress(temp.getGoalkeeping());
        teamName.setText(temp.getName());
        teamPic.setImageResource(temp.getPortraitID());
    }

    private void initializeTeams() {

        Team team1 = new Team("Bottles");
        listOfTeams.put(team1.getName(), team1);
        //listAdapter.add(team1.getName());

        Team team2 = new Team("Jars");
        listOfTeams.put(team2.getName(), team2);
        //listAdapter.add(team2.getName());

        Team team3 = new Team("Cans");
        listOfTeams.put(team3.getName(), team3);
        //listAdapter.add(team3.getName());
    }

    private void initializePlayers() {
        Player A1 = new Player("A1", 72, 36, 44);
        A1.setPortrait(R.drawable.a1);
        listOfPlayers.put(A1.getName(), A1);
        listAdapter.add(A1.getName());

        Player boullion = new Player("Better Than Bouillion", 56, 60, 22);
        boullion.setPortrait(R.drawable.chicken_stock);
        listOfPlayers.put(boullion.getName(), boullion);
        listAdapter.add(boullion.getName());

        Player cocoCaramel = new Player("Coco Caramel", 76, 44, 60);
        cocoCaramel.setPortrait(R.drawable.coco_caramel);
        listOfPlayers.put(cocoCaramel.getName(), cocoCaramel);
        listAdapter.add(cocoCaramel.getName());

        Player tsao = new Player("General Tsao", 80, 13, 40);
        tsao.setPortrait(R.drawable.general_tsao);
        listOfPlayers.put(tsao.getName(), tsao);
        listAdapter.add(tsao.getName());

        Player bear = new Player("Honey Bear", 64, 70, 15);
        bear.setPortrait(R.drawable.honey_bear);
        listOfPlayers.put(bear.getName(), bear);

        Player nama = new Player("Nama Shoyu", 26, 57, 80);
        nama.setPortrait(R.drawable.nama_shoyu);
        listOfPlayers.put(nama.getName(), nama);

        Player nutmeg = new Player("Nutmeg", 88, 14, 25);
        nutmeg.setPortrait(R.drawable.nutmeg);
        listOfPlayers.put(nutmeg.getName(), nutmeg);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
