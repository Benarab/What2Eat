package com.example.youssef.what2eat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.youssef.what2eat.Adapter.FremtidigeOpskrifterAdapter;
import com.example.youssef.what2eat.Adapter.OpskrifterAdapter;
import com.example.youssef.what2eat.Models.Opskrifter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class PlanFragment extends Fragment {

    public ArrayList<Opskrifter> resultater;
    public ListView lvPlan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_plan, container, false);

        resultater = new ArrayList<Opskrifter>();
        resultater.addAll(MainActivity.lokale_fremtidigeopskrifter);
        lvPlan = (ListView) view.findViewById(R.id.lv_fremtidsplan);
        lvPlan.setAdapter(new FremtidigeOpskrifterAdapter(getContext(), R.layout.custom_plan_list, MainActivity.lokale_fremtidigeopskrifter));

        FremtidigeOpskrifterAdapter adapter = new FremtidigeOpskrifterAdapter(getContext(), R.layout.custom_plan_list, resultater);

        if (resultater != null)
            lvPlan.setAdapter(adapter);
        registerForContextMenu(lvPlan);

        return view;
    }

    public void removePlan(Opskrifter obj){
        MainActivity.lokale_fremtidigeopskrifter.remove(obj);
        FremtidigeOpskrifterAdapter adapter = new FremtidigeOpskrifterAdapter(getContext(), R.layout.custom_plan_list, MainActivity.lokale_fremtidigeopskrifter);
        lvPlan.setAdapter(adapter);

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(getContext().getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.lokale_fremtidigeopskrifter);
        prefsEditor.putString("fremtidige", json);
        prefsEditor.commit();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.lv_fremtidsplan) {
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Opskrifter obj = (Opskrifter) lvPlan.getItemAtPosition(acmi.position);

            menu.setHeaderTitle(obj.navn);


            menu.add(0, 0, Menu.NONE, "Ændre");
            menu.add(0, 1, Menu.NONE, "Slet");

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Opskrifter obj = (Opskrifter) lvPlan.getItemAtPosition(info.position);
        switch(item.getItemId()) {
            case 0:
                Toast.makeText(getContext(),"Denne funktion er ikke tilgængelig!", Toast.LENGTH_SHORT).show();
                return true;
            case 1:
                removePlan(obj);
                return true;
        }
        return super.onContextItemSelected(item);

    }
}
