package com.gmail.kdiodati.piggamev3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FirstScreen extends AppCompatActivity{

    EditText p1Name;
    EditText p2Name;

    public static final String PLAYER_1 = "player 1";
    public static final String PLAYER_2 = "player 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        p1Name = (EditText) findViewById(R.id.player1EditText);
        p2Name = (EditText) findViewById(R.id.player2EditText);
    }

    public void play(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(PLAYER_1, p1Name.getText().toString());
        intent.putExtra(PLAYER_2, p2Name.getText().toString());
        startActivity(intent);
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




}
