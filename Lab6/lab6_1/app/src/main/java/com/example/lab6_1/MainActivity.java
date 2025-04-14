package com.example.lab6_1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        // Create a list of Ducks with all your images
        List<Ducks> ducksList = new ArrayList<>();
        ducksList.add(new Ducks(R.drawable.hair, "Hair", "Black hair"));
        ducksList.add(new Ducks(R.drawable.greenhat, "Green Hat", "Stylish green hat"));
        ducksList.add(new Ducks(R.drawable.bunnyhat, "Bunny Hat", "Cute bunny ears"));
        ducksList.add(new Ducks(R.drawable.leaves, "Leaves", "Decorated with leaves"));
        ducksList.add(new Ducks(R.drawable.coolglasses, "Cool Glasses", "Stylish sunglasses"));
        ducksList.add(new Ducks(R.drawable.hearts, "Hearts", "Adorned with hearts"));
        ducksList.add(new Ducks(R.drawable.onepiecehat, "One Piece Hat", "Hat from One Piece"));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Create and set adapter
        DuckAdaptor adapter = new DuckAdaptor(ducksList);
        recyclerView.setAdapter(adapter);
    }
}