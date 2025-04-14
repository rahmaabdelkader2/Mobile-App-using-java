package com.example.lab6_2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ProductViewHolder> {

    private List<ItemInfo> productList;
    public ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ProductAdaptor(List<ItemInfo> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ItemInfo item = productList.get(position);
        holder.titleTxt.setText(item.getTitle());
        holder.priceTxt.setText("$" + item.getPrice());
        holder.description.setText(item.getDescription());

        try {
            float rating = Float.parseFloat(item.getRating());
            holder.ratingBar.setRating(rating);
        } catch (NumberFormatException e) {
            holder.ratingBar.setRating(0);
        }

        // Load image in background thread
        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
            executorService.execute(() -> {
                try {
                    InputStream in = new URL(item.getImageUrl()).openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    holder.itemView.post(() -> holder.productImage.setImageBitmap(bitmap));
                } catch (Exception e) {
                    holder.itemView.post(() ->
                            holder.productImage.setImageResource(R.drawable.ic_launcher_background));
                }
            });
        } else {
            holder.productImage.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView titleTxt, priceTxt;
        RatingBar ratingBar;

        TextView description ;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageView);
            titleTxt = itemView.findViewById(R.id.titletxt);
            priceTxt = itemView.findViewById(R.id.pricetxt);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            description=itemView.findViewById(R.id.descriptxt);
        }
    }

    public void updateData(List<ItemInfo> newList) {
        productList = newList;
        notifyDataSetChanged();
    }
}