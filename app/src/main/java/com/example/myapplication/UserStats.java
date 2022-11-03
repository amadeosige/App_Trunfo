package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class UserStats extends AppCompatActivity {
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_stats);

        GetJson download = new GetJson();
        //Chama Async Task
        download.execute();


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_stats:
                        break;

                    case R.id.action_edit:
                        Intent editScreen = new Intent(UserStats.this, UserEdit.class);
                        startActivity(editScreen);
                        break;

                    case R.id.action_play:
                        Intent playScreen = new Intent(UserStats.this, StartPlay.class);
                        startActivity(playScreen);
                        break;
                }
                return true;
            }
        });
    }

    private class GetJson extends AsyncTask<Void, Void, ArrayList<UserModel>> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(UserStats.this,
                    "Por favor Aguarde ...", "Buscando Usuários...");
        }

        @Override
        protected ArrayList<UserModel> doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getUserJsonList("http://10.0.2.2:3000/user/listUser");
            //esse ip é o que o android identifica como o localhost do meu pc
        }

        @Override
        protected void onPostExecute(ArrayList<UserModel> user){

            ArrayList<UserModel> a = user;


            for (int i=0; i < user.size(); i++) {

                TableLayout tableLayout = (TableLayout) findViewById(R.id.user_list);

                TableRow tableRow = new TableRow(UserStats.this);
                tableRow.setBackgroundColor(Color.GRAY);
                tableRow.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tableRow.setPadding(0,10,0,10);



                TextView label_hello = new TextView(UserStats.this);
                label_hello.setText(user.get(i).getRanking()); // set the text
                label_hello.setTextColor(Color.WHITE);
                label_hello.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 4f));
                label_hello.setGravity(Gravity.CENTER);
                label_hello.setHeight(100);


                ImageView imageViewAVatar = new ImageView(UserStats.this);
                imageViewAVatar.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT,4f));
                byte[] decodedString = Base64.decode(user.get(i).getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageViewAVatar.getLayoutParams().height = 100;
                imageViewAVatar.getLayoutParams().width = 100;
                TableRow.LayoutParams params = (TableRow.LayoutParams) imageViewAVatar.getLayoutParams();
                params.gravity = Gravity.CENTER;
                imageViewAVatar.setLayoutParams(params);
                imageViewAVatar.setImageBitmap(decodedByte);


                TextView label_teste = new TextView(UserStats.this);
                label_teste.setText(user.get(i).getUserName()); // set the text
                label_teste.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 4f));
                label_teste.setGravity(Gravity.CENTER);
                label_teste.setHeight(100);


                tableRow.addView(label_hello);
                tableRow.addView(imageViewAVatar);
                tableRow.addView(label_teste);

                tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT));

            }
            load.dismiss();
        }
    }
}