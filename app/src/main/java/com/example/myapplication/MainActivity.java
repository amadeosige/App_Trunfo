package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ProgressDialog load;
    private String userName;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    private class GetJson extends AsyncTask<Void, Void, UserModel> {

        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(MainActivity.this,
                    "Por favor Aguarde ...", "Realizando login...");
        }

        @Override
        protected UserModel doInBackground(Void... params) {
            Utils util = new Utils();

            return util.getUserJson("http://10.0.2.2:3000/user/getUser?userName=" + userName);

            //pra testar localmente pelo emulador é esse ip aqui 10.0.2.2
            //pra fazer pelo celular msm tem que fazer o hotspot do notebook, conectar o celular e dar um ipconfig do notebook e ver qual ip dele na rede pra jogar no código
            //o que era quando eu testei na facul era esse: 192.168.137.1
        }

        @Override
        protected void onPostExecute(UserModel user){

            if(user == null){
                showToast("Usuário não encontrado");
                load.dismiss();
                return;
            }

            if(!(user.getPassword().equals(password))){
                showToast("Senha incorreta");
                load.dismiss();
                return;
            }

            Globals sharedData = Globals.getInstance();
            sharedData.setUsername(user.getUserName());
            sharedData.setUserImage(user.getImage());

            Intent telaSucesso = new Intent(MainActivity.this, UserEdit.class);
            startActivity(telaSucesso);
            load.dismiss();
        }
    }

    public void loginUser (View v){
        TextView user = (TextView) findViewById(R.id.userInput);
        TextView password = (TextView) findViewById(R.id.passwordInput);

        this.userName = user.getText().toString();
        this.password = password.getText().toString();

        MainActivity.GetJson download = new MainActivity.GetJson();
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

