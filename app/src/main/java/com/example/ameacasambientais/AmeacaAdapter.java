package com.example.ameacasambientais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class AmeacaAdapter extends BaseAdapter {

    LayoutInflater inflater;
    AmeacasSQLiteDatabase db;

    public AmeacaAdapter(Context ctx, AmeacasSQLiteDatabase db) {
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getAmeacas().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getAmeacas().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db.getAmeacas().get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.ameaca_item, null);
        TextView textDescricao = view.findViewById(R.id.textDescricaoItem);
        TextView textData = view.findViewById(R.id.textDataItem);
        Ameaca ameaca = db.getAmeacas().get(position);
        textDescricao.setText(ameaca.getDescricao());
        textData.setText(ameaca.getData());
        return view;
    }
}
