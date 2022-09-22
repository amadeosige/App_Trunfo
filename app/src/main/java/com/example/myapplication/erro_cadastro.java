package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class erro_cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erro_cadastro);

        TextView errosCadastro = (TextView) findViewById(R.id.erros);

        Intent telaCadastro = getIntent();

        String nome = telaCadastro.getStringExtra("nome");
        String numero = telaCadastro.getStringExtra("numero");
        String rua = telaCadastro.getStringExtra("rua");
        String cep = telaCadastro.getStringExtra("cep");
        String complemento = telaCadastro.getStringExtra("complemento");
        String email = telaCadastro.getStringExtra("email");

        String errors = "";
        if(nome != null && !nome.isEmpty())
            errors +=  nome + " - ";

        if(numero != null && !numero.isEmpty())
            errors +=  numero + " - ";

        if(rua != null && !rua.isEmpty())
            errors +=  rua + " - ";

        if(cep != null && !cep.isEmpty())
            errors +=  cep + " - ";

        if(complemento != null && !complemento.isEmpty())
            errors +=  complemento + " - ";

        if(email != null && !email.isEmpty())
            errors +=  email + " - ";

        errosCadastro.setText(errors);
    }

    public void voltar (View v){
        Intent telaCadastro = new Intent(this, cadastro.class);
        startActivity(telaCadastro);
    }
}