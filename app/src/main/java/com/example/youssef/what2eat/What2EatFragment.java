package com.example.youssef.what2eat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youssef.what2eat.Adapter.OpskrifterAdapter;
import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;

import java.io.Console;
import java.util.ArrayList;

public class What2EatFragment extends Fragment {

    private static final String[] INGREDIENSER = new String[]{
            "Salt", "Pepper", "Mælk", "Æg", "Mel", "Sukker", "Vand", "Smør", "Olie", "Flormelis", "Gær", "Chokolade", "Kartofler", "Tomater", "Argurk", "Ost", "Kylling", "Hakket oksekød", "Bagepulver"
    };

    ImageButton btn_add_ingredient;
    AutoCompleteTextView et_igredienser;
    ListView lv_what2eat;
    ListView søgeResultaterList;
    ArrayAdapter<String> adapter;
    OpskrifterAdapter adapterOpskrift, adapterSøgeResultater;
    FloatingActionButton search_button;
    ArrayList<Opskrifter> resultat_opskrifter;
    TextView tv_ingrediens;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_what2eat, container, false);

        søgeResultaterList = (ListView) view.findViewById((R.id.list_søgeresultater));
        search_button = (FloatingActionButton) view.findViewById((R.id.søg_knap));
        btn_add_ingredient = (ImageButton) view.findViewById(R.id.search_button);
        et_igredienser = (AutoCompleteTextView) view.findViewById(R.id.id_search_EditText);
        lv_what2eat = (ListView) view.findViewById(R.id.lv_what2eat);
        tv_ingrediens = (TextView) view.findViewById(R.id.tv_ingrediens);
        resultat_opskrifter = new ArrayList<>();
        adapterOpskrift = new OpskrifterAdapter(getContext(), MainActivity.lokale_opskrifters);
        adapterSøgeResultater = new OpskrifterAdapter(getContext(), resultat_opskrifter);
        søgeResultaterList.setAdapter(adapterOpskrift);

        et_igredienser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    et_igredienser.setHint("");
                InputMethodManager imm = (InputMethodManager)   getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        });

        adapter = new ArrayAdapter<String>(getContext(), R.layout.custom_ingredienser_list, MainActivity.søge_ingredienser);
        lv_what2eat.setAdapter(adapter);
        btn_add_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_igredienser.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Du har ikke angivet nogen ingrediens", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity.søge_ingredienser.add(et_igredienser.getText().toString().trim());
                    et_igredienser.setText(null);

                    adapter.notifyDataSetChanged();
                    lv_what2eat.setAdapter(adapter);
                }
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
                //search_button.setVisibility(view.GONE);
                adapterOpskrift.notifyDataSetChanged();
                søgeResultaterList.setAdapter(adapterSøgeResultater);
                søgeResultaterList.setVisibility(View.VISIBLE);
            }
        });

        ArrayAdapter<String> adapter_ingredienser = new ArrayAdapter<String>(getContext(),
                R.layout.custom_autotextview, R.id.text_view_list_item, INGREDIENSER);
        et_igredienser.setAdapter(adapter_ingredienser);

        return view;
    }
}
