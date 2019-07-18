package com.gmail.kdiodati.piggamev4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    private GameLogic logic;

    //public GameLogic getLogic() { return logic; }

    //sets the logic of the activity to that of the fragment
    public void setLogic(GameLogic logic_) { logic = logic_; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
    }

    public void startGame() {
        //if there are multiple fragments up on screen and the start button
        //is pressed start the game on the second fragment
        SecondFragment secondFragment = (SecondFragment)getSupportFragmentManager()
                .findFragmentById(R.id.second_fragment);
        secondFragment.startGame(logic);
    }
}
