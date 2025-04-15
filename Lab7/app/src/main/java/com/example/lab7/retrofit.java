package com.example.lab7;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface retrofit {
    @GET("products")
    Call<ProductResponse> getProducts();
}

class ProductResponse {
    private List<ItemInfo> products;

    public List<ItemInfo> getProducts() {
        return products;
    }


}