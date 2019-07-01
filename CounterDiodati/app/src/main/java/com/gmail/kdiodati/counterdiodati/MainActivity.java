package com.gmail.kdiodati.counterdiodati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private TextView counter;
    private Button clicker;
    private Button reset;

    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize count
        count = 0;

        //get references
        counter = (TextView) findViewById(R.id.counterText);
        clicker = (Button) findViewById(R.id.clickerButton);
        reset = (Button) findViewById(R.id.resetButton);

        //set listeners
        clicker.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickerButton:
                count = count + 1; //increment count
                counter.setText(Integer.toString(count)); //make int a string and display it
                break;
            case R.id.resetButton:
                count = 0; //reset count to zero
                counter.setText("0"); //display zero on the screen
                break;
        }
    }
}
