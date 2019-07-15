package com.gmail.kdiodati.piggamev4;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FirstFragment extends Fragment {

    private GameLogic logic;

    private EditText p1Name;
    private EditText p2Name;

    public static final String PLAYER_1 = "player 1";
    public static final String PLAYER_2 = "player 2";

    private FirstActivity activity;

    private boolean twoPaneLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = (FirstActivity) getActivity();
        p1Name = (EditText) activity.findViewById(R.id.player1EditText);
        p2Name = (EditText) activity.findViewById(R.id.player2EditText);

        this.logic = new GameLogic();
        activity.setLogic(logic);

        twoPaneLayout = activity.findViewById(R.id.second_fragment) != null;
    }

    public void play(View v) {
        if (v.getId() == R.id.newGameButton) {
            if (twoPaneLayout) {
                logic.player1 = p1Name.getText().toString();
                logic.player2 = p2Name.getText().toString();
                activity.setLogic(logic);
                activity.startGame();
            }
            else {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra(PLAYER_1, p1Name.getText().toString());
                intent.putExtra(PLAYER_2, p2Name.getText().toString());
                startActivity(intent);
            }
        }
    }

    /*
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
}
