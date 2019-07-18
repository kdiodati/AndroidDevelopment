package com.gmail.kdiodati.tideappv1;

//Written by Kyle Diodati July 17 2019
//modelled after WeatherForecast by Brian Bird

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements OnItemClickListener {

    private TideItems tideItems;
    static final String DATE = "date";
    static final String HIGH_LOW_TIME = "highLowTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileIO io = new FileIO(getApplicationContext());
        tideItems = io.readFile();

        //data source for the adapter
        ArrayList<HashMap<String, String>> data = new ArrayList<>();

        //for every tideItem put the modified date and highLowTime strings in a hashmap
        //which will be used by the simple adapter
        for (TideItem item : tideItems) {
            HashMap<String, String> map = new HashMap<>();

            map.put(DATE, item.getDateFormatted());

            map.put(HIGH_LOW_TIME, item.getHighLowTime());

            data.add(map);
        }

        //adapter to place all items in tideItems into the ListView
        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.listview_items,
                new String[]{DATE, HIGH_LOW_TIME},
                new int[]{R.id.dateTextView, R.id.tideTextView }
        );

        ListView itemsListView = (ListView) findViewById(R.id.tideListView);
        itemsListView.setAdapter(adapter);
        itemsListView.setOnItemClickListener(this);
    }

    //when you click on a row, it displays the Predicted Tide
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TideItem item = tideItems.get(position);
        Toast.makeText(this, item.getPred() + " Feet", Toast.LENGTH_LONG).show();
    }
}
