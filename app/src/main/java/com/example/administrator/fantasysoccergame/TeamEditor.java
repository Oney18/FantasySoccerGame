package com.example.administrator.fantasysoccergame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class TeamEditor extends Activity {

    private Intent intent;
    private HashMap<String, Player> listOfPlayers;
    private ArrayList<String> listOfPlayerNames;
    private ArrayAdapter<String> playerListAdapter;
    private ArrayAdapter<String> positionsAdapter;
    private Team teamSelected;

    private Button addToTeam;
    private Button newPlayer;
    private CheckBox onTeamIndicator;
    private RadioButton strikerOpt;
    private RadioButton defenderOpt;
    private RadioButton goalieOpt;
    private EditText newPlayerName;
    private EditText offIn;
    private EditText defIn;
    private EditText goalkIn;
    private ListView playerList;
    private ListView positionsList;
    private TextView playerName;
    private ImageView playerPic;
    private SeekBar offVal;
    private SeekBar defVal;
    private SeekBar goalkVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_editor);

        intent = getIntent();

        teamSelected = (Team) intent.getSerializableExtra("Team Selected");

        listOfPlayerNames = intent.getStringArrayListExtra("Player List");
        listOfPlayers = (HashMap<String, Player>) intent.getSerializableExtra("Player Hash");

        addToTeam = (Button) findViewById(R.id.addToTeam);
        newPlayer = (Button) findViewById(R.id.addPlayer);
        onTeamIndicator = (CheckBox) findViewById(R.id.onTeamIndicator);
        strikerOpt = (RadioButton) findViewById(R.id.strikerOpt);
        defenderOpt = (RadioButton) findViewById(R.id.defenderOpt);
        goalieOpt = (RadioButton) findViewById(R.id.goalieOpt);
        newPlayerName = (EditText) findViewById(R.id.newPlayerName);
        offIn = (EditText) findViewById(R.id.offIn);
        defIn = (EditText) findViewById(R.id.defIn);
        goalkIn = (EditText) findViewById(R.id.goalkIn);
        playerList = (ListView) findViewById(R.id.playerList);
        positionsList = (ListView) findViewById(R.id.positionsPlayedList);
        playerName = (TextView) findViewById(R.id.playerName);
        playerPic = (ImageView) findViewById(R.id.playerPic);
        offVal = (SeekBar) findViewById(R.id.offenseVal);
        defVal = (SeekBar) findViewById(R.id.defVal);
        goalkVal = (SeekBar) findViewById(R.id.goalkVal);

        playerListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                                                    listOfPlayerNames);
        playerList.setAdapter(playerListAdapter);

        playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long unneeded) {
                updateScreen(listOfPlayers.get(playerListAdapter.getItem(position)));
            }

        });

        offVal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; //disables the seek bar from being altered
            }
        });

        defVal.setOnTouchListener(new View.OnTouchListener() {
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

        onTeamIndicator.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; //disables the checkbox from being altered
            }
        });

    }

    public void goBack(View view){
        Intent alteredData = new Intent();
        alteredData.putStringArrayListExtra("New Player List", listOfPlayerNames);
        alteredData.putExtra("New Player Hash", listOfPlayers);
        alteredData.putExtra("New Team", teamSelected);
        setResult(RESULT_OK, alteredData);
        finish();
    }

    private void updateScreen(Player player) {
        playerPic.setImageResource(player.getPortraitID());
        playerName.setText(player.getName());
        offVal.setProgress(player.getOffense());
        defVal.setProgress(player.getDefense());
        goalkVal.setProgress(player.getGoalkeeping());
        if(teamSelected.checkForPlayer(player)){
            onTeamIndicator.setChecked(true);
        }
        else{
            onTeamIndicator.setChecked(false);
        }

        positionsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                player.getPositionsPlayed());
        positionsList.setAdapter(positionsAdapter);
    }

    public void addToTeam(View view){
        if(!playerName.getText().toString().isEmpty()) {
            Player player = listOfPlayers.get(playerName.getText().toString());
            teamSelected.addPlayer(player);
            teamSelected.setStats();
            this.updateScreen(player);
        }
    }

    public void makePlayer(View view){
        String name = newPlayerName.getText().toString();
        if(name.equals("Player Name") || listOfPlayerNames.contains(name) || name.isEmpty()){
            return; //false click
        }
        int off, def, goalk;
        try {
            off = Integer.valueOf(offIn.getText().toString());
            if (off > 100) {
                off = 100;
            } else if (off < 0) {
                off = 0;
            }

            def = Integer.valueOf(defIn.getText().toString());
            if (def > 100) {
                def = 100;
            } else if (def < 0) {
                def = 0;
            }

            goalk = Integer.valueOf(goalkIn.getText().toString());
            if (goalk > 100) {
                goalk = 100;
            } else if (goalk < 0) {
                goalk = 0;
            }
        }
        catch(NumberFormatException nfe)
        {
            return; //field left blank
        }

        Player player = new Player(name, off, def, goalk);
        if(strikerOpt.isChecked()){
            player.addPosPlayed(1);
        }
        if(defenderOpt.isChecked()){
            player.addPosPlayed(2);
        }
        if(goalieOpt.isChecked()){
            player.addPosPlayed(3);
        }
        listOfPlayers.put(player.getName(), player);
        listOfPlayerNames.add(player.getName());
        playerListAdapter.add(player.getName());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_editor, menu);
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
