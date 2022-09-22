package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class erro_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erro_login_tela);
    }

    public void voltar (View v){
        Intent telaLogin = new Intent(this, login.class);
        startActivity(telaLogin);
    }
}