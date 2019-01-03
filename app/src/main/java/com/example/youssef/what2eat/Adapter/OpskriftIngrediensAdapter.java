package com.example.youssef.what2eat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.youssef.what2eat.R;

import java.util.ArrayList;

public class OpskriftIngrediensAdapter extends ArrayAdapter<String> {
private int resourceLayout;
private Context mContext;

public OpskriftIngrediensAdapter(Context context, int resource, ArrayList<String> items) {
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

        String p = getItem(position);

        if (p != null) {
        TextView tt1 = (TextView) v.findViewById(R.id.listcel_ing);

        if (tt1 != null) {
        tt1.setText(p);
        }

        }

        return v;
        }

        }
