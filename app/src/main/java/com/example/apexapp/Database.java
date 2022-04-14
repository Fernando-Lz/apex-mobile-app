package com.example.apexapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Database {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public boolean registerUser(String username, String password) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        data.put("platform", "PC");
        db.collection("user").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             if (task.isSuccessful()){
                 System.out.println("Usuario registrado correctamente");
             } else {
                 System.out.println("Usuario no pudo ser registrado");
             }
            }
        });
        return true;
    }
    /*
    public static boolean userExists() {

    }

    public static void updateStats() {
    }

    public static LinkedList<Weapon> selectAllWeapons() {

    }

    public static LinkedList<Legend> selectAllLegends() {

    }


    */

}
