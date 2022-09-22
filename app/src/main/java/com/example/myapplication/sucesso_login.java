package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sucesso_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sucesso_login_tela);
    }

    public void login(View v){
        Intent telaLogin = new Intent(this, login.class);
        startActivity(telaLogin);
    }

    public void sobre (View v){
        Intent telaSobre = new Intent(this, sobre.class);
        startActivity(telaSobre);
    }

}