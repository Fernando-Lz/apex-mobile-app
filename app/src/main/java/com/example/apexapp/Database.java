package com.example.apexapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

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

    public boolean userExists(Context context, String username, String password){
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        ContentValues values;
        Cursor cursor;
        String table = "user";
        String selection = "username = '" + username + "' AND password = '" + password + "';";
        String sortOrder = "";
        String[] selectionArgs = {};
        String [] columns = {
                "userId",
                "username",
                "password"
        };
        dbHelper = new DatabaseHelper(context);
        // set the DB in read mode
        db = dbHelper.getReadableDatabase();
        cursor = db.query(table,    // The table to query
                columns,                        // The array of columns to return (pass null to get all)
                selection,                      // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
                null,                  // don't group the rows
                null,                   // don't filter by row groups
                sortOrder   );
        // If result >1 means that the user already exists
        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    public static void saveStats(Context context, String username, int level, String arenaRank, String battleRoyaleRank){
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        ContentValues values;
        //
        dbHelper = new DatabaseHelper(context);
        // DB in write mode
        db = dbHelper.getWritableDatabase();
        //
        values = new ContentValues();
        values.put("level", level);
        values.put("arenaRank", arenaRank);
        values.put("battleRoyaleRank", battleRoyaleRank);
        // Insert the row
        long result = db.update("user", values, "username = '" + username + "'", null);
        // If result>1 means that the user information was updated
        if (result > 0) {
            Toast.makeText(Model.activity, "User information updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Model.activity, "User could not be created", Toast.LENGTH_SHORT).show();
        }
    }


    public static LinkedList<Legend> selectAllLegends(Context context){
        LinkedList<Legend> result;
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        Cursor cursor;
        //
        Legend legend;
        int legendId;
        String legendName;
        String legendImgUrl;
        //
        String table = "legend";
        String selection = "";
        String sortOrder = "legendId ASC";
        String[] selectionArgs = {};
        String [] columns = {
                "legendId",
                "legendName",
                "legendImgUrl"
        };
        //
        dbHelper = new DatabaseHelper(context);
        // set the DB in read mode
        db = dbHelper.getReadableDatabase();

        //make the query and obtain the result list (cursor)
        cursor = db.query(
                table,    // The table to query
                columns,                        // The array of columns to return (pass null to get all)
                selection,                      // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
                null,                  // don't group the rows
                null,                   // don't filter by row groups
                sortOrder                       // The sort order
        );

        result = new LinkedList<Legend>();

        // adds each row to the list of Legends
        while(cursor.moveToNext())
        {
            legendId = cursor.getInt(cursor.getColumnIndexOrThrow("legendId"));
            legendName = cursor.getString(cursor.getColumnIndexOrThrow("legendName"));
            legendImgUrl = cursor.getString(cursor.getColumnIndexOrThrow("legendImgUrl"));
            legend = new Legend(legendId, legendName, legendImgUrl);
            result.add(legend);
        }

        // close the cursor
        cursor.close();
        return result;
    }

    public static LinkedList<Weapon> selectAllWeapons(Context context){
        LinkedList<Weapon> result;
        DatabaseHelper dbHelper;
        SQLiteDatabase db;
        Cursor cursor;
        //
        Weapon weapon;
        int weaponId;
        String weaponName;
        String weaponImgUrl;
        //
        String table = "weapon";
        String selection = "";
        String sortOrder = "weaponId ASC";
        String[] selectionArgs = {};
        String [] columns = {
                "weaponId",
                "weaponName",
                "weaponImgUrl"
        };
        //
        dbHelper = new DatabaseHelper(context);
        // set the DB in read mode
        db = dbHelper.getReadableDatabase();

        //make the query and obtain the result list (cursor)
        cursor = db.query(
                table,    // The table to query
                columns,                        // The array of columns to return (pass null to get all)
                selection,                      // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
                null,                  // don't group the rows
                null,                   // don't filter by row groups
                sortOrder                       // The sort order
        );

        result = new LinkedList<Weapon>();

        // adds each row to the list of Weapons
        while(cursor.moveToNext())
        {
            weaponId = cursor.getInt(cursor.getColumnIndexOrThrow("weaponId"));
            weaponName = cursor.getString(cursor.getColumnIndexOrThrow("weaponName"));
            weaponImgUrl = cursor.getString(cursor.getColumnIndexOrThrow("weaponImgUrl"));
            weapon = new Weapon(weaponId, weaponName, weaponImgUrl);
            result.add(weapon);
        }

        // close the cursor
        cursor.close();
        return result;
    }


    // The methods below are used when the application starts, they use the json files in the assets folder
    // and the loadFromJSONAsset method.
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

            // Reads the JSONArray and inserts each legend in the array to the db
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject legend = jsonArray.getJSONObject(i);
                String legendId = legend.getString("legendId");
                String legendName = legend.getString("legendName");
                String legendImgUrl = legend.getString("legendImgUrl");

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

            // Reads the JSONArray and inserts each weapon in the array to the db
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject weapon = jsonArray.getJSONObject(i);
                String weaponId = weapon.getString("weaponId");
                String weaponName = weapon.getString("weaponName");
                String weaponImgUrl = weapon.getString("weaponImgUrl");

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
        String json;
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
