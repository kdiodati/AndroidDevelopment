package edu.uoregon.bbird.weatherdemo;

// Written by Brian Bird 7/11/15, updated 7/18/18

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.FCT_TEXT;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.IMAGE_ID;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.POP;
import static edu.uoregon.bbird.weatherdemo.WeatherSQLiteHelper.TITLE;

public class MainActivity extends Activity 
				implements OnItemSelectedListener {

    /******* ------  Instance Variables -------------- *************/

    private Dal dal = new Dal(this);
	Cursor cursor = null;
	String locationSelection = "Eugene";
	SimpleCursorAdapter adapter = null;
    ListView itemsListView;


	/********* --------- Activity and Listener Callback Methods --------- ***********/

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemsListView = (ListView)findViewById(R.id.forecastListView);

        // Set up location selection spinner
        Spinner locationSpinner = (Spinner)findViewById(R.id.locationSpinner);
        locationSpinner.setOnItemSelectedListener(this);
       }

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position){
		case 0:
			locationSelection = "Eugene";
			break;
		case 1:
			locationSelection = "Newport";
			break;
		case 2:
			locationSelection = "Sisters";
		}
        // Get a weather forecast for the selected location
        getForecast("OR", locationSelection);
        // TODO: replace hard-coded state with selected state
    }

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub	
	}

	/************* ----------- Private Methods ------------- ***************/

	private void getForecast(String state, String city) {
        // If there isn't a forecast in the db for this location and date, then get one from the web service
        cursor = dal.getForcastFromDb(state, city);
        if (cursor.getCount() == 0) {
            // Get a forecast from the web service, put it in the dB, get it back out again, and display it
            new RestTask().execute(state, city);
        } else {
        	// Display the forecast that is already in the dB
            displayForecast();
        }
    }

	private void displayForecast() {
		// Set up the adapter for the ListView to display the forecast
		adapter = new SimpleCursorAdapter(
				this,
				R.layout.listview_items,
				cursor,
				new String[]{TITLE,
						IMAGE_ID,
						FCT_TEXT,
						POP},
				new int[]{
						R.id.dateTextView,
                        R.id.iconImageView,
						R.id.descriptionTextView,
						R.id.popTextView
				},
				0 );	// no flags
        itemsListView.setAdapter(adapter);
	}


	/************* ----------- Nested Class ------------- ***************/
	/********************************************************************/

	public class RestTask extends AsyncTask<String, Void, WeatherItems> {

        private String state;
		private String city;

		@Override
		protected WeatherItems doInBackground(String... params) {

			// build the string used for the REST query
			String apiKey = "0e3e69302fba4e56";  // put your own API key here
			// Get a free API key at: https://www.wunderground.com/?apiref=5cdccc9428586099
			String baseUrl = "http://api.wunderground.com/api/";
			state = params[0];
			city = params[1];
			String query = "/forecast/q/" + state + "/" + city + ".xml";

            WeatherItems items = null;
            try {
                URL url = new URL(baseUrl + apiKey + query);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestProperty("User-Agent", "weatherdemo-app");  // Identifies the sender (our app), can be an empty string
                connection.setRequestMethod("GET");   // The HTTP request type
                connection.connect();
                InputStream in = connection.getInputStream();

				if (in != null) {
                    items = dal.parseXmlStream(in);
                    items.setState(state);
                    items.setCity(city);
                }
                connection.disconnect();

			} catch (Exception e) {
				Log.e("RestTask", "doInBackground error: " + e.getLocalizedMessage());
			}

			return items;
		}


		@Override
		protected void onPostExecute(WeatherItems items) {
			if (items != null && items.size() != 0) {
				dal.putForecastIntoDb(items);
                cursor = dal.getForcastFromDb(state, city);
                displayForecast();
			}
		}
	}
}
