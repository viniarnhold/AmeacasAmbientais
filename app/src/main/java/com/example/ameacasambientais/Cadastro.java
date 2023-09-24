package com.example.ameacasambientais;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cadastro extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference ameacas = root.child(MainActivity.AMEACAS_KEY);
    EditText txtDescricao, txtData, txtEndereco;
    public static final int CAMERA = 1022;
    Bitmap bitmap;
    ImageView image;
    Boolean hasImagem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtDescricao = findViewById(R.id.editTextDescricao);
        txtData = findViewById(R.id.editTextData);
        txtEndereco = findViewById(R.id.editEndereco);
        image = findViewById(R.id.imageCadastro);

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
    }

    public void addAmeaca(View v) {
        Ameaca ameaca = new Ameaca();
        ameaca.setDescricao(txtDescricao.getText().toString());
        ameaca.setData(txtData.getText().toString());
        ameaca.setEndereco(txtEndereco.getText().toString());
        if(hasImagem) {
            String bitmapEncoded = loadImage();
            hasImagem = false;
            ameaca.setImagem(bitmapEncoded);
        }

        String key = ameacas.push().getKey();
        ameacas.child(key).setValue(ameaca);
        finish();
    }
    public void updateLabel(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtData.setText(sdf.format(myCalendar.getTime()));
    }

    public String loadImage(){
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOut);
        return Base64.encodeToString(byteOut.toByteArray(), android.util.Base64.DEFAULT);
    }

    public void takePicture(View v){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK){
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
            hasImagem = true;
        }
    }
}
