package com.example.ameacasambientais;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Atualizacao extends AppCompatActivity {

    EditText txtDescricao, txtData, txtEndereco;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference ameacas = root.child(MainActivity.AMEACAS_KEY);
    String key;
    Ameaca ameaca;
    Calendar myCalendar;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);
        txtDescricao = findViewById(R.id.editTextEdicaoDescricao);
        txtData = findViewById(R.id.editTextEdicaoDate);
        txtEndereco = findViewById(R.id.editTextEdicaoEndereco);

        key = getIntent().getStringExtra("KEY");

        ameaca = (Ameaca) getIntent().getSerializableExtra("STD");

        myCalendar = Calendar.getInstance();
        try {
            myCalendar.setTime(simpleDateFormat.parse(ameaca.getData()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        txtData.setOnClickListener(view -> new DatePickerDialog(Atualizacao.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH))
                .show());

        txtDescricao.setText(ameaca.getDescricao());
        txtData.setText(ameaca.getData());
        txtEndereco.setText(ameaca.getEndereco());
    }

    public void atualizarAmeaca(View view) {
        ameaca.setDescricao(txtDescricao.getText().toString());
        ameaca.setData(txtData.getText().toString());
        ameaca.setEndereco(txtEndereco.getText().toString());
        ameacas.child(key).setValue(ameaca);
        finish();
    }
    public void updateLabel(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtData.setText(sdf.format(myCalendar.getTime()));
    }
}
