package com.example.administrator.fantasysoccergame;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
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

/* @author: Jarrett Oney
 * @date: 9/28/15
 * @purpose: Contains the main interface of the app, allows user
 *           to see the stats of different teams and move into
 *           over activities. Allows creation of new teams
 */

public class TeamMenu extends Activity {

    private TextView teamName;
    private ImageView teamPic;
    private SeekBar offenseVal;
    private SeekBar defenseVal;
    private SeekBar goalkVal;
    private SeekBar avgVal;
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


        //Set the views
        teamName = (TextView) findViewById(R.id.teamName);
        teamPic = (ImageView) findViewById(R.id.teamPic);
        offenseVal = (SeekBar) findViewById(R.id.offenseVal);
        defenseVal = (SeekBar) findViewById(R.id.defenseVal);
        goalkVal = (SeekBar) findViewById(R.id.goalkVal);
        avgVal = (SeekBar) findViewById(R.id.avgVal);
        teamList = (ListView) findViewById(R.id.teamList);
        newTeam = (EditText) findViewById(R.id.newTeamName);

        //Creates team lists
        listOfTeams = new HashMap<String, Team>();
        listOfTeamNames = new ArrayList<String>();

        //Creates player lists
        listOfPlayers = new HashMap<String, Player>();
        listOfPlayerNames = new ArrayList<String>();

        //Assigns team list to listview
        listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                listOfTeamNames);
        teamList.setAdapter(listAdapter);

        //Premade teams/players
        this.initializePlayers();
        this.initializeTeams();



        //Gets player and puts on stats screen
        teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long unneeded) {
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

    /* -- Initializes a new team based on name input -- */
    public void addTeam(View view){
        if(!newTeam.getText().toString().isEmpty() ||
                listOfPlayerNames.contains(newTeam.getText().toString())) { //Checks for misclick and duplo
            Team team = new Team(newTeam.getText().toString());
            listOfTeams.put(team.getName(), team);
            listAdapter.add(team.getName());
            newTeam.setText(""); //Resets the textbox
        }
    }

    /* -- Updates the stats screen with the given team -- */
    private void updateScreen(String key) {
        Team temp = listOfTeams.get(key);
        offenseVal.setProgress(temp.getOffense());
        defenseVal.setProgress(temp.getDefense());
        goalkVal.setProgress(temp.getGoalkeeping());
        avgVal.setProgress(temp.getAverage());
        teamName.setText(temp.getName());
        teamPic.setImageResource(temp.getLogoID());
    }

    /* -- Initializes the pre-made teams -- */
    private void initializeTeams() {

        Team wolves = new Team("Eagle River Wolves");
        wolves.setLogoID(R.drawable.wolves);
        wolves.addPlayer(listOfPlayers.get("Spicy Strawberry"));
        wolves.addPlayer(listOfPlayers.get("Coco Caramel"));
        wolves.addPlayer(listOfPlayers.get("Nama Shoyu"));
        wolves.addPlayer(listOfPlayers.get("A1"));
        wolves.addPlayer(listOfPlayers.get("Real Salt"));
        wolves.setStats();
        listOfTeams.put(wolves.getName(), wolves);
        listAdapter.add(wolves.getName());

        Team mustangs = new Team("Chugiak Mustangs");
        mustangs.setLogoID(R.drawable.mustangs);
        mustangs.addPlayer(listOfPlayers.get("Old Bay"));
        mustangs.addPlayer(listOfPlayers.get("General Tsao"));
        mustangs.addPlayer(listOfPlayers.get("Soy Milk"));
        mustangs.addPlayer(listOfPlayers.get("Nutmeg"));
        mustangs.addPlayer(listOfPlayers.get("Honey Bear"));
        mustangs.setStats();
        listOfTeams.put(mustangs.getName(), mustangs);
        listAdapter.add(mustangs.getName());

        Team eagles = new Team("West Eagles");
        eagles.setLogoID(R.drawable.eagles);
        eagles.addPlayer(listOfPlayers.get("True Thai"));
        eagles.addPlayer(listOfPlayers.get("Spicy Teriyaki"));
        eagles.addPlayer(listOfPlayers.get("Pico Limon"));
        eagles.addPlayer(listOfPlayers.get("Valentina's"));
        eagles.addPlayer(listOfPlayers.get("Better Than Bouillion"));
        eagles.setStats();
        listOfTeams.put(eagles.getName(), eagles);
        listAdapter.add(eagles.getName());

        Team bears = new Team("Bartlett Bears");
        bears.setLogoID(R.drawable.bears);
        bears.addPlayer(listOfPlayers.get("Signature Blend"));
        bears.addPlayer(listOfPlayers.get("Madagascar Vanilla"));
        bears.addPlayer(listOfPlayers.get("Woodstock Pickles"));
        bears.addPlayer(listOfPlayers.get("Honey Bear"));
        bears.addPlayer(listOfPlayers.get("Just Mayo"));
        bears.setStats();
        listOfTeams.put(bears.getName(), bears);
        listAdapter.add(bears.getName());

        Team wolverines = new Team("South Wolverines");
        wolverines.setLogoID(R.drawable.wolverines);
        wolverines.addPlayer(listOfPlayers.get("Coco Caramel"));
        wolverines.addPlayer(listOfPlayers.get("Soy Milk"));
        wolverines.addPlayer(listOfPlayers.get("Spicy Strawberry"));
        wolverines.addPlayer(listOfPlayers.get("True Thai"));
        wolverines.addPlayer(listOfPlayers.get("Spicy Teriyaki"));
        wolverines.setStats();
        listOfTeams.put(wolverines.getName(), wolverines);
        listAdapter.add(wolverines.getName());

        Team tBirds = new Team("East Thunderbirds");
        tBirds.setLogoID(R.drawable.thunderbirds);
        tBirds.addPlayer(listOfPlayers.get("Woodstock Pickles"));
        tBirds.addPlayer(listOfPlayers.get("Better Than Bouillion"));
        tBirds.addPlayer(listOfPlayers.get("Nutmeg"));
        tBirds.addPlayer(listOfPlayers.get("Madagascar Vanilla"));
        tBirds.addPlayer(listOfPlayers.get("Real Salt"));
        tBirds.setStats();
        listOfTeams.put(tBirds.getName(), tBirds);
        listAdapter.add(tBirds.getName());

        Team cougars = new Team("Service Cougars");
        cougars.setLogoID(R.drawable.cougars);
        cougars.addPlayer(listOfPlayers.get("Signature Blend"));
        cougars.addPlayer(listOfPlayers.get("Honey Bear"));
        cougars.addPlayer(listOfPlayers.get("General Tsao"));
        cougars.addPlayer(listOfPlayers.get("Valentina's"));
        cougars.addPlayer(listOfPlayers.get("Just Mayo"));
        cougars.setStats();
        listOfTeams.put(cougars.getName(), cougars);
        listAdapter.add(cougars.getName());

        Team lynx = new Team("Dimond Lynx");
        lynx.setLogoID(R.drawable.lynx);
        lynx.addPlayer(listOfPlayers.get("Woodstock Pickles"));
        lynx.addPlayer(listOfPlayers.get("Soy Milk"));
        lynx.addPlayer(listOfPlayers.get("True Thai"));
        lynx.addPlayer(listOfPlayers.get("Coco Caramel"));
        lynx.addPlayer(listOfPlayers.get("A1"));
        lynx.setStats();
        listOfTeams.put(lynx.getName(), lynx);
        listAdapter.add(lynx.getName());
    }

    /* -- Initializes the pre-made players -- */
    private void initializePlayers() {
        Player A1 = new Player("A1", 72, 36, 44);
        A1.setPortrait(R.drawable.a1);
        A1.addPosPlayed(1);
        listOfPlayers.put(A1.getName(), A1);
        listOfPlayerNames.add(A1.getName());

        Player boullion = new Player("Better Than Bouillion", 56, 60, 22);
        boullion.setPortrait(R.drawable.chicken_stock);
        boullion.addPosPlayed(2);
        listOfPlayers.put(boullion.getName(), boullion);
        listOfPlayerNames.add(boullion.getName());

        Player cocoCaramel = new Player("Coco Caramel", 76, 44, 60);
        cocoCaramel.setPortrait(R.drawable.coco_caramel);
        cocoCaramel.addPosPlayed(1);
        cocoCaramel.addPosPlayed(3);
        listOfPlayers.put(cocoCaramel.getName(), cocoCaramel);
        listOfPlayerNames.add(cocoCaramel.getName());

        Player tsao = new Player("General Tsao", 80, 13, 40);
        tsao.setPortrait(R.drawable.general_tsao);
        tsao.addPosPlayed(1);
        listOfPlayers.put(tsao.getName(), tsao);
        listOfPlayerNames.add(tsao.getName());

        Player bear = new Player("Honey Bear", 64, 70, 15);
        bear.setPortrait(R.drawable.honey_bear);
        bear.addPosPlayed(1);
        bear.addPosPlayed(2);
        listOfPlayers.put(bear.getName(), bear);
        listOfPlayerNames.add(bear.getName());

        Player nama = new Player("Nama Shoyu", 26, 57, 80);
        nama.setPortrait(R.drawable.nama_shoyu);
        nama.addPosPlayed(2);
        nama.addPosPlayed(3);
        listOfPlayers.put(nama.getName(), nama);
        listOfPlayerNames.add(nama.getName());

        Player nutmeg = new Player("Nutmeg", 88, 14, 25);
        nutmeg.setPortrait(R.drawable.nutmeg);
        nutmeg.addPosPlayed(1);
        listOfPlayers.put(nutmeg.getName(), nutmeg);
        listOfPlayerNames.add(nama.getName());

        Player oldBay = new Player("Old Bay", 60, 70, 10);
        oldBay.setPortrait(R.drawable.old_bay);
        oldBay.addPosPlayed(1);
        oldBay.addPosPlayed(2);
        listOfPlayers.put(oldBay.getName(), oldBay);
        listOfPlayerNames.add(oldBay.getName());

        Player pickles = new Player("Woodstock Pickles", 20, 50, 90);
        pickles.setPortrait(R.drawable.pickles);
        pickles.addPosPlayed(2);
        pickles.addPosPlayed(3);
        listOfPlayers.put(pickles.getName(), pickles);
        listOfPlayerNames.add(pickles.getName());

        Player pico = new Player("Pico Limon", 80, 20, 50);
        pico.setPortrait(R.drawable.pico_limon);
        pico.addPosPlayed(1);
        pico.addPosPlayed(3);
        listOfPlayers.put(pico.getName(), pico);
        listOfPlayerNames.add(pico.getName());

        Player salt = new Player("Real Salt", 25, 90, 50);
        salt.setPortrait(R.drawable.real_salt);
        salt.addPosPlayed(2);
        salt.addPosPlayed(3);
        listOfPlayers.put(salt.getName(), salt);
        listOfPlayerNames.add(salt.getName());

        Player robin = new Player("Signature Blend", 75, 50, 40);
        robin.setPortrait(R.drawable.red_robin);
        robin.addPosPlayed(1);
        listOfPlayers.put(robin.getName(), robin);
        listOfPlayerNames.add(robin.getName());

        Player mayo = new Player("Just Mayo", 40, 90, 50);
        mayo.setPortrait(R.drawable.chipotle_mayo);
        mayo.addPosPlayed(2);
        listOfPlayers.put(mayo.getName(), mayo);
        listOfPlayerNames.add(mayo.getName());

        Player milk = new Player("Soy Milk", 60, 60, 30);
        milk.setPortrait(R.drawable.soy_milk);
        milk.addPosPlayed(1);
        milk.addPosPlayed(2);
        listOfPlayers.put(milk.getName(), milk);
        listOfPlayerNames.add(milk.getName());

        Player jam = new Player("Spicy Strawberry", 95, 15, 40);
        jam.setPortrait(R.drawable.spicy_strawberry);
        jam.addPosPlayed(1);
        listOfPlayers.put(jam.getName(), jam);
        listOfPlayerNames.add(jam.getName());

        Player teriyaki = new Player("Spicy Teriyaki", 85, 35, 30);
        teriyaki.setPortrait(R.drawable.teriyaki);
        teriyaki.addPosPlayed(1);
        listOfPlayers.put(teriyaki.getName(), teriyaki);
        listOfPlayerNames.add(teriyaki.getName());

        Player thai = new Player("True Thai", 70, 50, 30);
        thai.setPortrait(R.drawable.true_thai);
        thai.addPosPlayed(1);
        thai.addPosPlayed(2);
        listOfPlayers.put(thai.getName(), thai);
        listOfPlayerNames.add(thai.getName());

        Player valentinas = new Player("Valentina's", 20, 40, 80);
        valentinas.setPortrait(R.drawable.valentinos);
        valentinas.addPosPlayed(3);
        listOfPlayers.put(valentinas.getName(), valentinas);
        listOfPlayerNames.add(valentinas.getName());

        Player vanilla = new Player("Madagascar Vanilla", 75, 55, 60);
        vanilla.setPortrait(R.drawable.vanilla);
        vanilla.addPosPlayed(1);
        vanilla.addPosPlayed(3);
        listOfPlayers.put(vanilla.getName(), vanilla);
        listOfPlayerNames.add(vanilla.getName());

    }

    /* -- Passes the player and team info to editor, opens editor ---  */
    public void openTeamEditor(View view){
        if(!teamName.getText().toString().isEmpty()) {
            Intent intent = new Intent(this, TeamEditor.class);

            intent.putExtra("Team Selected", listOfTeams.get(teamName.getText().toString()));

            intent.putStringArrayListExtra("Player List", listOfPlayerNames);
            intent.putExtra("Player Hash", listOfPlayers);

            startActivityForResult(intent, 100);
        }
    }

    /* -- Moves to the game activity -- */
    public void startGame(View view){
        Intent intent = new Intent(this, PlayGame.class);

        intent.putStringArrayListExtra("Team List", listOfTeamNames);
        intent.putExtra("Team Hash", listOfTeams);

        intent.putStringArrayListExtra("Player List", listOfPlayerNames);
        intent.putExtra("Player Hash", listOfPlayers);

        startActivity(intent);

    }

    /* -- Pulls info from editor activity -- */
    public void onActivityResult(int reqCode, int resCode, Intent returnedData){
        if(reqCode == 100)
        {
            if(resCode == RESULT_OK){
                listOfPlayerNames = returnedData.getStringArrayListExtra("New Player List");
                listOfPlayers = (HashMap<String, Player>) returnedData.getSerializableExtra("New Player Hash");
                Team temp = (Team) returnedData.getSerializableExtra("New Team");
                listOfTeams.put(temp.getName(), temp);
                listAdapter.notifyDataSetChanged();
                this.updateScreen(temp.getName());
            }
        }
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
