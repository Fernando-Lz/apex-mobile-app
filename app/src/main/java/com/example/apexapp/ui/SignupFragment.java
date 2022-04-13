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
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.apexapp.R;
import com.example.apexapp.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment{

    View view;
    //
    TextView loginTextView;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_signup, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView(){
        loginTextView = view.findViewById(R.id.loginTextView);
        loginSetOnClickListener(view);
    }

    // Listeners

    private void loginSetOnClickListener(View view){
        loginTextView = view.findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController;
                //
                navController = NavHostFragment.findNavController(SignupFragment.this);
                navController.navigate(R.id.action_signup_to_login);

            }
        });
    }

    public void createNewUser() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
