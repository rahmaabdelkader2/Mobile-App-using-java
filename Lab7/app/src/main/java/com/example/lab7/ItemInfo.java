package com.example.lab7;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("price")
    private String price;

    @SerializedName("description")
    private String description;

    @SerializedName("rating")
    private String rating;

    @SerializedName("images")
    private List<String> images;

    public String getImageUrl() {
        return (images != null && !images.isEmpty()) ? images.get(0) : null;
    }


    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }


    public String getPrice() {
        return price;
    }


    public String getTitle() {
        return title;
    }

}