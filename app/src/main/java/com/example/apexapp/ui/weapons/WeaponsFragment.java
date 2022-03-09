package com.example.apexapp.ui.weapons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apexapp.databinding.FragmentWeaponsBinding;

public class WeaponsFragment extends Fragment{

    private FragmentWeaponsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WeaponsViewModel legendsViewModel =
                new ViewModelProvider(this).get(WeaponsViewModel.class);

        binding = FragmentWeaponsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textWeapons;
//        legendsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
