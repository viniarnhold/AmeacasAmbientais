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
    List<Ameaca> ameacas;

    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public AmeacaAdapter(Context context, List<Ameaca> ameacas) {
        this.inflater = LayoutInflater.from(context);
        this.ameacas = ameacas;
    }


    @Override
    public int getCount() {
        return ameacas.size();
    }

    @Override
    public Object getItem(int position) {
        return ameacas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.ameaca_item, null);
        TextView textDescricao = view.findViewById(R.id.textDescricaoItem);
        TextView textData = view.findViewById(R.id.textDataItem);
        Ameaca ameaca = ameacas.get(position);
        textDescricao.setText(ameaca.getDescricao());
        textData.setText(ameaca.getData());
        return view;
    }
}
