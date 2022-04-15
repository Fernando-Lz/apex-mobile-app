package com.example.apexapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Database{
    // Creates the database instance

    public static void registerUser(Context context, String username, String password) {
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        ContentValues values;
        //
        dbHelper = new DatabaseHelper(context);
        // DB in write mode
        db = dbHelper.getWritableDatabase();
        //
        values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("platform", "PC");
        // Insert the row
        long result = db.insert("user", null, values);
        // If results = -1 means that the user already exists
        if (result < 0) {
            Toast.makeText(Model.activity, "User already exists", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Model.activity, "User created", Toast.LENGTH_SHORT).show();
        }
    }

    public void userExists(String username, String password){
    }

    public static void insertLegends(Context context) {
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        ContentValues values;
        //
        dbHelper = new DatabaseHelper(context);
        // DB in write mode
        db = dbHelper.getWritableDatabase();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("legends"));
            JSONArray jsonArray = obj.getJSONArray("legends");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo_inside = jsonArray.getJSONObject(i);
                String legendId = jo_inside.getString("legendId");
                String legendName = jo_inside.getString("legendName");
                String legendImgUrl = jo_inside.getString("legendImgUrl");

                values = new ContentValues();
                values.put("legendId", legendId);
                values.put("legendName", legendName);
                values.put("legendImgUrl", legendImgUrl);
                // Insert the row
                db.insert("legend", null, values);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void insertWeapons(Context context) {
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        ContentValues values;
        //
        dbHelper = new DatabaseHelper(context);
        // DB in write mode
        db = dbHelper.getWritableDatabase();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset("weapons"));
            JSONArray jsonArray = obj.getJSONArray("weapons");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo_inside = jsonArray.getJSONObject(i);
                String weaponId = jo_inside.getString("weaponId");
                String weaponName = jo_inside.getString("weaponName");
                String weaponImgUrl = jo_inside.getString("weaponImgUrl");

                values = new ContentValues();
                values.put("weaponId", weaponId);
                values.put("weaponName", weaponName);
                values.put("weaponImgUrl", weaponImgUrl);
                // Insert the row
                db.insert("weapon", null, values);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // Reads the json file in the assets folder and converts it into a string
    public static String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = Model.activity.getAssets().open(filename + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
