package com.gmail.kdiodati.piggamev4;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

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

    private GameLogic logic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        rollDie = (Button) view.findViewById(R.id.rollDieButton);
        endTurn = (Button) view.findViewById(R.id.endTurnButton);

        rollDie.setOnClickListener(this);
        endTurn.setOnClickListener(this);

        p1Name = (TextView) view.findViewById(R.id.player1Text);
        p2Name = (TextView) view.findViewById(R.id.player2Text);

        p1Score = (TextView) view.findViewById(R.id.player1ScoreHolder);
        p2Score = (TextView) view.findViewById(R.id.player2ScoreHolder);
        turnIndicator = (TextView) view.findViewById(R.id.turnIndicator);
        turnPoints = (TextView) view.findViewById(R.id.turnPoints);

        diePicture = (ImageView) view.findViewById(R.id.diceImageView);

        rollDie.setVisibility(INVISIBLE);
        endTurn.setVisibility(INVISIBLE);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rollDieButton:
                if (logic.winner <= 0) {
                    logic.rollTheDie();
                    updateScreen();
                }
                else {
                    logic.resetGame(); //case shouldn't be possible but left in for good measure
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

    public void startGame(GameLogic logic_) {
        //sets the GameLogic and begins the game
        logic = logic_;

        player1 = logic_.player1;
        player2 = logic_.player2;

        rollDie.setVisibility(VISIBLE);
        endTurn.setVisibility(VISIBLE);

        logic.resetGame();

        updateScreen();
    }

    private void updateScreen() {
        if (player1.length() == 0) {
            player1 = "Player 1";
        }
        p1Name.setText(player1);

        if (player2.length() == 0) {
            player2 = "Player 2";
        }
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

        //if the turn is over make the rollDie button invisible
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
