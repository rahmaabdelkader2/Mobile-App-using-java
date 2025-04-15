package com.example.lab6_2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdaptor adaptor;
    private List<ItemInfo> itemList = new ArrayList<>();
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Handler
        mainHandler = new Handler(Looper.getMainLooper());

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Initialize adapter
        adaptor = new ProductAdaptor(itemList);
        recyclerView.setAdapter(adaptor);

        // Fetch data from API using Thread+Handler approach
        fetchItemsWithThread("https://dummyjson.com/products");
    }

    private void fetchItemsWithThread(String url) {
        new Thread(() -> {
            // Background work: fetch JSON data
            HttpHandler handler = new HttpHandler();
            final String jsonStr = handler.makeServiceCall(url);

            // Post result to main thread
            mainHandler.post(() -> {
                // UI thread work: process JSON and update UI
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr);
                        JSONArray jsonArray = jsonObject.getJSONArray("products");
                        List<ItemInfo> newList = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject product = jsonArray.getJSONObject(i);
                            ItemInfo item = new ItemInfo();
                            item.setId(product.getString("id"));
                            item.setTitle(product.getString("title"));
                            item.setPrice(product.getString("price"));
                            item.setDescription(product.getString("description"));
                            item.setRating(product.getString("rating"));

                            // Get the first image URL
                            JSONArray images = product.getJSONArray("images");
                            if (images.length() > 0) {
                                item.setImageUrl(images.getString(0));
                            }

                            newList.add(item);
                        }

                        // Update adapter with new data
                        adaptor.updateData(newList);

                    } catch (JSONException e) {
                        Log.e("MainActivity", "Error parsing JSON: " + e.getMessage());
                    }
                } else {
                    Log.e("MainActivity", "Couldn't get JSON from server.");
                }
            });
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove any pending Handler callbacks
        mainHandler.removeCallbacksAndMessages(null);
    }
}