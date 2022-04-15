package com.example.apexapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
        long result = db.insert("t_user", null, values);
        // If results = -1 means that the user already exists
        if (result < 0) {
            Toast.makeText(Model.activity, "User already exists", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Model.activity, "User created", Toast.LENGTH_SHORT).show();
        }


    }

    public void userExists(String username, String password){
    }
}
