package com.example.youssef.what2eat.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.youssef.what2eat.CustomLayout.NonScrollListView;
import com.example.youssef.what2eat.MainActivity;
import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;
import com.example.youssef.what2eat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OpskrifterAdapter extends ArrayAdapter<Opskrifter> {


    private Context context;
    private ArrayList<Opskrifter> values;
    private ArrayList<Opskrifter> list;

    public OpskrifterAdapter(Context context, ArrayList<Opskrifter> values) {
        super(context, R.layout.custom_opskrifter_list, values);

        this.context = context;
        this.values = values;
        values = MainActivity.lokale_opskrifters;
        this.list = new ArrayList<Opskrifter>();
        this.list.addAll(values);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_opskrifter_list, parent, false);
        }




        ImageView imgOpskrift = (ImageView) row.findViewById(R.id.img_opskrift);
        TextView tvOpskriftnavn = (TextView) row.findViewById(R.id.tv_opskriftNavn);
        TextView tvRating = (TextView) row.findViewById(R.id.tv_rating);
        TextView tvTilberedelsestid = (TextView) row.findViewById(R.id.tv_tilberedelsestid);
        TextView tvKategori = (TextView) row.findViewById(R.id.tv_kategori);
        TextView tvGenre = (TextView) row.findViewById(R.id.tv_genre);
        TextView tvFremgangsmåde = (TextView) row.findViewById(R.id.tv_fremgangsmåde);
        NonScrollListView Ingredienserliste = (NonScrollListView) row.findViewById(R.id.lv_Ingredienser);
        ArrayList<String> newList = new ArrayList<String>();


       Opskrifter item = values.get(position);



        Bitmap billede = item.getBillede();
        String navn = item.getNavn();
        int rating = item.getRating();
        int varighed = item.getVarighed();
        String kategori = item.getKategori();
        String genre = item.getGenre();
        String fremgangsmåde = item.getFremgangsmåde();


        if (item.ingredienser_list != null) {
            for (Ingredienser ingredienser : item.getIngredienser()) {

                newList.add(ingredienser.ingrediens_navn + ", mængde: " + ingredienser.ingrediens_antal + " " + ingredienser.maal);
            }
        }
        Ingredienserliste.setAdapter(new OpskriftIngrediensAdapter(getContext(), R.layout.listcell, newList));


        tvOpskriftnavn.setText(navn);
        tvRating.setText(String.valueOf(rating));
        tvTilberedelsestid.setText(String.valueOf(varighed));
        tvKategori.setText(kategori);
        tvGenre.setText(genre);
        tvFremgangsmåde.setText(fremgangsmåde);

        return row;
    }

   /* public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());

        {
            values.clear();
            if (charText.length() == 0)
            {
                values.addAll(list);
            }
            else {

                for (Opskrifter opskrifter: list) {
                    if (opskrifter.getNavn().toLowerCase(Locale.getDefault()).contains(charText)) ;
                    values.add(opskrifter);


                }
            }
            notifyDataSetChanged();
        }*/
}
