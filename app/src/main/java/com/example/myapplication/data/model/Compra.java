package com.example.myapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import java.util.Date;

public class Compra implements Parcelable {
    private int id;
    private String nome;
    private Date dataHora;
    private String conteudo;

    public Compra() {

    }

    public static final class  LembreteEntry implements BaseColumns {
        public static final String TABLE_NAME = "compra";

        public static final String COLUNA_DESCRICAO_LEMBRETE = "descricao";
        public static final String COLUNA_DATA_HORA = "data_hora";
        public static final String COLUNA_CONTEUDO = "conteudo";
    }

    public Compra(Parcel in) {
        id = in.readInt();
        nome = in.readString();
    }

    public static final Creator<Compra> CREATOR = new Creator<Compra>() {
        @Override
        public Compra createFromParcel(Parcel in) {
            return new Compra(in);
        }

        @Override
        public Compra[] newArray(int size) {
            return new Compra[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nome);
    }
}
