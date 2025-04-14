package com.example.lab6_1;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DuckAdaptor extends RecyclerView.Adapter<DuckAdaptor.DuckViewHolder> {
    private static final String TAG = "DuckAdaptor";
    private List<Ducks> ducksList;

    public DuckAdaptor(List<Ducks> ducksList) {
        this.ducksList = ducksList;
    }

    @NonNull
    @Override
    public DuckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Creating new view holder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerow, parent, false);
        return new DuckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuckViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Binding view holder at position " + position);
        Ducks currentDuck = ducksList.get(position);

        holder.imageView.setImageResource(currentDuck.getImageId());
        holder.titleTextView.setText(currentDuck.getTitle());
        holder.descriptionTextView.setText(currentDuck.getDescription());
    }

    @Override
    public int getItemCount() {
        return ducksList.size();
    }

    public static class DuckViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView descriptionTextView;

        public DuckViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titletxt);
            descriptionTextView = itemView.findViewById(R.id.descriptiontxt);
        }
    }
}