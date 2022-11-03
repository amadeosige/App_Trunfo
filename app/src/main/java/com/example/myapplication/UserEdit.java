package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserEdit extends AppCompatActivity {
    private ProgressDialog load;
    private String originalUserName;
    private String ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit);

        UserEdit.GetUser download = new UserEdit.GetUser();
        //Chama Async Task
        download.execute();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_stats:
                        Intent statsScreen = new Intent(UserEdit.this, UserStats.class);
                        startActivity(statsScreen);
                        break;

                    case R.id.action_edit:
                        break;

                    case R.id.action_play:
                        Intent playScreen = new Intent(UserEdit.this, StartPlay.class);
                        startActivity(playScreen);
                        break;
                }
                return true;
            }
        });

    }


    private class GetUser extends AsyncTask<Void, Void, UserModel> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(UserEdit.this,
                    "Por favor Aguarde ...", "Editando usuário...");
        }

        @Override
        protected UserModel doInBackground(Void... params) {
            Utils util = new Utils();
            Globals sharedData = Globals.getInstance();
            String userName = sharedData.getUsername();

            return util.getUserJson("http://10.0.2.2:3000/user/getUser?userName=" + userName);
            //esse ip é o que o android identifica como o localhost do meu pc
        }

        @Override
        protected void onPostExecute(UserModel user){

            originalUserName = user.getUserName();
            ranking = user.getRanking();

            ImageView userImage = (ImageView) findViewById(R.id.imageView);
            byte[] decodedString = Base64.decode(user.getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            userImage.setImageBitmap(decodedByte);

            TextView userName = (TextView) findViewById(R.id.editTextTextPassword);
            userName.setText(user.getPassword()); // set the text

            TextView password = (TextView) findViewById(R.id.editTextTextPersonName);
            password.setText(user.getUserName()); // set the text

            load.dismiss();
        }
    }

    private class EditUser extends AsyncTask<Void, Void, UserModel> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(UserEdit.this,
                    "Por favor Aguarde ...", "Editando usuário...");
        }

        @Override
        protected UserModel doInBackground(Void... params) {
            Utils util = new Utils();

            TextView user = (TextView) findViewById(R.id.editTextTextPersonName);
            TextView password = (TextView) findViewById(R.id.editTextTextPassword);
            String newUserName = user.getText().toString();
            String newPassword = password.getText().toString();

            return util.getUserJson("http://10.0.2.2:3000/user/editUser?" +
                    "originalUserName=" + originalUserName +
                    "&userName=" + newUserName +

                    "&password=" + newPassword +
                    "&ranking=" + ranking);
        }

        @Override
        protected void onPostExecute(UserModel user){

            if(user == null){
                showToast("Erro ao editar usuário");
                load.dismiss();
                return;
            }

            originalUserName = user.getUserName();
            Globals sharedData = Globals.getInstance();
            sharedData.setValue(originalUserName);

            ranking = user.getRanking();

            TextView userName = (TextView) findViewById(R.id.editTextTextPassword);
            userName.setText(user.getPassword()); // set the text

            TextView password = (TextView) findViewById(R.id.editTextTextPersonName);
            password.setText(user.getUserName()); // set the text

            showToast("Usuário editado com sucesso");

            load.dismiss();
        }
    }


    public void editUser (View v){
        UserEdit.EditUser download = new UserEdit.EditUser();
        //Chama Async Task
        download.execute();
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
        toast.show();
    }
}