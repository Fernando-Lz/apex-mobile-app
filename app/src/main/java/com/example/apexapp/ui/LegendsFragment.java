package com.example.apexapp.ui;


import android.content.ClipData;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apexapp.AdapterLegends;
import com.example.apexapp.BuildConfig;
import com.example.apexapp.Database;
import com.example.apexapp.Legend;
import com.example.apexapp.Model;
import com.example.apexapp.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LegendsFragment extends Fragment {

    View view;
    RecyclerView legendsRecyclerView;
    AdapterLegends adapter;
    //
    LinkedList<Legend> legendsList;
    List<String> legendNames;
    List<Integer> legendImages;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_legends, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        //
        legendsRecyclerView = view.findViewById(R.id.legendsRecyclerView);
        legendsList = Database.selectAllLegends(Model.activity);
        legendNames = new ArrayList<>();
        legendImages = new ArrayList<>();
        // Saves the references of the legends images and the names of the legends to display them
        saveLegendsInfo();
        adapter = new AdapterLegends(Model.activity, legendNames, legendImages);
        showAllLegends();
    }

    public void saveLegendsInfo() {
        for (int i = 0; i < legendsList.size(); i++) {
            Legend currentLegend = legendsList.get(i);
            String legendName = currentLegend.getName();
            String legendImg = legendName.toLowerCase();
            // Unique exception in legends names
            if (legendName.equals("Mad Maggie"))
                legendImg = "mad_maggie";
            // Obtains the reference to the image
            int image = getResources().getIdentifier(legendImg, "drawable", BuildConfig.APPLICATION_ID);
            legendNames.add(legendName);
            legendImages.add(image);
        }
    }

    public void showAllLegends(){
        // Creates a component where items can be created in a two columns grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Model.activity, 2, GridLayoutManager.VERTICAL, false);
        legendsRecyclerView.setLayoutManager(gridLayoutManager);
        legendsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}