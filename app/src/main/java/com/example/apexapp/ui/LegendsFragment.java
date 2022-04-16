package com.example.apexapp.ui;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apexapp.Adapter;
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
    Adapter adapter;
    //
    LinkedList<Legend> legendsList;
    List<String> legendNames;
    List<Integer> legendImages;
    int[] arr;

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
        // Creates a component where items can be created in a two columns grid
        saveLegendsInfo();
        adapter = new Adapter(Model.activity, legendNames, legendImages);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Model.activity, 2, GridLayoutManager.VERTICAL, false);
        legendsRecyclerView.setLayoutManager(gridLayoutManager);
        legendsRecyclerView.setAdapter(adapter);
        showAllLegends();
    }

    public void saveLegendsInfo() {
        for (int i = 0; i < legendsList.size(); i++) {
            Legend currentLegend = legendsList.get(i);
            String legendName = currentLegend.getName();
//            Integer.valueOf(("R.drawable." + legendName))
            legendNames.add(legendName);
            legendImages.add(R.drawable.bloodhound);
        }
    }

    public void showAllLegends(){

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // Creates the card

    public void createLegendCard(String legendName) {

    }
}