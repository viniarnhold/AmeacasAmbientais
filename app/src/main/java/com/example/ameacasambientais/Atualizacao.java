package com.example.ameacasambientais;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Atualizacao extends AppCompatActivity {

    EditText txtDescricao, txtData, txtEndereco;

    AmeacasSQLiteDatabase db;
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

        db = new AmeacasSQLiteDatabase(getBaseContext());
        Long id = getIntent().getLongExtra("ID", 0);

        ameaca = db.getAmeaca(id);

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
        db.updateAmeaca(ameaca);
        setResult(Activity.RESULT_OK);
        finish();
    }
    public void updateLabel(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtData.setText(sdf.format(myCalendar.getTime()));
    }
}
