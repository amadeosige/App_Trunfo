package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StartPlay extends AppCompatActivity {
    private ProgressDialog load;
    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_play);

        StartPlay.GetJson download = new StartPlay.GetJson();
        //Chama Async Task
        download.execute();

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

    private class GetJson extends AsyncTask<Void, Void, GameCards> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(StartPlay.this,
                    "Por favor Aguarde ...", "Preparando o jogo...");
        }

        @Override
        protected GameCards doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getGameCards("http://10.0.2.2:3000/cards/getCards");

            //pra testar localmente pelo emulador é esse ip aqui 10.0.2.2
            //pra fazer pelo celular msm tem que fazer o hotspot do notebook, conectar o celular e dar um ipconfig do notebook e ver qual ip dele na rede pra jogar no código
            //o que era quando eu testei na facul era esse: 192.168.137.1
        }

        @Override
        protected void onPostExecute(GameCards gameCards){

            if(gameCards == null){
                showToast("Não foi possível buscar as cartas para iniciar o jogo");
                load.dismiss();
                return;
            }

            Globals sharedData = Globals.getInstance();
            sharedData.setGame(gameCards);
            load.dismiss();
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }
}