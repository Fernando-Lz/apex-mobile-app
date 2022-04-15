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
import com.example.apexapp.R;

public class LoginFragment extends Fragment{
    View view;
    //
    TextView signUpTextView;
    EditText emailLoginEditText;
    EditText passwordLoginEditText;
    Button loginButton;
    Database db = new Database();

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
        emailLoginEditText = view.findViewById(R.id.usernameLoginEditText);
        passwordLoginEditText = view.findViewById(R.id.passwordLoginEditText);
        loginButton = view.findViewById(R.id.loginButton);
        //
        signUpSetOnClickListener(view);
        loginButtonSetOnClickListener(view);
    }

    // Listeners

    //Moves the user to the signup fragment
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

    private void loginButtonSetOnClickListener(View view){
        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailLoginEditText.getText().toString();
                String password = passwordLoginEditText.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(), "Fill the form", Toast.LENGTH_SHORT).show();
                }

                db.userExists(email, password);
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
