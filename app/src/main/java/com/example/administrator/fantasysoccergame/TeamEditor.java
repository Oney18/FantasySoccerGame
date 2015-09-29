package com.example.administrator.fantasysoccergame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class TeamEditor extends Activity {

    private Intent intent;
    private HashMap<String, Player> listOfPlayers;
    private ArrayList<String> listOfPlayerNames;
    private ArrayAdapter<String> playerListAdapter;
    private Team teamSelected;

    private Button addToTeam;
    private Button newPlayer;
    private RadioButton onTeamIndicator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_editor);

        intent = getIntent();

        teamSelected = (Team) intent.getSerializableExtra("Team Selected");

        listOfPlayerNames = intent.getStringArrayListExtra("Player List");
        listOfPlayers = (HashMap<String, Player>) intent.getSerializableExtra("Player Hash");

        addToTeam;
        newPlayer;
        RadioButton onTeamIndicator;
        RadioButton strikerOpt;
        RadioButton defenderOpt;
        RadioButton goalieOpt;
        EditText newPlayerName;
        EditText offIn;
        EditText defIn;
        EditText goalkIn;
        ListView playerList;
        ListView positionsList;
        TextView playerName;
        ImageView playerPic;


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
