package com.example.apexapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apexapp.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment{

    private FragmentSignupBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void createNewUser() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
