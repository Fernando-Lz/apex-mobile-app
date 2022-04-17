package com.example.apexapp.ui;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.apexapp.Database;
import com.example.apexapp.Model;
import com.example.apexapp.R;


public class SignupFragment extends Fragment{

    View view;
    //
    TextView loginTextView;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    Button signUpButton;

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
        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        signUpButton = view.findViewById(R.id.signUpButton);
        //
        loginSetOnClickListener(view);
        signUpButtonOnClickListener(view);
    }

    // Listeners

    //Moves the user to the login fragment when clicking the "Already have an account ?..." text
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

    private void signUpButtonOnClickListener(View view){
        signUpButton = view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(v -> {
            // Parse the data
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            // Checks that fields are not empty, if they are send a notification
            if (TextUtils.isEmpty(password) || TextUtils.isEmpty(username) || TextUtils.isEmpty(confirmPassword) && password.equals(confirmPassword )){
                Toast.makeText(getContext(), "Check your information", Toast.LENGTH_SHORT).show();
            } else {
                // Register the user to the database
                Database.registerUser(Model.activity, username, password);
                // Move to login fragment
                NavController navController;
                navController = NavHostFragment.findNavController(SignupFragment.this);
                navController.navigate(R.id.action_signup_to_login);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
