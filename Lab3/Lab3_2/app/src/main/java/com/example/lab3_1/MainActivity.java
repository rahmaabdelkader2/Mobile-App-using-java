package com.example.lab3_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements StaticFragment.FragmentCommunicationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCounterUpdated(int count) {
        // Find the second fragment
        Fragment fragment2 = getSupportFragmentManager()
                .findFragmentById(R.id.fragmentContainerView2);

        if (fragment2 instanceof StaticFragment2) {
            ((StaticFragment2) fragment2).updateCounter(count);
        }
    }
}