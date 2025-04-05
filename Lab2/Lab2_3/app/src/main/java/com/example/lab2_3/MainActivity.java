package com.example.lab2_3;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String COUNTER_KEY = "rotation_counter";
    private int counter = 0;
    private TextView txtCounter;

    @Override
    // savedInstanceState reference to the bundle object
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCounter = findViewById(R.id.countNumber);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY, 0);
        }

        counter++;

        txtCounter.setText("Rotation count: " + counter);

        Log.i(TAG, "onCreate" );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, counter);
    }
}
