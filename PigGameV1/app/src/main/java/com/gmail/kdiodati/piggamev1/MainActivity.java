package com.gmail.kdiodati.piggamev1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText p1Name;
    EditText p2Name;

    TextView p1Score;
    TextView p2Score;
    TextView turnIndicator;
    TextView turnPoints;

    ImageView diePicture;

    private Button rollDie;
    private Button endTurn;
    private Button newGame;

    Random rand = new Random();

    String player1;
    String player2;

    private GameLogic logic = new GameLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1Name = (EditText) findViewById(R.id.player1NameEdit);
        p2Name = (EditText) findViewById(R.id.player2NameEdit);

        p1Score = (TextView) findViewById(R.id.player1ScoreHolder);
        p2Score = (TextView) findViewById(R.id.player2ScoreHolder);
        turnIndicator = (TextView) findViewById(R.id.turnIndicator);
        turnPoints = (TextView) findViewById(R.id.turnPoints);

        diePicture = (ImageView) findViewById(R.id.diceImageView);

        rollDie = (Button) findViewById(R.id.rollDieButton);
        endTurn = (Button) findViewById(R.id.endTurnButton);
        newGame = (Button) findViewById(R.id.newGameButton);

        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);
        newGame.setOnClickListener(this);

        logic.resetGame(); //sets the variables of logic
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rollDieButton:
                if (logic.winner <= 0) {
                    logic.rollTheDie(rand);
                    updateScreen();
                }
                else {
                    logic.resetGame();
                    updateScreen();
                }
                break;
            case R.id.endTurnButton:
                logic.endTurn();
                updateScreen();
                break;
            case R.id.newGameButton:
                logic.resetGame();
                updateScreen();
                p1Name.setText("");
                p2Name.setText("");
                turnIndicator.setText("Player 1 will begin first");
                break;
        }
    }

    private void updateScreen() {
        //sets names of the players for the turn/win messages
        player1 = p1Name.getText().toString();
        if (player1.length() == 0) {
            player1 = "Player 1"; //if a name has not been entered use 'Player 1'
        }
        player2 = p2Name.getText().toString();
        if (player2.length() == 0) {
            player2 = "Player 2"; //if a name has not been entered use 'Player 2'
        }

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

        //sets the score of the players
        p1Score.setText(Integer.toString(logic.p1));
        p2Score.setText(Integer.toString(logic.p2));

        //if there has been a winner, announce it! otherwise announce which player's turn it is
        if (logic.winner == 1) {
            turnIndicator.setText(player1 + " WINS!");
        }
        else if (logic.winner == 2) {
            turnIndicator.setText(player2 + " WINS!");
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
