package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_stats:
                        Toast.makeText(MainActivity.this, "Estatisticas", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_edit:
                        Toast.makeText(MainActivity.this, "Editar", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.action_play:
                        Toast.makeText(MainActivity.this, "Jogar", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

}