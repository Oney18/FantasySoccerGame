package com.example.administrator.fantasysoccergame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PlayGame extends Activity {

    private Spinner goalieASpin;
    private Spinner goalieBSpin;
    private Spinner strikerA1Spin;
    private Spinner strikerA2Spin;
    private Spinner strikerB1Spin;
    private Spinner strikerB2Spin;
    private Spinner defA1Spin;
    private Spinner defA2Spin;
    private Spinner defB1Spin;
    private Spinner defB2Spin;
    private Spinner teamASpin;
    private Spinner teamBSpin;
    private ImageView goalieAPic;
    private ImageView goalieBPic;
    private ImageView strikerA1Pic;
    private ImageView strikerA2Pic;
    private ImageView strikerB1Pic;
    private ImageView strikerB2Pic;
    private ImageView defA1Pic;
    private ImageView defA2Pic;
    private ImageView defB1Pic;
    private ImageView defB2Pic;
    private ImageView teamAPic;
    private ImageView teamBPic;
    private Team teamA;
    private Team teamB;

    //Init Team data from main
    private Intent passedData;
    private HashMap<String, Team> listOfTeams;
    private ArrayList<String> listOfTeamNames;
    private ArrayAdapter<String> teamListAdapter;

    //Init components used in selecting players
    private HashMap<String, Player> listOfPlayers;
    private ArrayList<String> teamARoster;
    private ArrayList<String> teamBRoster;
    private ArrayAdapter<String> rosterAAdapter;
    private ArrayAdapter<String> rosterBAdapter;

    //Init the players
    private Player goalieA;
    private Player goalieB;
    private Player strikerA1;
    private Player strikerA2;
    private Player strikerB1;
    private Player strikerB2;
    private Player defA1;
    private Player defA2;
    private Player defB1;
    private Player defB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //Assign the views
        goalieASpin = (Spinner) findViewById(R.id.goalieASpin);
        goalieBSpin = (Spinner) findViewById(R.id.goalieBSpin);
        strikerA1Spin = (Spinner) findViewById(R.id.strikerA1Spin);
        strikerA2Spin = (Spinner) findViewById(R.id.strikerA2Spin);
        strikerB1Spin = (Spinner) findViewById(R.id.strikerB1Spin);
        strikerB2Spin = (Spinner) findViewById(R.id.strikerB2Spin);
        defA1Spin = (Spinner) findViewById(R.id.defA1Spin);
        defA2Spin = (Spinner) findViewById(R.id.defA2Spin);
        defB1Spin = (Spinner) findViewById(R.id.defB1Spin);
        defB2Spin = (Spinner) findViewById(R.id.defB2Spin);
        teamASpin = (Spinner) findViewById(R.id.teamASpinner);
        teamBSpin = (Spinner) findViewById(R.id.teamBSpinner);
        goalieAPic = (ImageView) findViewById(R.id.goalieAPic);
        goalieBPic = (ImageView) findViewById(R.id.goalieBPic);
        strikerA1Pic = (ImageView) findViewById(R.id.strikerA1Pic);
        strikerA2Pic = (ImageView) findViewById(R.id.strikerA2Pic);
        strikerB1Pic = (ImageView) findViewById(R.id.strikerB1Pic);
        strikerB2Pic = (ImageView) findViewById(R.id.strikerB2Pic);
        defA1Pic = (ImageView) findViewById(R.id.defA1Pic);
        defA2Pic = (ImageView) findViewById(R.id.defA2Pic);
        defB1Pic = (ImageView) findViewById(R.id.defB1Pic);
        defB2Pic = (ImageView) findViewById(R.id.defB2Pic);
        teamAPic = (ImageView) findViewById(R.id.teamAPic);
        teamBPic = (ImageView) findViewById(R.id.teamBPic);

        //Assign the data from main
        passedData = getIntent();
        listOfTeams = (HashMap<String, Team>) passedData.getSerializableExtra("Team Hash");
        listOfTeamNames = passedData.getStringArrayListExtra("Team List");
        listOfPlayers = (HashMap<String, Player>) passedData.getSerializableExtra("Player Hash");

        //Assign adapter for teams
        teamListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                listOfTeamNames);
        teamASpin.setAdapter(teamListAdapter);
        teamBSpin.setAdapter(teamListAdapter);

        //Create listeners for when something is selected
        teamASpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String teamName = adapter.getItemAtPosition(index).toString();
                teamA = listOfTeams.get(teamName);
                teamAPic.setImageResource(teamA.getLogoID());
                updateField(0);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        teamBSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String teamName = adapter.getItemAtPosition(index).toString();
                teamB = listOfTeams.get(teamName);
                teamBPic.setImageResource(teamB.getLogoID());
                updateField(1);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        goalieASpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                goalieA = listOfPlayers.get(playerName);
                goalieAPic.setImageResource(goalieA.getPortraitID());
                ((TextView)view).setText("G"); //Sets the spinner's text

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        goalieBSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                goalieB = listOfPlayers.get(playerName);
                goalieBPic.setImageResource(goalieB.getPortraitID());
                ((TextView)view).setText("G"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        strikerA1Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                strikerA1 = listOfPlayers.get(playerName);
                strikerA1Pic.setImageResource(strikerA1.getPortraitID());
                ((TextView)view).setText("O"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        strikerA2Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                strikerA2 = listOfPlayers.get(playerName);
                strikerA2Pic.setImageResource(strikerA2.getPortraitID());
                ((TextView)view).setText("O"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        strikerB1Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                strikerB1 = listOfPlayers.get(playerName);
                strikerB1Pic.setImageResource(strikerB1.getPortraitID());
                ((TextView)view).setText("O"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        strikerB2Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                strikerB2 = listOfPlayers.get(playerName);
                strikerB2Pic.setImageResource(strikerB2.getPortraitID());
                ((TextView)view).setText("O"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        defA1Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                defA1 = listOfPlayers.get(playerName);
                defA1Pic.setImageResource(defA1.getPortraitID());
                ((TextView)view).setText("D"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        defA2Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                defA2 = listOfPlayers.get(playerName);
                defA2Pic.setImageResource(defA2.getPortraitID());
                ((TextView)view).setText("D"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        defB1Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                defB1 = listOfPlayers.get(playerName);
                defB1Pic.setImageResource(defB1.getPortraitID());
                ((TextView)view).setText("D"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        defB2Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapter, View view, int index, long id) {
                String playerName = adapter.getItemAtPosition(index).toString();
                defB2 = listOfPlayers.get(playerName);
                defB2Pic.setImageResource(defB2.getPortraitID());
                ((TextView)view).setText("D"); //Sets the spinner's text
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /* -- Updates the field's spinners --*/
    private void updateField(int side) {
        if(side == 0) { //left side team is chosen
            teamARoster = teamA.getRoster();
            rosterAAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    teamARoster);

            //The modulos prevent an error to occur if a team has less than 5 players
            goalieASpin.setAdapter(rosterAAdapter);
            goalieASpin.setSelection(0);

            strikerA1Spin.setAdapter(rosterAAdapter);
            strikerA1Spin.setSelection(1 % teamARoster.size());

            strikerA2Spin.setAdapter(rosterAAdapter);
            strikerA2Spin.setSelection(4 % teamARoster.size());

            defA1Spin.setAdapter(rosterAAdapter);
            defA1Spin.setSelection(3 % teamARoster.size());

            defA2Spin.setAdapter(rosterAAdapter);
            defA2Spin.setSelection(2 % teamARoster.size());
        }
        else if(side == 1){ //right side team is chosen
            teamBRoster = teamB.getRoster();
            rosterBAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    teamBRoster);

            goalieBSpin.setAdapter(rosterBAdapter);
            goalieBSpin.setSelection(4 % teamBRoster.size());

            strikerB1Spin.setAdapter(rosterBAdapter);
            strikerB1Spin.setSelection(3 % teamBRoster.size());

            strikerB2Spin.setAdapter(rosterBAdapter);
            strikerB2Spin.setSelection(2 % teamBRoster.size());

            defB1Spin.setAdapter(rosterBAdapter);
            defB1Spin.setSelection(1 % teamBRoster.size());

            defB2Spin.setAdapter(rosterBAdapter);
            defB2Spin.setSelection(0);
        }
    }

    /* -- Returns to team menu -- */
    public void goBack(View view){
        finish();
    }

    /* -- Simulates the game; Gets Winner -- */
    public void play(View view){
        Random RNGesus = new Random(); //used for determining goals made

        int aOffense = ((strikerA1.getOffense()+strikerA2.getOffense()) + teamA.getOffense())*3/10;
        //60% of strikers avg + 30% of team avg
        //This is the equivalent; altered to avoid java's loved lack of decimals

        int bOffense = ((strikerB1.getOffense()+strikerB2.getOffense()) + teamB.getOffense())*3/10;
        //60% of strikers avg + 30% of team avg
        //Same style of reducing

        int aDefence = ((defA1.getDefense()+defA2.getDefense()) + teamA.getDefense())*3/10;
        //60% of defs' avg + 30% of team
        //Reduced again

        int bDefence = ((defB1.getDefense()+defB2.getDefense()) + teamB.getDefense())*3/10;
        //60% of defs' avg + 30% of team
        //Reduced again

        int aGoalkeep = goalieA.getGoalkeeping()/2 + teamA.getGoalkeeping()/5;
        //50% of goalie + 20% of team avg

        int bGoalkeep = goalieB.getGoalkeeping()/2 + teamB.getGoalkeeping()/5;
        //50% of goalie + 20% of team avg

        int aShots = (aOffense*9)/(bDefence*2);
        int bShots = (bOffense*9)/(aDefence*2);

        int aGoalProb = aOffense - bGoalkeep + 20;
        int bGoalProb = bOffense - aGoalkeep + 20;
        //The 20 is added to make the games a bit more volatile

        int aGoals = 0;
        int bGoals = 0;

        for(int i = 0; i < aShots; i++){
            if(RNGesus.nextInt(100) < aGoalProb){
                aGoals++;
            }
            if(RNGesus.nextInt(100) < bGoalProb){
                bGoals++;
            }
        }

        //Creates the results window for winner
        //Instantiates results screen
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View results = layoutInflater.inflate(R.layout.activity_results_screen, null);
        final PopupWindow resultsScreen = new PopupWindow(results, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //Assigns views for button
        TextView resultsText = (TextView) results.findViewById(R.id.resultText);
        TextView winningTeamText = (TextView) results.findViewById(R.id.winningTeamText);
        TextView scoreText = (TextView) results.findViewById(R.id.scoreText);
        Button back = (Button) results.findViewById(R.id.backButton);

        //Fills in views
        if (aGoals > bGoals){
            resultsText.setText("Winner:");
            winningTeamText.setText(teamA.getName());
        }
        else if (aGoals < bGoals){
            resultsText.setText("Winner:");
            winningTeamText.setText(teamB.getName());
        }
        else{
            resultsText.setText("Tie");
            winningTeamText.setText("No Winner");
        }

        scoreText.setText(aGoals + " - " + bGoals);

        //Displays the results
        resultsScreen.showAtLocation(results, Gravity.CENTER, 0, 0);

        //Closes results on button press
        back.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                resultsScreen.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
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
