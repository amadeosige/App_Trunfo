package com.example.myapplication;

public class Globals {

    private static Globals instance = new Globals();

    // Getter-Setters
    public static Globals getInstance() {
        return instance;
    }

    public static void setInstance(Globals instance) {
        Globals.instance = instance;
    }

    private String userName;


    private Globals() {

    }

    public String getUsername() {
        return userName;
    }

    public void setValue(String userName) {
        this.userName = userName;
    }
}
