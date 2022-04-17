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

public class AdapterWeapons extends RecyclerView.Adapter<AdapterWeapons.ViewHolder> {

    List<String> names;
    List<Integer> images;
    LayoutInflater inflater;

    public AdapterWeapons(Context context, List<String> names, List<Integer> images){
        this.names = names;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    // Inflates the card of the correspondent legend
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.weapon_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWeapons.ViewHolder holder, int position) {
        // Sets the name and img of the card component based on the current legend of the iteration
        holder.weaponName.setText(names.get(position));
        holder.weaponImg.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView weaponName;
        ImageView weaponImg;

        // Selects the id of the item that will change in the card component
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weaponName = itemView.findViewById(R.id.TextViewWeapon);
            weaponImg = itemView.findViewById(R.id.ImageViewWeapon);
        }
    }
}