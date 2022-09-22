package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sobre_tela);
    }

    public void boasVindas (View v){
        Intent telaBoasVindas = new Intent(this, MainActivity.class);
        startActivity(telaBoasVindas);
    }

    public void ftec(View arg0) {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("https://www.ftec.com.br/"));
        startActivity(viewIntent);
    }
}