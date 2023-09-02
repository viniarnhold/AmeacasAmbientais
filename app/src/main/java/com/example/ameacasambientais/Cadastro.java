package com.example.ameacasambientais;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cadastro extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();

    AmeacasSQLiteDatabase db;
    EditText txtDescricao, txtData, txtEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtDescricao = findViewById(R.id.editTextDescricao);
        txtData = findViewById(R.id.editTextData);
        txtEndereco = findViewById(R.id.editEndereco);

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };
        txtData.setOnClickListener(view -> new DatePickerDialog(Cadastro.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH))
                .show());

        db = new AmeacasSQLiteDatabase(getBaseContext());
    }

    public void addAmeaca(View v) {
        Ameaca ameaca = new Ameaca();
        ameaca.setDescricao(txtDescricao.getText().toString());
        ameaca.setData(txtData.getText().toString());
        ameaca.setEndereco(txtEndereco.getText().toString());

        db.addAmeaca(ameaca);
        setResult(Activity.RESULT_OK);
        finish();
    }
    public void updateLabel(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtData.setText(sdf.format(myCalendar.getTime()));
    }
}
