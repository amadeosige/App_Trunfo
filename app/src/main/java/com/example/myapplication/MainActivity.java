package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void loginsss (View v){

        Intent telaLogin = new Intent(this, login.class);

        startActivity(telaLogin);
    }

    public void sobre_main (View v){
        Intent telaSobre = new Intent(this, sobre.class);
        startActivity(telaSobre);
    }

    public void cadastro (View v){
        Intent cadastro = new Intent(this, cadastro.class);
        startActivity(cadastro);
    }
}