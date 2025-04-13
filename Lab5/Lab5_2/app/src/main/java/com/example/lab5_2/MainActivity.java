package com.example.lab5_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView titleTxt, priceTxt, descriptionTxt;
    private RatingBar ratingBar;
    private ImageView arrowLeft, arrowRight, productImage;
    private List<ItemInfo> itemList = new ArrayList<>();
    private int currentItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        titleTxt = findViewById(R.id.titletxt);
        priceTxt = findViewById(R.id.pricetxt);
        descriptionTxt = findViewById(R.id.descriptxt);
        ratingBar = findViewById(R.id.ratingBar);
        arrowLeft = findViewById(R.id.arrowleft);
        arrowRight = findViewById(R.id.imageView3);
        productImage = findViewById(R.id.imageView);

        // Fetch data from API
        new FetchItemsTask().execute("https://dummyjson.com/products");

        // Set up click listeners for navigation arrows
        arrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentItemIndex > 0) {
                    currentItemIndex--;
                    displayCurrentItem();
                }
            }
        });

        arrowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentItemIndex < itemList.size() - 1) {
                    currentItemIndex++;
                    displayCurrentItem();
                }
            }
        });
    }

    private void displayCurrentItem() {
        if (itemList.isEmpty() || currentItemIndex < 0 || currentItemIndex >= itemList.size()) {
            return;
        }

        ItemInfo currentItem = itemList.get(currentItemIndex);
        titleTxt.setText(currentItem.getTitle());
        priceTxt.setText("$" + currentItem.getPrice());
        descriptionTxt.setText(currentItem.getDescription());

        try {
            float rating = Float.parseFloat(currentItem.getRating());
            ratingBar.setRating(rating);
        } catch (NumberFormatException e) {
            ratingBar.setRating(0);
            Log.e("MainActivity", "Error parsing rating: " + e.getMessage());
        }

        // Load product image using AsyncTask
        if (currentItem.getImageUrl() != null && !currentItem.getImageUrl().isEmpty()) {
            new LoadImageTask().execute(currentItem.getImageUrl());
        } else {
            productImage.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream in = new URL(imageUrl).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("LoadImageTask", "Error loading image: " + e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                productImage.setImageBitmap(result);
            } else {
                productImage.setImageResource(R.drawable.ic_launcher_background);
            }
        }
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

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject product = jsonArray.getJSONObject(i);
                        ItemInfo item = new ItemInfo();
                        item.setId(product.getString("id"));
                        item.setTitle(product.getString("title"));
                        item.setPrice(product.getString("price"));
                        item.setDescription(product.getString("description"));

                        // Get rating from the product object
                        item.setRating(product.getString("rating"));

                        // Get the first image URL
                        JSONArray images = product.getJSONArray("images");
                        if (images.length() > 0) {
                            item.setImageUrl(images.getString(0));
                        }

                        itemList.add(item);
                    }

                    // Display the first item
                    if (!itemList.isEmpty()) {
                        displayCurrentItem();
                    }

                } catch (JSONException e) {
                    Log.e("MainActivity", "Error parsing JSON: " + e.getMessage());
                }
            } else {
                Log.e("MainActivity", "Couldn't get JSON from server.");
            }
        }
    }
}