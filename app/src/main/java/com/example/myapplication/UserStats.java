package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

    public void teste(View v){
        TableLayout tableLayout = (TableLayout) findViewById(R.id.user_list);


        TableRow tr_head = new TableRow(UserStats.this);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT));


        TextView label_hello = new TextView(UserStats.this);
        label_hello.setText("AAAA"); // set the text
        label_hello.setTextColor(Color.WHITE);
        //label_hello.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 4f));
        label_hello.setWidth(300);
        label_hello.setHeight(50);

        TextView label_android = new TextView(UserStats.this);
        label_android.setText("FDFDFS"); // set the textlabel_android.setTextColor(Color.WHITE); // set the color
        //label_android.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 4f));
        label_android.setWidth(300);
        label_android.setHeight(50);

        TextView label_teste = new TextView(UserStats.this);
        label_teste.setText("BBBBBB"); // set the text
        label_teste.setWidth(300);
        label_teste.setHeight(50);

        tr_head.addView(label_hello);// add the column to the table row
        tr_head.addView(label_android);
        tr_head.addView(label_teste);


        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT));
    }

    private class GetJson extends AsyncTask<Void, Void, ArrayList<UserModel>> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(UserStats.this,
                    "Por favor Aguarde ...", "Recuperando Informações do Servidor...");
        }

        @Override
        protected ArrayList<UserModel> doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getUserJson("http://10.0.2.2:3000/user/listUser");
            //esse ip é o que o android identifica como o localhost do meu pc
        }

        @Override
        protected void onPostExecute(ArrayList<UserModel> user){

            ArrayList<UserModel> a = user;


            for (int i=0; i < user.size(); i++) {

                TableLayout tableLayout = (TableLayout) findViewById(R.id.user_list);

                TableRow tr_head = new TableRow(UserStats.this);
                tr_head.setBackgroundColor(Color.GRAY);
                tr_head.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT));


                TextView label_hello = new TextView(UserStats.this);
                label_hello.setText(user.get(i).getRanking()); // set the text
                label_hello.setTextColor(Color.WHITE);
                //label_hello.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 4f));
                label_hello.setWidth(300);
                label_hello.setHeight(50);

                TextView label_android = new TextView(UserStats.this);
                label_android.setText(user.get(i).getUserName()); // set the textlabel_android.setTextColor(Color.WHITE); // set the color
                //label_android.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 4f));
                label_android.setWidth(300);
                label_android.setHeight(50);

                TextView label_teste = new TextView(UserStats.this);
                label_teste.setText("BBBBBB"); // set the text
                label_teste.setWidth(300);
                label_teste.setHeight(50);

                tr_head.addView(label_hello);// add the column to the table row
                tr_head.addView(label_android);
                tr_head.addView(label_teste);


                tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT));

            }





            load.dismiss();
        }
    }
}