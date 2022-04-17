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
import com.example.apexapp.Database;
import com.example.apexapp.Model;
import com.example.apexapp.R;
import com.example.apexapp.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    // Queue for the api call
    private RequestQueue queue;
    View view;
    // items that will be updated when fetching the data
    TextView homeUsernameTextView;
    TextView homeUserLevel;
    ProgressBar homeProgressBar;
    ImageView homeBrRank;
    ImageView homeArenaRank;
    ImageView homeLegendImg;
    // Array of stats, usually there are three stats in a player, but they can set two, one or none
    // making it an array prevents errors as there is no information for x item
    TextView homeStats[];

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
        // Array of the three stats in the ui
        homeStats = new TextView[]{view.findViewById(R.id.homeStat1), view.findViewById(R.id.homeStat2), view.findViewById(R.id.homeStat3)};
        // Retrieves the username from local storage, if there is no username, it displays the
        // information of StringentAbyss as default
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String username = sharedPref.getString("username", "StringentAbyss");
        // Retrieves the information from the api and adds it to the queue, it must be in the queue
        // in order to finish the process once all the data is fetchec, if not it will not end
        StringRequest stringRequest = updateStats(username);
        queue.add(stringRequest);
    }

    public StringRequest updateStats(String username) {
        // Use volleyball to fetch teh data from the api
        // The url is generated based on the username
        String url = "https://api.mozambiquehe.re/bridge?version=5&platform=PC&player=" + username + "&auth=m8gv6XIif2okqcLoYEft";
        return new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // Stores the result as a JSONObject in order to manipulate it easily
                    JSONObject result = new JSONObject(response).getJSONObject("global");
                    // BASIC STATS
                    String name = result.getString("name");
                    int level = result.getInt("level");
                    int progress = result.getInt("toNextLevelPercent");
                    // Set the stats in the ui
                    homeUsernameTextView.setText(name);
                    homeUserLevel.setText("Lv. " + level);
                    homeProgressBar.setProgress(progress);

                    //RANK STATS
                    String rankBrImg = result.getJSONObject("rank").getString("rankImg");
                    String rankBr = result.getJSONObject("rank").getString("rankName") + " " + result.getJSONObject("rank").getString("rankDiv");
                    String rankArenaImg = result.getJSONObject("arena").getString("rankImg");
                    String rankArena = result.getJSONObject("arena").getString("rankName") + " " + result.getJSONObject("arena").getString("rankDiv");
                    // Sets the rank images into the id of the ImageView form the ui
                    Picasso.get().load(rankBrImg).into(homeBrRank);
                    Picasso.get().load(rankArenaImg).into(homeArenaRank);

                    //LAST LEGEND USED STATS
                    JSONObject legendUsed = new JSONObject(response).getJSONObject("legends").getJSONObject("selected");
                    String lastLegendImg = legendUsed.getJSONObject("ImgAssets").getString("icon");
                    // Sets the last legend used image into the id of the ImageView form the ui
                    Picasso.get().load(lastLegendImg).into(homeLegendImg);

                    // Converts the stats into an array, if iterates based on the stats that the user
                    // is displaying in the game, it can go from 0 to 3
                    JSONArray trackers = legendUsed.getJSONArray("data");
                    for (int i = 0; i < trackers.length(); i++) {
                        String trakcer_name = trackers.getJSONObject(i).getString("name");
                        String trakcer_stat = trackers.getJSONObject(i).getString("value");
                        homeStats[i].setText(trakcer_name + ": " + trakcer_stat);
                    }

                    // Saves the data in the database
                    Database.saveStats(Model.activity, username, level, rankArena, rankBr);


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