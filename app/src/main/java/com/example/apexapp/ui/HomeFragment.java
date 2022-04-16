package com.example.apexapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apexapp.Model;
import com.example.apexapp.R;
import com.example.apexapp.databinding.FragmentHomeBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    private RequestQueue queue;
    View view;
    TextView homeUsernameTextView;
    TextView homeUserLevel;
    ProgressBar homeProgressBar;
    ImageView homeBrRank;
    ImageView homeArenaRank;
    ImageView homeLegendImg;
    TextView homeStat1;
    TextView homeStat2;
    TextView homeStat3;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeView();
    }

    private void initializeView(){
        queue = Volley.newRequestQueue(Model.activity);
        homeUsernameTextView = view.findViewById(R.id.homeUsernameTextView);
        homeUserLevel = view.findViewById(R.id.homeUserLevel);
        homeProgressBar = view.findViewById(R.id.homeProgressBar);
        homeBrRank = view.findViewById(R.id.homeBrRank);
        homeArenaRank = view.findViewById(R.id.homeArenaRank);
        homeLegendImg = view.findViewById(R.id.homeLegendImg);
        homeStat1 = view.findViewById(R.id.homeStat1);
        homeStat2 = view.findViewById(R.id.homeStat2);
        homeStat3 = view.findViewById(R.id.homeStat3);
        //
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String username = sharedPref.getString("username", "StringentAbyss");
        StringRequest stringRequest = updateStats("StringentAbyss");
        queue.add(stringRequest);
    }

    public StringRequest updateStats(String username) {
        // Use volleyball to fetch teh data from the api
        String url = "https://api.mozambiquehe.re/bridge?version=5&platform=PC&player=" + username + "&auth=m8gv6XIif2okqcLoYEft";
        return new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject result = new JSONObject(response).getJSONObject("global");
                    String name = result.getString("name");
                    int level = result.getInt("level");
                    int progress = result.getInt("toNextLevelPercent");

                    homeUsernameTextView.setText(name);
                    homeUserLevel.setText("Lv. " + level);
                    homeProgressBar.setProgress(progress);

                }catch (JSONException e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        },
        new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}