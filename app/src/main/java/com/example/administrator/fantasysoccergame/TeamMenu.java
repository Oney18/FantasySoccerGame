package com.example.administrator.fantasysoccergame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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

        listOfTeams = new HashMap<String, Team>();
        listOfTeamNames = new ArrayList<String>();
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listOfTeamNames);
        teamList.setAdapter(listAdapter);
        this.initializeTeams();

    }

    private void initializeTeams() {
        Team team1 = new Team("Bottles");
        listOfTeams.put(team1.getName(),team1);
        listAdapter.add(team1.getName());

        Team team2 = new Team("Jars");
        listOfTeams.put(team2.getName(),team2);
        listAdapter.add(team2.getName());

        Team team3 = new Team("Cans");
        listOfTeams.put(team3.getName(),team3);
        listAdapter.add(team3.getName());
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
