package com.example.apexapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "apex.db";
    public static final String TABLE_USER = "t_user";
    public static final String TABLE_LEGEND = "t_legend";
    public static final String TABLE_WEAPON = "t_weapon";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USER + "(" +
                "userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE NOT NULL," +
                "password TEXT NOT NULL," +
                "platform TEXT NOT NULL," +
                "level INTEGER," +
                "arenaRank TEXT," +
                "battleRoyaleRank TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_LEGEND + "(" +
                "legendId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "legendName TEXT NOT NULL," +
                "legendImgUrl TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + TABLE_WEAPON + "(" +
                "weaponId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "weaponName TEXT NOT NULL," +
                "weaponImgUrl TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_USER);
        db.execSQL("DROP TABLE " + TABLE_LEGEND);
        db.execSQL("DROP TABLE " + TABLE_WEAPON);
        onCreate(db);
    }
}