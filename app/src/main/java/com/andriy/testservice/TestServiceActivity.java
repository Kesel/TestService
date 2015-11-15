package com.andriy.testservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class TestServiceActivity extends AppCompatActivity implements Callback<List<Airport>> {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Task task = new Task(8,888);
        AirportsService service = ApiFactory.getAirportsService();
//        Call<List<Airport>> call = service.airports("55.749792,37.6324949");
//        call.enqueue(this);
        Call<Task> call = service.createTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Response<Task> response) {
                if (response.isSuccess()) {
                    Task resTask = response.body();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        Call<TimeNow> myCall = service.getTimeNow();
        myCall.enqueue(new Callback<TimeNow>() {
            @Override
            public void onResponse(Response<TimeNow> response) {
                if (response.isSuccess()){
                    TimeNow timeNow = response.body();
                    try {
                        Date date = ISO8601DateParser.parse(timeNow.mDate);
                        Log.d(LOG_TAG, ISO8601DateParser.toString(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickStart(View v) {
        startService(new Intent(this, MyService.class));
    }

    public void onClickStop(View v) {
        stopService(new Intent(this, MyService.class));
    }

    @Override
    public void onResponse(Response<List<Airport>> response) {

        if (response.isSuccess()) {
            List<Airport> airports = response.body();
            //do something here
            Log.d(LOG_TAG, "Airports " + Integer.toString(airports.size()));
        }

    }

    @Override
    public void onFailure(Throwable t) {

    }
}
