package com.example.ameacasambientais;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class AmeacasSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "ameacas.db";
    public static final Integer DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public AmeacasSQLiteDatabase(Context ctx) {
        this.ctx = ctx;
        db = new AmeacaSqliteDatabaseHelper().getWritableDatabase();
    }

    public static class AmeacasTable implements BaseColumns {
        public static final String TABLE_NAME = "ameacas";
        public static final String COLUMN_DESCRICAO = "descricao";
        public static final String COLUMN_DATA = "data";
        public static final String COLUMN_ENDERECO = "endereco";

        public static final String getSQL(){
            String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_DESCRICAO     + " TEXT, " +
                    COLUMN_DATA          + " TEXT, " +
                    COLUMN_ENDERECO      + " TEXT)";
            return sql;
        }
    }

    public Long addAmeaca(Ameaca ameaca) {
        ContentValues values = new ContentValues();
        values.put(AmeacasTable.COLUMN_DESCRICAO, ameaca.getDescricao());
        values.put(AmeacasTable.COLUMN_DATA, ameaca.getData());
        values.put(AmeacasTable.COLUMN_ENDERECO, ameaca.getEndereco());

        return db.insert(AmeacasTable.TABLE_NAME, null, values);
    }

    @SuppressLint("Range")
    public Ameaca getAmeaca(Long id) {
        String cols[] = {AmeacasTable._ID, AmeacasTable.COLUMN_DESCRICAO, AmeacasTable.COLUMN_DATA,
                AmeacasTable.COLUMN_ENDERECO};
        String args[] = {id.toString()};

        Cursor cursor = db.query(AmeacasTable.TABLE_NAME, cols,
                AmeacasTable._ID + "=?", args, null, null, AmeacasTable._ID);

        if(cursor.getCount() != 1){
            return null;
        }

        cursor.moveToNext();
        Ameaca ameaca = new Ameaca();
        ameaca.setId(cursor.getLong(cursor.getColumnIndex(AmeacasTable._ID)));
        ameaca.setDescricao(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DESCRICAO)));
        ameaca.setData(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DATA)));
        ameaca.setEndereco(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_ENDERECO)));

        return ameaca;
    }

    @SuppressLint("Range")
    public List<Ameaca> getAmeacas() {
        String cols[] = {AmeacasTable._ID, AmeacasTable.COLUMN_DESCRICAO, AmeacasTable.COLUMN_DATA,
                AmeacasTable.COLUMN_ENDERECO};

        Cursor cursor = db.query(AmeacasTable.TABLE_NAME, cols, null, null,
                null, null, AmeacasTable.COLUMN_DATA);

        List<Ameaca> ameacasList = new ArrayList<>();
        Ameaca ameaca;

        while (cursor.moveToNext()){
            ameaca = new Ameaca();
            ameaca.setId(cursor.getLong(cursor.getColumnIndex(AmeacasTable._ID)));
            ameaca.setDescricao(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DESCRICAO)));
            ameaca.setData(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_DATA)));
            ameaca.setEndereco(cursor.getString(cursor.getColumnIndex(AmeacasTable.COLUMN_ENDERECO)));
            ameacasList.add(ameaca);
        }

        return ameacasList;
    }

    public Integer updateAmeaca(Ameaca ameaca) {
        String args[] = {ameaca.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(AmeacasTable.COLUMN_DESCRICAO, ameaca.getDescricao());
        values.put(AmeacasTable.COLUMN_DATA, ameaca.getData());
        values.put(AmeacasTable.COLUMN_ENDERECO, ameaca.getEndereco());

        return db.update(AmeacasTable.TABLE_NAME, values, AmeacasTable._ID + "=?", args);
    }

    public Integer removeAmeaca(Ameaca ameaca) {
        String args[] = {ameaca.getId().toString()};
        return db.delete(AmeacasTable.TABLE_NAME, AmeacasTable._ID + "=?", args);
    }
    private class AmeacaSqliteDatabaseHelper extends SQLiteOpenHelper{

        public AmeacaSqliteDatabaseHelper() {
            super(ctx, DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(AmeacasTable.getSQL());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + AmeacasTable.TABLE_NAME);
            onCreate(db);
        }
    }
}
