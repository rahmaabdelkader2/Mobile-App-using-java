package com.example.lab7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductGlide extends RecyclerView.Adapter<ProductGlide.ProductViewHolder> {

    private List<ItemInfo> productList;

    public ProductGlide() {
        this.productList = new ArrayList<>();
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

        // Load image with Glide
        String imageUrl = item.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void updateData(List<ItemInfo> newList) {
        productList = newList;
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView titleTxt, priceTxt, description;
        RatingBar ratingBar;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.imageView);
            titleTxt = itemView.findViewById(R.id.titletxt);
            priceTxt = itemView.findViewById(R.id.pricetxt);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            description = itemView.findViewById(R.id.descriptxt);
        }
    }
}