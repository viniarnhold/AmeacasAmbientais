package com.example.ameacasambientais;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro extends AppCompatActivity {

    AmeacasSQLiteDatabase db;
    EditText txtDescricao, txtData, txtEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtDescricao = findViewById(R.id.editTextDescricao);
        txtData = findViewById(R.id.editTextData);
        txtEndereco = findViewById(R.id.editEndereco);

        db = new AmeacasSQLiteDatabase(getBaseContext());
    }

    public void addAmeaca(View v){
        Ameaca ameaca = new Ameaca();
        ameaca.setDescricao(txtDescricao.getText().toString());
        ameaca.setData(txtData.getText().toString());
        ameaca.setEndereco(txtEndereco.getText().toString());

        db.addAmeaca(ameaca);
        notifyAll();
        finish();
    }
}
