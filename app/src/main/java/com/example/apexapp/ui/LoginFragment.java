package com.example.apexapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.apexapp.Database;
import com.example.apexapp.MainActivity;
import com.example.apexapp.Model;
import com.example.apexapp.R;

public class LoginFragment extends Fragment{
    View view;
    //
    TextView signUpTextView;
    EditText usernameLoginEditText;
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
        usernameLoginEditText = view.findViewById(R.id.usernameLoginEditText);
        passwordLoginEditText = view.findViewById(R.id.passwordLoginEditText);
        loginButton = view.findViewById(R.id.loginButton);
        //
        //
        signUpSetOnClickListener(view);
        loginButtonSetOnClickListener(view);
    }

    // Listeners

    //Moves the user to the signup fragment when clicks the "New user?... text"
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

    // Verifies that the user exists in the database and moves it to the home fragment
    private void loginButtonSetOnClickListener(View view){
        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLoginEditText.getText().toString();
                String password = passwordLoginEditText.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    // Sends a notification to tell the user the data is incomplete
                    Toast.makeText(getContext(), "Fill the form", Toast.LENGTH_SHORT).show();
                }

                if (db.userExists(Model.activity, username, password)){
                    Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    // Saves the name of the user in local storage to use it in the Home fragment
                    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", username);
                    editor.apply();
                    // Redirects to home
                    NavController navController;
                    navController = NavHostFragment.findNavController(LoginFragment.this);
                    navController.navigate(R.id.action_login_to_nav_home);
                } else {
                    Toast.makeText(getContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
