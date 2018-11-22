package com.example.youssef.what2eat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class AddOpskrift extends DialogFragment {

    private static final String[] INGREDIENSER = new String[]{
            "Salt", "Pepper", "Mælk", "Æg", "Mel", "Sukker", "Vand", "Smør", "Olie", "Flormelis", "Gær", "Chokolade", "Kartofler", "Tomater", "Argurk", "Ost", "Kylling", "Hakket oksekød", "Bagepulver"
    };

    private static final int RESULT_LOAD_IMAGES = 1;

    TextView filepath_name;

    private Spinner måleenhedSpinner1, måleenhedSpinner2, måleenhedSpinner3;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_opskrift, container, false);

        filepath_name = (TextView) view.findViewById((R.id.billede_path));

        måleenhedSpinner1 = (Spinner) view.findViewById(R.id.spinner1);
        måleenhedSpinner2 = (Spinner) view.findViewById(R.id.spinner2);
        måleenhedSpinner3 = (Spinner) view.findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> måleenhedAdapter = ArrayAdapter.createFromResource(getContext(), R.array.måleenheder, android.R.layout.simple_spinner_item);
        måleenhedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        måleenhedSpinner1.setAdapter(måleenhedAdapter);
        måleenhedSpinner2.setAdapter(måleenhedAdapter);
        måleenhedSpinner3.setAdapter(måleenhedAdapter);

        Button soeg_billede = (Button) view.findViewById(R.id.billede_button);
        soeg_billede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGES);


            }

        });

        AutoCompleteTextView et_ingredienser1 = view.findViewById(R.id.et_ingredienser1);
        ArrayAdapter<String> adapter_ingredienser = new ArrayAdapter<String>(getContext(),
                R.layout.custom_autotextview, R.id.text_view_list_item, INGREDIENSER);
        et_ingredienser1.setAdapter(adapter_ingredienser);

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGES && data != null) {
            Uri selectedImage = data.getData();
            filepath_name.setText(selectedImage.getPath());
        }
    }
}

   /* @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_add_opskrift, container, false);
        uploadImg = (Button) view.findViewById(R.id.billede_button);
        opret_knap = (Button) view.findViewById(R.id.addOpskrift);
        varighed = (EditText) view.findViewById(R.id.opskrift_varighed);
        navn = (EditText) view.findViewById(R.id.opskrift_navn);
        genre = (Spinner) view.findViewById(R.id.opskrift_genre);
        kategori = (Spinner) view.findViewById(R.id.opskrift_kategori);

        filepath_name = (TextView) view.findViewById(R.id.billede_path);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGES);
            }

        });

        opret_knap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tilfoejopskrift();
            }
        });

        return view;
    }
    private void Tilfoejopskrift() {
     ArrayList<Opskrifter> opskrifters = MainActivity.lokale_opskrifters;
int varighedText = Integer.parseInt(varighed.getText().toString());
String navnText = navn.getText().toString();
String kategoritext = kategori.getItemAtPosition(kategori.getSelectedItemPosition()).toString();
String genretext = kategori.getItemAtPosition(kategori.getSelectedItemPosition()).toString();

Opskrifter ni = new Opskrifter();
ni.varighed = varighedText;
ni.navn = navnText;
ni.kategori = kategoritext;
ni.genre = genretext;

     opskrifters.add(ni);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGES && resultCode == RESULT_OK && data != null) {
           Uri selectedImage = data.getData();
           filepath_name.setText(selectedImage.getPath());
        }}}*/


