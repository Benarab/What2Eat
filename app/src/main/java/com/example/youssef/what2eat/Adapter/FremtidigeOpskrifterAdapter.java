package com.example.youssef.what2eat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.youssef.what2eat.Models.Opskrifter;
import com.example.youssef.what2eat.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FremtidigeOpskrifterAdapter extends ArrayAdapter<Opskrifter> {
    private int resourceLayout;
    private Context mContext;

    public FremtidigeOpskrifterAdapter(Context context, int resource, ArrayList<Opskrifter> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;


        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Opskrifter p = getItem(position);

        if (p != null) {
            TextView et_navn= (TextView) v.findViewById(R.id.adapter_navn_et);
            TextView et_tid= (TextView) v.findViewById(R.id.adapter_tilberedelsestid_et);
            TextView et_dato = (TextView) v.findViewById(R.id.id_dato);

            et_navn.setText(p.navn);
            et_tid.setText(String.valueOf(p.varighed + " min"));
            et_dato.setText(p.tilføjelsesdato);

        }
        return v;
    }

}


