package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
    //t

    public void startPlay(View v){
        Intent chooseCardsScreen = new Intent(this, ChooseCards.class);
        startActivity(chooseCardsScreen);
    }
}