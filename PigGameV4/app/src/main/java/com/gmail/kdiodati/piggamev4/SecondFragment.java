package com.gmail.kdiodati.piggamev4;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.View.INVISIBLE;


public class SecondFragment extends Fragment implements OnClickListener {
    TextView p1Name;
    TextView p2Name;

    TextView p1Score;
    TextView p2Score;
    TextView turnIndicator;
    TextView turnPoints;

    ImageView diePicture;

    private Button rollDie;
    private Button endTurn;

    String player1;
    String player2;

    String player1Given;
    String player2Given;

    SharedPreferences prefs;

    public boolean showDice;
    public int winningScore;
    public int maxRolls;
    public boolean customNames;

    private GameLogic logic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        rollDie = (Button) view.findViewById(R.id.rollDieButton);
        endTurn = (Button) view.findViewById(R.id.endTurnButton);

        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);

        return view;
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        p1Name = (TextView) findViewById(R.id.player1Text);
        p2Name = (TextView) findViewById(R.id.player2Text);

        p1Score = (TextView) findViewById(R.id.player1ScoreHolder);
        p2Score = (TextView) findViewById(R.id.player2ScoreHolder);
        turnIndicator = (TextView) findViewById(R.id.turnIndicator);
        turnPoints = (TextView) findViewById(R.id.turnPoints);

        diePicture = (ImageView) findViewById(R.id.diceImageView);

        rollDie = (Button) findViewById(R.id.rollDieButton);
        endTurn = (Button) findViewById(R.id.endTurnButton);

        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);

        if (logic == null) {
            logic = new GameLogic();
        }

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        maxRolls = Integer.parseInt(prefs.getString("max_rolls", "0"));
        winningScore = Integer.parseInt(prefs.getString("winning_score", "100"));

        //if there is no saved data set variables to initial numbers
        if (savedInstanceState == null) {
            logic.resetGame(maxRolls, winningScore); //sets the variables of logic
        }
        //if saved data is available, use it
        else {
            logic.loadData(
                    savedInstanceState.getInt("Roll"),
                    savedInstanceState.getInt("Turn"),
                    savedInstanceState.getInt("P1"),
                    savedInstanceState.getInt("P2"),
                    savedInstanceState.getInt("Score"),
                    savedInstanceState.getInt("Winner"),
                    savedInstanceState.getInt("RollCount"),
                    maxRolls,
                    winningScore,
                    savedInstanceState.getBoolean("Over")
            );
            player1 = savedInstanceState.getString("Player1");
            player2 = savedInstanceState.getString("Player2");

            updateScreen();
        }
    }
    */

    /*
    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        player1Given = intent.getExtras().getString(FirstActivity.PLAYER_1);
        player2Given = intent.getExtras().getString(FirstActivity.PLAYER_2);

        if (logic == null) {
            logic = new GameLogic();
        }

        //
        showDice = prefs.getBoolean("pref_show_dice", true);
        if (showDice) {
            diePicture.setVisibility(VISIBLE);
        }
        else {
            diePicture.setVisibility(GONE);
        }

        //
        winningScore = Integer.parseInt(prefs.getString("winning_score", "100"));
        if (winningScore < 0) {
            winningScore = 100;
        }
        logic.updateWinScore(winningScore);

        //
        maxRolls = Integer.parseInt(prefs.getString("max_rolls", "0"));
        logic.updateMaxRolls(maxRolls);

        //If custom names are enabled set p1/p2 to them, otherwise set to player1/player2
        customNames = prefs.getBoolean("custom_names", true);
        if (customNames) {
            player1 = player1Given;
            if (player1.length() == 0) {
                player1 = "Player 1"; //if a name has not been entered use 'Player 1'
            }
            player2 = player2Given;
            if (player2.length() == 0) {
                player2 = "Player 2"; //if a name has not been entered use 'Player 2'
            }
        }
        else {
            player1 = "Player 1";
            player2 = "Player 2";
        }
        p1Name.setText(player1);
        p2Name.setText(player2);
    }
    */

    /*
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("RollCount", logic.rollCount);

        savedInstanceState.putInt("Roll", logic.roll);
        savedInstanceState.putInt("Turn",logic.turn);
        savedInstanceState.putInt("P1", logic.p1);
        savedInstanceState.putInt("P2", logic.p2);
        savedInstanceState.putInt("Score", logic.score);
        savedInstanceState.putInt("Winner", logic.winner);

        savedInstanceState.putBoolean("Over", logic.over);

        savedInstanceState.putString("Player1", player1);
        savedInstanceState.putString("Player2", player2);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        }
        else if (id == R.id.menu_about) {
            Toast.makeText(this, "This game was written by Kyle Diodati", Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
        //this code is practically an exact copy of your rps game
    }
    */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rollDieButton:
                if (logic.winner <= 0) {
                    logic.rollTheDie();
                    updateScreen();
                }
                else {
                    logic.resetGame(maxRolls, winningScore);
                    updateScreen();
                }
                break;
            case R.id.endTurnButton:
                logic.endTurn();
                updateScreen();
                rollDie.setVisibility(VISIBLE);
                break;
        }
    }

    public void startGame(GameLogic logic) {
        this.logic = logic;
    }

    private void updateScreen() {
        p1Name.setText(player1);
        //player2 = p2Name.getText().toString();
        p2Name.setText(player2);

        //sets the picture of the die depending on the last roll
        if (logic.roll == 1) {
            diePicture.setImageResource(R.drawable.die1);
        }
        else if (logic.roll == 2) {
            diePicture.setImageResource(R.drawable.die2);
        }
        else if (logic.roll == 3) {
            diePicture.setImageResource(R.drawable.die3);
        }
        else if (logic.roll == 4) {
            diePicture.setImageResource(R.drawable.die4);
        }
        else if (logic.roll == 5) {
            diePicture.setImageResource(R.drawable.die5);
        }
        else if (logic.roll == 6) {
            diePicture.setImageResource(R.drawable.die6);
        }

        if (logic.over == true) {
            rollDie.setVisibility(INVISIBLE);
        }

        //sets the score of the players
        p1Score.setText(Integer.toString(logic.p1));
        p2Score.setText(Integer.toString(logic.p2));

        //if there has been a winner, announce it! otherwise announce which player's turn it is
        if (logic.winner == 1) {
            turnIndicator.setText(player1 + " WINS!");
            rollDie.setVisibility(GONE);
            endTurn.setVisibility(GONE);
        }
        else if (logic.winner == 2) {
            turnIndicator.setText(player2 + " WINS!");
            rollDie.setVisibility(GONE);
            endTurn.setVisibility(GONE);
        }
        else {
            if (logic.turn == 1) {
                turnIndicator.setText(player1 + "'s turn");
            } else if (logic.turn == 2) {
                turnIndicator.setText(player2 + "'s turn");
            }
        }

        //set the score of the current turn (or 0 if turn ended)
        turnPoints.setText(Integer.toString(logic.score));
    }
}
