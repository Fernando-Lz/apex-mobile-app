package com.example.apexapp;

public class Weapon {
    // Attributes
    private int id;
    private String name;
    private String imgUrl;

    // Constructor
    public Weapon(int id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
