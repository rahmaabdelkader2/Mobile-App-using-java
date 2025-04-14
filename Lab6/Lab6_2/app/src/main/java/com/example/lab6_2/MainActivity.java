package com.example.lab6_2;
import android.os.AsyncTask;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        // Initialize adapter
        adaptor = new ProductAdaptor(itemList);
        recyclerView.setAdapter(adaptor);

        // Fetch data from API
        new FetchItemsTask().execute("https://dummyjson.com/products");
    }

    private class FetchItemsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpHandler handler = new HttpHandler();
            return handler.makeServiceCall(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
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
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Shutdown the executor service when the activity is destroyed
        adaptor.executorService.shutdown();
    }
}