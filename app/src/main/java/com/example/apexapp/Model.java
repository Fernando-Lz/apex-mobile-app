package com.example.apexapp;

import android.app.Activity;
import android.content.Context;

import java.util.LinkedList;

public abstract class Model {
    public static Activity activity;

    public static void initialize(Activity anActivity)
    {
        // The Activity is the Context
        activity = anActivity;
    }//end initialize
    /*

    public static void User fetchStats() {

    }

    public static LinkedList<Weapon> showWeapons() {

    }

    public static LinkedList<Legend> showLegends() {

    }


    */

}
