package com.example.apexapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterLegends extends RecyclerView.Adapter<AdapterLegends.ViewHolder> {

    List<String> names;
    List<Integer> images;
    LayoutInflater inflater;

    public AdapterLegends(Context context, List<String> names, List<Integer> images){
        this.names = names;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    // Inflates the card of the correspondent legend
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.legend_card_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Sets the name and img of the card component based on the current legend of the iteration
        holder.legendName.setText(names.get(position));
        holder.legendImg.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView legendName;
        ImageView legendImg;

        // Selects the id of the item that will change in the card component
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            legendName = itemView.findViewById(R.id.TextViewLegend);
            legendImg = itemView.findViewById(R.id.ImageViewLegend);
        }
    }
}