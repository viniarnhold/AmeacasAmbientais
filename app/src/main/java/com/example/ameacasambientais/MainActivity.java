package com.example.ameacasambientais;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    public ListView listViewAmeaca;
    public AmeacaAdapter ameacaAdapter;
    public ActivityResultLauncher<Intent> activityResultLauncher;
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

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        ameacaAdapter.notifyDataSetChanged();
                    }
                });
    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(getBaseContext(), Cadastro.class);
        activityResultLauncher.launch(intent);
    }

    public void abrirAtualizar(Long id) {
        Intent intent = new Intent(getBaseContext(), Atualizacao.class);
        intent.putExtra("ID", id);
        activityResultLauncher.launch(intent);
    }
}