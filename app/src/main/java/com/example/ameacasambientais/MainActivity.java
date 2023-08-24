package com.example.ameacasambientais;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listViewAmeaca;
    public List<Ameaca> ameacas = new ArrayList<>();
    public AmeacaAdapter ameacaAdapter;

    AmeacasSQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new AmeacasSQLiteDatabase(getBaseContext());
        Ameaca ameaca = new Ameaca();
        ameaca.setEndereco("Rua Madrid 18");
        ameaca.setData("17/02/1998");
        ameaca.setDescricao("Teste do banco");
        Long num = db.addAmeaca(ameaca);
        Ameaca ameaca1 = db.getAmeaca(num);
        Log.d("LIST INFO ", ameaca1.toString());
        ameacas = db.getAmeacas();
        listViewAmeaca = findViewById(R.id.listViewAmeacas);
        ameacaAdapter = new AmeacaAdapter(getBaseContext(), ameacas);
        listViewAmeaca.setAdapter(ameacaAdapter);
    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(getBaseContext(), Cadastro.class);
        startActivity(intent);
    }

    public void abrirAtualizar(View view) {
        Intent intent = new Intent(getBaseContext(), Atualizacao.class);
        startActivity(intent);
    }
}