package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StartPlay extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_play);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_stats:
                        Intent statsScreen = new Intent(StartPlay.this, UserStats.class);
                        startActivity(statsScreen);
                        break;

                    case R.id.action_edit:
                        Intent editScreen = new Intent(StartPlay.this, UserEdit.class);
                        startActivity(editScreen);
                        break;

                    case R.id.action_play:
                        break;
                }
                return true;
            }
        });
    }

    public void startPlay(View v){
        int id = v.getId();
        int quantity = 0;

        switch (id){
            case R.id.button7: {
                quantity = 1;
                break;
            }
            case R.id.button8: {
                quantity = 3;
                break;
            }
            case R.id.button9: {
                quantity = 5;
                break;
            }
            default:
                break;
        }


        Intent i = new Intent(StartPlay.this, ChooseCards.class);
        i.putExtra("quantity",quantity);
        startActivity(i);
    }
}