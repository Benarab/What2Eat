package com.example.youssef.what2eat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.youssef.what2eat.Adapter.OpskrifterAdapter;
import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;

import java.io.Console;
import java.util.ArrayList;

public class What2EatFragment extends Fragment {

    ImageButton imgButton;
    EditText et_igredienser;
    GridView gridview;
    ListView søgeResultaterList;
    ArrayAdapter<String> adapter;
    OpskrifterAdapter adapterOpskrift, adapterSøgeResultater;
    Button search_button;
    ArrayList<Opskrifter> resultat_opskrifter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_what2eat, container, false);
        søgeResultaterList = (ListView) view.findViewById((R.id.list_søgeresultater));
        search_button = (Button) view.findViewById((R.id.søg_knap));
        imgButton = (ImageButton) view.findViewById(R.id.search_button);
        et_igredienser = (EditText) view.findViewById(R.id.id_search_EditText);
        gridview = (GridView) view.findViewById(R.id.grid_viewList);
        resultat_opskrifter = new ArrayList<>();
        adapterOpskrift = new OpskrifterAdapter(getContext(), MainActivity.lokale_opskrifters);
        adapterSøgeResultater = new OpskrifterAdapter(getContext(), resultat_opskrifter);
        søgeResultaterList.setAdapter(adapterOpskrift);

        adapter = new ArrayAdapter<String>(getContext(), R.layout.cell, MainActivity.søge_ingredienser);
        gridview.setAdapter(adapter);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.søge_ingredienser.add(et_igredienser.getText().toString().trim());
                et_igredienser.setText(null);

                adapter.notifyDataSetChanged();
                gridview.setAdapter(adapter);
            }

        });

        search_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                for (String søg_ingre : MainActivity.søge_ingredienser) {
                    for (Opskrifter opskrift : MainActivity.lokale_opskrifters) {
                        for (Ingredienser ops_ingre : opskrift.ingredienser_list) {
                            if (søg_ingre.equals(ops_ingre.ingrediens_navn)) {
                                resultat_opskrifter.add(opskrift);
                                break;
                            }

                        }
                    }
                }


                Log.d("Resultat: ", "" + resultat_opskrifter);
                search_button.setVisibility(view.GONE);
                adapterOpskrift.notifyDataSetChanged();
                søgeResultaterList.setAdapter(adapterSøgeResultater);
                søgeResultaterList.setVisibility(View.VISIBLE);
            }
        });


        return view;
    }
}
