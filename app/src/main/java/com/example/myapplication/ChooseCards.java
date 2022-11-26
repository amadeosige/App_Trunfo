package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ChooseCards extends AppCompatActivity {
    int quantity = 0;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_card);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            quantity = extras.getInt("quantity");

            Globals sharedData = Globals.getInstance();
            GameCards game = sharedData.getGame();


        }


    }
}