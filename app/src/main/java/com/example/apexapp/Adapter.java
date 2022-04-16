package com.example.apexapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> names;
    List<Integer> images;
    LayoutInflater inflater;

    public Adapter(Context context, List<String> names, List<Integer> images){
        this.names = names;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.legend_card_layout, parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            legendName = itemView.findViewById(R.id.TextViewLegend);
            legendImg = itemView.findViewById(R.id.ImageViewLegend);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}