package com.gmail.kdiodati.piggamev1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText p1Name;
    private EditText p2Name;

    private TextView p1Score;
    private TextView p2Score;
    private TextView turnIndicator;
    private TextView turnPoints;

    private ImageView diePicture;

    private Button rollDie;
    private Button endTurn;
    private Button newGame;

    private String player1Name;
    private String player2Name;

    private int p1;
    private int p2;
    private int score;

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

        player1Name = "Player 1";
        player2Name = "Player 2";

        p1 = 0;
        p2 = 0;
        score = 0;

        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);
        newGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rollDieButton:

                break;
            case R.id.endTurnButton:

                break;
            case R.id.newGameButton:
                p1 = 0;
                p2 = 0;
                score = 0;
                player1Name = "Player 1";
                player2Name = "Player 2";

                p1Score.setText("0");
                p2Score.setText("0");
                p1Name.setText("");
                p2Name.setText("");
                turnIndicator.setText("Please Enter Your Names");
                turnPoints.setText("0");
                break;
        }
    }
}
