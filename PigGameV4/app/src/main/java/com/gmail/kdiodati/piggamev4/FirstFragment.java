package com.gmail.kdiodati.piggamev4;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FirstFragment extends Fragment implements OnClickListener {

    private GameLogic logic;

    private EditText p1Name;
    private EditText p2Name;

    private Button newGame;

    public static final String PLAYER_1 = "player1";
    public static final String PLAYER_2 = "player2";

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

        newGame =(Button) activity.findViewById(R.id.newGameButton);

        newGame.setOnClickListener(this);

        this.logic = new GameLogic();
        activity.setLogic(logic);

        twoPaneLayout = activity.findViewById(R.id.second_fragment) != null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGameButton:
                if (twoPaneLayout) { //if there are 2 fragments, run 2nd fragment
                    logic.player1 = p1Name.getText().toString();
                    logic.player2 = p2Name.getText().toString();
                    activity.setLogic(logic);
                    activity.startGame();
                }
                else { //if there is only one fragment, just start a new activity for 2nd fragment
                    Intent intent = new Intent(getActivity(), SecondActivity.class);
                    intent.putExtra(PLAYER_1, p1Name.getText().toString());
                    intent.putExtra(PLAYER_2, p2Name.getText().toString());
                    startActivity(intent);
                }

                break;
        }
    }
}
