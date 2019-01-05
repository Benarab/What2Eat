package com.example.youssef.what2eat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.youssef.what2eat.Adapter.OpskrifterAdapter;
import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class OpskrifterFragment extends Fragment {

    public ArrayList<Opskrifter> resultater;
    public ListView lvOpskrifter;
    //  DatePicker datePicker;
    // Button tilføj_button;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_opskrifter, container, false);
        FloatingActionButton showDialog = (FloatingActionButton) view.findViewById(R.id.fab_filter);
        SearchView searchView = (SearchView) view.findViewById(R.id.id_search);
        // datePicker = (DatePicker) view.findViewById(R.id.datepicker_id);
        // tilføj_button = (Button) view.findViewById(R.id.tilføj_datoknap) ;


        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupFilterFragment dialog = new PopupFilterFragment();
                dialog.show(getFragmentManager(), "PopupFilterFragment");

            }

        });

        lvOpskrifter = (ListView) view.findViewById(R.id.lv_Opskrifter);
        resultater = new ArrayList<Opskrifter>();
        resultater.addAll(MainActivity.lokale_opskrifters);

        OpskrifterAdapter adapter = new OpskrifterAdapter(getContext(), resultater);

        if (resultater != null)
            lvOpskrifter.setAdapter(adapter);
        registerForContextMenu(lvOpskrifter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                resultater.clear();

                if (newText.length() == 0) {
                    resultater.addAll(MainActivity.lokale_opskrifters); }
                    else {
                    resultater.clear();
                    for (Opskrifter o : MainActivity.lokale_opskrifters)
                    { if (o.getNavn().contains(newText))
                    {
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

    public void removeOpskrift(Opskrifter obj) {

        MainActivity.lokale_opskrifters.remove(obj);
        OpskrifterAdapter adapter = new OpskrifterAdapter(getContext(), MainActivity.lokale_opskrifters);
        lvOpskrifter.setAdapter(adapter);

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getContext().getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.lokale_opskrifters);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }

    public void addToPlan(Opskrifter obj)
    {
        popup_plan dialog = new popup_plan();
        dialog.Opskrift_object = obj;
        dialog.show(getFragmentManager(), "popup_plan");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        if (v.getId() == R.id.lv_Opskrifter) {
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Opskrifter obj = (Opskrifter) lvOpskrifter.getItemAtPosition(acmi.position);

            menu.setHeaderTitle(obj.navn);


            menu.add(0, 0, Menu.NONE, "Tilføj til plan");
            menu.add(0, 1, Menu.NONE, "Opdater");
            menu.add(0, 2, Menu.NONE, "Slet");

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Opskrifter obj = (Opskrifter) lvOpskrifter.getItemAtPosition(info.position);
        switch (item.getItemId()) {
            case 0:
                addToPlan(obj);
                return true;
            case 1:
                return true;
            case 2:
                removeOpskrift(obj);
                return true;

        }
        return super.onContextItemSelected(item);

    }


}