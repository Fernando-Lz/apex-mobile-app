package com.example.apexapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apexapp.AdapterLegends;
import com.example.apexapp.AdapterWeapons;
import com.example.apexapp.BuildConfig;
import com.example.apexapp.Database;
import com.example.apexapp.Legend;
import com.example.apexapp.Model;
import com.example.apexapp.R;
import com.example.apexapp.Weapon;
import com.example.apexapp.databinding.FragmentWeaponsBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WeaponsFragment extends Fragment {

    View view;
    RecyclerView weaponsRecyclerView;
    AdapterWeapons adapter;
    //
    LinkedList<Weapon> weaponsList;
    List<String> weaponNames;
    List<Integer> weaponImages;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_weapons, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView() {
        //
        weaponsRecyclerView = view.findViewById(R.id.weaponsRecyclerView);
        weaponsList = Database.selectAllWeapons(Model.activity);
        weaponNames = new ArrayList<>();
        weaponImages = new ArrayList<>();
        // Saves the references of the legends images and the names of the legends to display them
        saveLegendsInfo();
        adapter = new AdapterWeapons(Model.activity, weaponNames, weaponImages);
        showAllWeapons();
    }

    public void saveLegendsInfo() {
        for (int i = 0; i < weaponsList.size(); i++) {
            Weapon currentWeapon = weaponsList.get(i);
            String weaponName = currentWeapon.getName();
            String weaponImg = weaponName.toLowerCase();
            //Replaces " " and "-" for nothing to match the names of the images
            weaponImg = weaponImg.replace(" ", "");
            weaponImg = weaponImg.replace("-", "");
            weaponImg = weaponImg.replace(".", "");
            // Obtains the reference to the image
            int image = getResources().getIdentifier(weaponImg, "drawable", BuildConfig.APPLICATION_ID);
            weaponNames.add(weaponName);
            weaponImages.add(image);
        }
    }

    public void showAllWeapons() {
        // Creates a component where items can be created in a two columns grid
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Model.activity, 2, GridLayoutManager.VERTICAL, false);
        weaponsRecyclerView.setLayoutManager(gridLayoutManager);
        weaponsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
