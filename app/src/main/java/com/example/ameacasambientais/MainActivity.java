package com.example.ameacasambientais;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    public static final String AMEACAS_KEY = "ameacas";
    public ActivityResultLauncher<Intent> activityResultLauncher;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference ameacas = root.child(AMEACAS_KEY);
    FirebaseListAdapter<Ameaca> ameacaAdapter;
    ListView listViewAmeaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewAmeaca = findViewById(R.id.listViewAmeacas);

        Query query = ameacas;
        FirebaseListOptions<Ameaca> options = new FirebaseListOptions.Builder<Ameaca>()
                .setLayout(R.layout.ameaca_item)
                .setQuery(query, Ameaca.class)
                .build();
        ameacaAdapter = new FirebaseListAdapter<Ameaca>(options) {
            @Override
            protected void populateView(View v, Ameaca model, int position) {
                TextView textDescricao = v.findViewById(R.id.textDescricaoItem);
                TextView textData = v.findViewById(R.id.textDataItem);
                textDescricao.setText(model.getDescricao());
                textData.setText(model.getData());
            }
        };
        ameacaAdapter.startListening();
        listViewAmeaca.setAdapter(ameacaAdapter);

        listViewAmeaca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference item = ameacaAdapter.getRef(position);
                abrirAtualizar(item.getKey(), ameacaAdapter.getItem(position));
            }
        });

        listViewAmeaca.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference item = ameacaAdapter.getRef(position);
                item.removeValue();
                return false;
            }
        });
    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(getBaseContext(), Cadastro.class);
        startActivity(intent);
    }

    public void abrirAtualizar(String key, Ameaca ameaca) {
        Intent intent = new Intent(getBaseContext(), Atualizacao.class);
        intent.putExtra("KEY", key);
        intent.putExtra("STD", ameaca);
        startActivity(intent);
    }
}