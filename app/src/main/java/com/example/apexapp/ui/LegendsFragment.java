package com.example.apexapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apexapp.databinding.FragmentLegendsBinding;

public class LegendsFragment extends Fragment {

    private FragmentLegendsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLegendsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textLegends;
//        legendsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void showAllLegends() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}