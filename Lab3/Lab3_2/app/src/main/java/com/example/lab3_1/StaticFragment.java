package com.example.lab3_1;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class StaticFragment extends Fragment {
    private static final String COUNTER_KEY = "counter_key";
    private int counter = 0;
    private FragmentCommunicationListener listener;

    public interface FragmentCommunicationListener {
        void onCounterUpdated(int count);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCommunicationListener) {
            listener = (FragmentCommunicationListener) context;
        } else {
            throw new RuntimeException(context + " must implement FragmentCommunicationListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_static, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.countbtn);
        btn.setOnClickListener(v -> {
            counter++;
            Toast.makeText(getActivity(), "Button clicked! Count: " + counter, Toast.LENGTH_SHORT).show();
            if (listener != null) {
                listener.onCounterUpdated(counter);
            }
        });

        // Update UI with restored counter
        if (savedInstanceState != null) {
            listener.onCounterUpdated(counter);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, counter);
    }
}