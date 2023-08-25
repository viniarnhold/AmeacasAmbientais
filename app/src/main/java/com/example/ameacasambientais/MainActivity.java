package com.example.ameacasambientais;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listViewAmeaca;
    public AmeacaAdapter ameacaAdapter;

    public AmeacasSQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new AmeacasSQLiteDatabase(getBaseContext());

        listViewAmeaca = findViewById(R.id.listViewAmeacas);
        ameacaAdapter = new AmeacaAdapter(getBaseContext(), db);
        listViewAmeaca.setAdapter(ameacaAdapter);

        listViewAmeaca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirAtualizar(id);
            }
        });

        listViewAmeaca.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.removeAmeaca((Ameaca) ameacaAdapter.getItem(position));
                ameacaAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(getBaseContext(), Cadastro.class);
        startActivity(intent);
        ameacaAdapter.notifyDataSetChanged();
    }

    public void abrirAtualizar(Long id) {
        Intent intent = new Intent(getBaseContext(), Atualizacao.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}