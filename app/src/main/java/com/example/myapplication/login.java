package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loginUser (View v){
        TextView usuario = (TextView) findViewById(R.id.usuario);
        TextView senha = (TextView) findViewById(R.id.senha);

        if(usuario.getText().toString().equals("Amadeo") && senha.getText().toString().equals("1234")){
            Intent telaSucesso = new Intent(this, sucesso_login.class);
            startActivity(telaSucesso);
        } else {
            Intent telaErro = new Intent(this, erro_login.class);
            startActivity(telaErro);
        }
    }
}