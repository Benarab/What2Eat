package com.example.youssef.what2eat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.youssef.what2eat.Adapter.OpskrifterAdapter;
import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;

import java.util.ArrayList;
import java.util.List;

public class OpskrifterFragment extends Fragment {

    public ArrayList<Opskrifter> resultater;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_opskrifter, container, false);
        FloatingActionButton showDialog = (FloatingActionButton) view.findViewById(R.id.fab_filter);
        SearchView searchView = (SearchView) view.findViewById(R.id.id_search);

        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupFilterFragment dialog = new PopupFilterFragment();
                dialog.show(getFragmentManager(), "PopupFilterFragment");

            }

        });

        ListView lvOpskrifter = (ListView) view.findViewById(R.id.lv_Opskrifter);
        resultater = new ArrayList<Opskrifter>();
        resultater.addAll(MainActivity.lokale_opskrifters);

        OpskrifterAdapter adapter = new OpskrifterAdapter(getContext(), resultater);

        if (resultater != null)
            lvOpskrifter.setAdapter(adapter);
        registerForContextMenu(lvOpskrifter);

        lvOpskrifter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                resultater.clear();

                if (newText.length() == 0) {
                    resultater.addAll(MainActivity.lokale_opskrifters);
                } else

                {
                    resultater.clear();
                    for (Opskrifter o : MainActivity.lokale_opskrifters)

                    {

                        if (o.getNavn().contains(newText)) {
                            resultater.add(o);

                        }

                    }
                }
                adapter.notifyDataSetChanged();
                lvOpskrifter.setAdapter(adapter);
                return false;
            }
        });



        FloatingActionButton addOpskrift = (FloatingActionButton) view.findViewById(R.id.addOpskrift);
        addOpskrift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddOpskrift dialog = new AddOpskrift();
                dialog.show(getFragmentManager(), "AddOpskrift");
            }



        });



        return view;
            }



}



