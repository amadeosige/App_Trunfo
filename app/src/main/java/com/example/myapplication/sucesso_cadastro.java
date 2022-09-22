package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class sucesso_cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sucesso_cadastro);

        TextView nomeCadastro = (TextView) findViewById(R.id.nomeCadastroSucesso);

        Intent telaCadastro = getIntent();
        String nome = telaCadastro.getStringExtra("nome");

        nomeCadastro.setText("Olá " + nome);
    }

    public void sobre (View v){
        Intent telaSobre = new Intent(this, sobre.class);
        startActivity(telaSobre);
    }

}