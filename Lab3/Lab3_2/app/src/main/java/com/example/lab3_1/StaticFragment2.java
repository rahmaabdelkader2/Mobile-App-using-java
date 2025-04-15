package com.example.lab3_1;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StaticFragment2 extends Fragment {
    private static final String COUNTER_KEY = "counter_key";
    private TextView counterTextView;
    private int currentCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_static2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        counterTextView = view.findViewById(R.id.Counttxt);

        if (savedInstanceState != null) {
            currentCounter = savedInstanceState.getInt(COUNTER_KEY, 0);
            updateCounter(currentCounter);
        }
    }

    public void updateCounter(int count) {
        currentCounter = count;
        if (counterTextView != null) {
            counterTextView.setText("Current Count: " + currentCounter);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, currentCounter);
    }
}