package com.example.myapplication;

import java.util.ArrayList;

public class BattleCards {
    private Card oponent;
    private Card user;

    public BattleCards(Card oponent, Card user){
        this.oponent = oponent;
        this.user = user;
    }

    public Card getOponent() {
        return oponent;
    }

    public Card getUser() {
        return user;
    }
}
