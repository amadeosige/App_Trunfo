package com.example.myapplication.data.model;

public class Produto {

    Produto(String nome, float valor){
        nome = nome;
        valor = valor;
    }

    private String nome;
    private float valor;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor){
        this.valor = valor;
    }
}
