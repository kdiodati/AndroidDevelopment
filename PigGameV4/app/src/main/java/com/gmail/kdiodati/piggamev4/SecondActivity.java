package com.gmail.kdiodati.piggamev4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //just creates the fragment and gives it everything it needs to run
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        GameLogic logic = new GameLogic();

        String p1 = getIntent().getExtras().getString("player1");
        String p2 = getIntent().getExtras().getString("player2");

        logic.player1 = p1; //set the player names based off the first activity
        logic.player2 = p2;

        SecondFragment secondFragment = (SecondFragment)getSupportFragmentManager()
                .findFragmentById(R.id.second_fragment);

        secondFragment.startGame(logic);
    }
}

