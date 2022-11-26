package com.example.myapplication;

import java.util.ArrayList;

public class GameCards {
    ArrayList<Card> user = new ArrayList <Card> ();
    ArrayList <Card> oponent = new ArrayList <Card> ();

    public GameCards(ArrayList<Card> user, ArrayList <Card> oponent){
        this.user =  user;
        this.oponent = oponent;
    }

    public ArrayList<Card> getOponent() {
        return oponent;
    }

    public ArrayList<Card> getUser() {
        return user;
    }

    public void setOponent(ArrayList<Card> oponent) {
        this.oponent = oponent;
    }

    public void setUser(ArrayList<Card> user) {
        this.user = user;
    }
}
