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
    private GameCards game;



    private Globals() {

    }


    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public GameCards getGame() {
        return game;
    }

    public void setGame(GameCards game) {
        this.game = game;
    }
}
