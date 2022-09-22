package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);
    }


    public void salvar (View v){

        boolean hasError = false;
        TextView nome = (TextView) findViewById(R.id.nome);
        TextView numero = (TextView) findViewById(R.id.numero);
        TextView rua = (TextView) findViewById(R.id.rua);
        TextView cep = (TextView) findViewById(R.id.cep);
        TextView complemento = (TextView) findViewById(R.id.complemento);
        TextView email = (TextView) findViewById(R.id.email);

        Intent telaErro = new Intent(this, erro_cadastro.class);

        if(nome.getText().toString().isEmpty()){
            hasError = true;
            telaErro.putExtra("nome", "nome");
        }

        if(numero.getText().toString().isEmpty()){
            hasError = true;
            telaErro.putExtra("numero", "numero");
        }

        if(rua.getText().toString().isEmpty()){
            hasError = true;
            telaErro.putExtra("rua", "rua");
        }

        if(cep.getText().toString().isEmpty()){
            hasError = true;
            telaErro.putExtra("cep", "cep");
        }

        if(complemento.getText().toString().isEmpty()){
            hasError = true;
            telaErro.putExtra("complemento", "nome");
        }

        if(email.getText().toString().isEmpty()){
            hasError = true;
            telaErro.putExtra("email", "email");
        }

        if(hasError)
            startActivity(telaErro);
        else{
            Intent telaSucesso = new Intent(this, sucesso_cadastro.class);
            telaSucesso.putExtra("nome", nome.getText().toString());
            startActivity(telaSucesso);
        }
    }
}