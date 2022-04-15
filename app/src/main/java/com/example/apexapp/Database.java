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
        db.insert("t_user", null, values);
        Toast.makeText(Model.activity, "User created", Toast.LENGTH_SHORT).show();

    }

    public void userExists(String username, String password){
    }
}
