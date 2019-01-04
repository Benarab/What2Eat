package com.example.youssef.what2eat.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.youssef.what2eat.MainActivity;
import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.R;

import java.util.ArrayList;
import java.util.List;

public class IngrediensAdapter extends ArrayAdapter<String> {
    private int resourceLayout;
    private Context mContext;

    public IngrediensAdapter(Context context, int resource, ArrayList<String> items) {
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
            TextView tt1 = (TextView) v.findViewById(R.id.tv_ingrediens);
            ImageButton imageButton = (ImageButton) v.findViewById(R.id.btn_slet_ingrediens);

            if (tt1 != null) {
                tt1.setText(p);
            }


            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.søge_ingredienser.remove(p);
notifyDataSetChanged();

                }
            });


        }

        return v;
    }

}


