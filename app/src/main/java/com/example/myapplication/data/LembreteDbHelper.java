package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.data.model.Compra;
import com.example.myapplication.uteis.Util;

import java.util.ArrayList;
import java.util.List;

public class LembreteDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "compras.db";

    private static final int DATABASE_VERSION = 1;

    // Constructor
    public LembreteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE "
                + Compra.LembreteEntry.TABLE_NAME + " (" +
                Compra.LembreteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Compra.LembreteEntry.COLUNA_DESCRICAO_LEMBRETE + " TEXT NOT NULL, " +
                Compra.LembreteEntry.COLUNA_CONTEUDO + " TEXT NOT NULL, " +
                Compra.LembreteEntry.COLUNA_DATA_HORA + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Compra.LembreteEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long inserirLembrete(String lembrete, String conteudo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Compra.LembreteEntry.COLUNA_DESCRICAO_LEMBRETE, lembrete);
        values.put(Compra.LembreteEntry.COLUNA_CONTEUDO, conteudo);
        long id = db.insert(Compra.LembreteEntry.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public int editarLembrete(Compra lembrete) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Compra.LembreteEntry.COLUNA_DESCRICAO_LEMBRETE, lembrete.getNome());
        values.put(Compra.LembreteEntry.COLUNA_CONTEUDO, lembrete.getConteudo());

        return db.update(Compra.LembreteEntry.TABLE_NAME, values, Compra.LembreteEntry._ID + " = ?",
                new String[]{String.valueOf(lembrete.getId())});
    }

    public void deletarLembrete(Compra lembrete) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Compra.LembreteEntry.TABLE_NAME, Compra.LembreteEntry._ID + " = ?",
                new String[]{String.valueOf(lembrete.getId())});
        db.close();
    }

    public Compra getLembrete(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Compra.LembreteEntry.TABLE_NAME,
                new String[]{Compra.LembreteEntry._ID, Compra.LembreteEntry.COLUNA_DESCRICAO_LEMBRETE,Compra.LembreteEntry.COLUNA_CONTEUDO,
                        Compra.LembreteEntry.COLUNA_DATA_HORA},
                Compra.LembreteEntry._ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Compra lembrete = new Compra();
        lembrete.setId(cursor.getInt(cursor.getColumnIndex(Compra.LembreteEntry._ID)));
        lembrete.setNome(cursor.getString(cursor.getColumnIndex(Compra.LembreteEntry.COLUNA_DESCRICAO_LEMBRETE)));
        lembrete.setConteudo(cursor.getString(cursor.getColumnIndex(Compra.LembreteEntry.COLUNA_CONTEUDO)));
        lembrete.setDataHora(Util.strToDateTime(cursor.getString(cursor.getColumnIndex(Compra.LembreteEntry.COLUNA_DATA_HORA))));

        cursor.close();

        return lembrete;
    }

    public List<Compra> getAllLembretes() {
        List<Compra> lembretes = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + Compra.LembreteEntry.TABLE_NAME + " ORDER BY " +
                Compra.LembreteEntry.COLUNA_DATA_HORA + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Compra lembrete = new Compra();
                lembrete.setId(cursor.getInt(cursor.getColumnIndex(Compra.LembreteEntry._ID)));
                lembrete.setNome(cursor.getString(cursor.getColumnIndex(Compra.LembreteEntry.COLUNA_DESCRICAO_LEMBRETE)));
                lembrete.setConteudo(cursor.getString(cursor.getColumnIndex(Compra.LembreteEntry.COLUNA_CONTEUDO)));
                lembrete.setDataHora(Util.strToDateTime(cursor.getString(cursor.getColumnIndex(Compra.LembreteEntry.COLUNA_DATA_HORA))));
                lembretes.add(lembrete);
            } while (cursor.moveToNext());
        }

        db.close();

        return lembretes;
    }
}
