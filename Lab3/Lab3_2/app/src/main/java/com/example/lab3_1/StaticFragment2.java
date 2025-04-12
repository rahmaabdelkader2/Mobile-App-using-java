package com.example.lab3_1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StaticFragment2 extends Fragment {
    private TextView counterTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_static2, container, false);
        counterTextView = view.findViewById(R.id.Counttxt);
        return view;
    }

    public void updateCounter(int count) {
        if (counterTextView != null) {
            counterTextView.setText("Count: " + count);
        }
    }
}