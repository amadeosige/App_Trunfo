package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void loginUser (View v){
        TextView user = (TextView) findViewById(R.id.userInput);
        TextView password = (TextView) findViewById(R.id.passwordInput);

        if(user.getText().toString().equals("Amadeo") && password.getText().toString().equals("1234")){
            Intent telaSucesso = new Intent(this, UserEdit.class);
            startActivity(telaSucesso);
        } else {
            Toast.makeText(MainActivity.this, "Usuário ou Senha Incorreta", Toast.LENGTH_SHORT).show();
        }
    }

}