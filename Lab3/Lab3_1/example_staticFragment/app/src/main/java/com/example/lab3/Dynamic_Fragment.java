package com.example.lab3;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Dynamic_Fragment extends Fragment {


    private String mParam1;
    private String mParam2;

    public Dynamic_Fragment() {
        // Required empty public constructor
    }


    public static Dynamic_Fragment newInstance(String param1, String param2) {
        Dynamic_Fragment fragment = new Dynamic_Fragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView =view.findViewById(R.id.dynamictxt);
        textView.setText("this is dynamic fragment!");

    }
}