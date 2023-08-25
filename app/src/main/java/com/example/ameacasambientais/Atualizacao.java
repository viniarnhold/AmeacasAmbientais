package com.example.ameacasambientais;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Atualizacao extends AppCompatActivity {

    EditText txtDescricao, txtData, txtEndereco;

    AmeacasSQLiteDatabase db;
    Ameaca ameaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);
        txtDescricao = findViewById(R.id.editTextEdicaoDescricao);
        txtData = findViewById(R.id.editTextEdicaoDate);
        txtEndereco = findViewById(R.id.editTextEdicaoEndereco);

        db = new AmeacasSQLiteDatabase(getBaseContext());
        Long id = getIntent().getLongExtra("ID", 0);

        ameaca = db.getAmeaca(id);

        txtDescricao.setText(ameaca.getDescricao());
        txtData.setText(ameaca.getData());
        txtEndereco.setText(ameaca.getEndereco());
    }

    public void atualizarAmeaca(View view) {
        ameaca.setDescricao(txtDescricao.getText().toString());
        ameaca.setData(txtData.getText().toString());
        ameaca.setEndereco(txtEndereco.getText().toString());
        db.updateAmeaca(ameaca);
        notifyAll();
        finish();
    }
}
