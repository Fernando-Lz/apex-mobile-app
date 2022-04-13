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

import com.example.apexapp.MainActivity;
import com.example.apexapp.R;
import com.example.apexapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment{
    View view;
    //
    TextView signUpTextView;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView(){
        signUpTextView = view.findViewById(R.id.signUpTextView);
        signUpSetOnClickListener(view);
    }

    // Listeners

    private void signUpSetOnClickListener(View view){
        signUpTextView = view.findViewById(R.id.signUpTextView);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController;
                //
                navController = NavHostFragment.findNavController(LoginFragment.this);
                navController.navigate(R.id.action_login_to_signup);

            }
        });
    }

    public void verifyCredentials() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
