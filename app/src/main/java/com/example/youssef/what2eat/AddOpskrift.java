package com.example.youssef.what2eat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.youssef.what2eat.Adapter.OpskrifterAdapter;
import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class AddOpskrift extends DialogFragment implements View.OnClickListener {

    private static final String[] INGREDIENSER = new String[]{
            "Salt", "Pepper", "Mælk", "Æg", "Mel", "Sukker", "Vand", "Smør", "Olie", "Flormelis", "Gær", "Chokolade", "Kartofler", "Tomater", "Argurk", "Ost", "Kylling", "Hakket oksekød", "Bagepulver"
    };

    private static final String[] KATEGORI = new String[]{
            "Hovedret", "Forret", "Dessert", "Snack", "Drikke"
    };

    private static final String[] GENRE = new String[]{
            "Dansk", "Indisk", "Italiansk", "Amerikansk", "Fransk", "Thai", "Kinesisk", "Japansk", "Spansk", "Mexikansk", "Afrikansk", "Tyrkisk", "Marokkansk", "Svensk", "Norsk", "Portugesisk", "Tysk", "Græsk", "Engelsk", "Britisk"
    };

    private static final int RESULT_LOAD_IMAGES = 1;

    TextView filepath_name;
    Button uploadImg, opret_knap, soeg_billede, tilfoejing;
    EditText t_varighed, t_navn, t_ingrediens1, t_mængde1, t_ingrediens2, t_mængde2, t_ingrediens3, t_mængde3;
    AutoCompleteTextView t_genre, t_kategori, et_ingredienser1, et_genre;
    LinearLayout ny_layout, ingrediens_layout;
    ArrayAdapter<CharSequence> måleenhedAdapter;

    private int countupMaal = 0, countupIng = 0, countupmMngde = 0;


    private int id = 0;


    private Spinner måleenhedSpinner1, måleenhedSpinner2, måleenhedSpinner3;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_opskrift, container, false);

        // // // // // //
        filepath_name = (TextView) view.findViewById((R.id.billede_path));
        ingrediens_layout = (LinearLayout) view.findViewById(R.id.ingrediens_layout);
        måleenhedSpinner1 = (Spinner) view.findViewById(R.id.spinner1);
        måleenhedSpinner2 = (Spinner) view.findViewById(R.id.spinner2);
        måleenhedSpinner3 = (Spinner) view.findViewById(R.id.spinner3);
        t_ingrediens1 = (EditText) view.findViewById(R.id.et_ingredienser1);
        t_ingrediens2 = (EditText) view.findViewById(R.id.et_ingredienser2);
        t_ingrediens3 = (EditText) view.findViewById(R.id.et_ingredienser3);
        t_mængde1 = (EditText) view.findViewById(R.id.et_mængde1);
        t_mængde2 = (EditText) view.findViewById(R.id.et_mængde2);
        t_mængde3 = (EditText) view.findViewById(R.id.et_mængde3);
        uploadImg = (Button) view.findViewById(R.id.billede_button);
        t_varighed = (EditText) view.findViewById(R.id.opskrift_varighed);
        t_navn = (EditText) view.findViewById(R.id.opskrift_navn);
        t_genre = (AutoCompleteTextView) view.findViewById(R.id.opskrift_genre);
        t_kategori = (AutoCompleteTextView) view.findViewById(R.id.opskrift_kategori);
        filepath_name = (TextView) view.findViewById((R.id.billede_path));
        et_ingredienser1 = view.findViewById(R.id.et_ingredienser1);
        et_genre = view.findViewById(R.id.opskrift_genre);


        // // // // // //

        opret_knap = (Button) view.findViewById(R.id.opret_knap);
        opret_knap.setOnClickListener(this);

        soeg_billede = (Button) view.findViewById(R.id.billede_button);
        soeg_billede.setOnClickListener(this);

        tilfoejing = (Button) view.findViewById(R.id.btn_tilføj_ingrediens);
        tilfoejing.setOnClickListener(this);

        // // // // // //

        // // // // // //  Kalde og sætte adaptere

        måleenhedAdapter = ArrayAdapter.createFromResource(getContext(), R.array.måleenheder, android.R.layout.simple_spinner_item);
        måleenhedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        måleenhedSpinner1.setAdapter(måleenhedAdapter);
        måleenhedSpinner2.setAdapter(måleenhedAdapter);
        måleenhedSpinner3.setAdapter(måleenhedAdapter);

// // // // // //  Lave og sette adapters


        ArrayAdapter<String> adapter_ingredienser = new ArrayAdapter<String>(getContext(),
                R.layout.custom_autotextview, R.id.text_view_list_item, INGREDIENSER);
        et_ingredienser1.setAdapter(adapter_ingredienser);
        ArrayAdapter<String> adapter_genre = new ArrayAdapter<String>(getContext(),
                R.layout.custom_autotextview, R.id.text_view_list_item, GENRE);
        et_genre.setAdapter(adapter_genre);

        AutoCompleteTextView et_kategori = view.findViewById(R.id.opskrift_kategori);
        ArrayAdapter<String> adapter_kategori = new ArrayAdapter<String>(getContext(),
                R.layout.custom_autotextview, R.id.text_view_list_item, KATEGORI);
        et_kategori.setAdapter(adapter_kategori);

        // // // // // //

        return view;
    }


// Metoderne kommer herunder.

    // // // // // //
    public void Tilfoejopskrift(Context context) {
        Random random = new Random();
        int id = random.nextInt(500);

        addIngInList(id, context);

        Opskrifter no = new Opskrifter(id, t_navn.getText().toString());
        no.kategori = t_kategori.getText().toString();
        no.genre = t_genre.getText().toString();
        no.navn = t_navn.getText().toString();
        no.varighed = Integer.parseInt(t_varighed.getText().toString());

        MainActivity.lokale_opskrifters.add(no);

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.lokale_opskrifters);
        prefsEditor.putString("user", json);
        prefsEditor.commit();

        dismiss();
    }

    // // // // // //


    public void addIngInList(int opskriftID, Context context) {
        ArrayList<Ingredienser> nyliste = new ArrayList<Ingredienser>();

        if (! t_ingrediens1.getText().toString().isEmpty() && ! t_mængde1.getText().toString().isEmpty()) {
            MainActivity.lokale_ingredienser.add(new Ingredienser(t_ingrediens1.getText().toString(), Integer.parseInt(t_mængde1.getText().toString()), måleenhedSpinner1.getSelectedItem().toString(), opskriftID));
        }

        if (! t_ingrediens2.getText().toString().isEmpty() && ! t_mængde2.getText().toString().isEmpty()) {
            MainActivity.lokale_ingredienser.add(new Ingredienser(t_ingrediens2.getText().toString(), Integer.parseInt(t_mængde2.getText().toString()), måleenhedSpinner2.getSelectedItem().toString(), opskriftID));
        }

        if (! t_ingrediens3.getText().toString().isEmpty() && ! t_mængde3.getText().toString().isEmpty()) {
            MainActivity.lokale_ingredienser.add(new Ingredienser(t_ingrediens3.getText().toString(), Integer.parseInt(t_mængde3.getText().toString()), måleenhedSpinner3.getSelectedItem().toString(), opskriftID));
        }

// Her tilføjes der til sharedpreferences
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.lokale_ingredienser);
        prefsEditor.putString("ingredienser", json);


    }

    // // // // // //  Denne metoder er hjælp til at finde ibllede fra tlf
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGES && data != null) {
            Uri selectedImage = data.getData();
            filepath_name.setText(selectedImage.getPath());

        }
    }


    // // // // // //  on click
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.billede_button:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGES);
                break;

            case R.id.opret_knap:
                if (t_navn.getText().toString().isEmpty() | t_varighed.getText().toString().isEmpty() | t_genre.getText().toString().isEmpty() | t_kategori.getText().toString().isEmpty() | t_ingrediens1.getText().toString().isEmpty() | t_mængde1.getText().toString().isEmpty()){
                    t_navn.setHint("*");
                    t_navn.setHintTextColor(getResources().getColor(R.color.Red));
                    t_varighed.setHint("*");
                    t_varighed.setHintTextColor(getResources().getColor(R.color.Red));
                    t_genre.setHint("*");
                    t_genre.setHintTextColor(getResources().getColor(R.color.Red));
                    t_kategori.setHint("*");
                    t_kategori.setHintTextColor(getResources().getColor(R.color.Red));
                    t_ingrediens1.setHint("*");
                    t_ingrediens1.setHintTextColor(getResources().getColor(R.color.Red));
                    t_mængde1.setHint("*");
                    t_mængde1.setHintTextColor(getResources().getColor(R.color.Red));
                    break;
                } else {
                    Tilfoejopskrift(this.getContext());
                    break;
                }
            case R.id.btn_tilføj_ingrediens:
                CreateIngrediens();
                break;

            default:
                break;
        }
    }
    // // // // // //  Nedenstående metoder tilføjer et nyt felt af ingrediens i opret opskrift


    public void CreateIngrediens() {

        ny_layout = new LinearLayout(getContext());
        ny_layout.setOrientation(LinearLayout.HORIZONTAL);
        EditText ed_ingrediens = new EditText(getContext());
        EditText ed_mængde = new EditText(getContext());
        Spinner spinner_maal = new Spinner(getContext());

        ed_ingrediens.setHint("Ingrediens");
        ed_ingrediens.setTextSize(17);
        ed_mængde.setHint("Mængde");
        ed_mængde.setTextSize(16);

        spinner_maal.setAdapter(måleenhedAdapter);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1.0f
        );

        spinner_maal.setLayoutParams(param);
        ed_ingrediens.setLayoutParams(param);
        ed_mængde.setLayoutParams(param);


        spinner_maal.setId(countupMaal + 1);
        ed_ingrediens.setId(countupIng + 11);
        ed_mængde.setId(countupmMngde + 111);

        ny_layout.addView(ed_ingrediens);
        ny_layout.addView(ed_mængde);
        ny_layout.addView(spinner_maal);

        ingrediens_layout.addView(ny_layout);


    }


}






