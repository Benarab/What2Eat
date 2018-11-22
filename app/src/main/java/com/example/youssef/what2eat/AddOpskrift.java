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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.youssef.what2eat.Models.Opskrifter;

import java.util.ArrayList;

public class AddOpskrift extends DialogFragment implements View.OnClickListener {

    private static final String[] INGREDIENSER = new String[]{
            "Salt", "Pepper", "Mælk", "Æg", "Mel", "Sukker", "Vand", "Smør", "Olie", "Flormelis", "Gær", "Chokolade", "Kartofler", "Tomater", "Argurk", "Ost", "Kylling", "Hakket oksekød", "Bagepulver"
    };

    private static final int RESULT_LOAD_IMAGES = 1;
    TextView filepath_name;
    Button uploadImg, opret_knap, soeg_billede, tilfoejing;
    EditText t_varighed, t_navn;
    Spinner t_genre, t_kategori;
    LinearLayout ny_layout, ingrediens_layout;
    private int id = 0;


    private Spinner måleenhedSpinner1, måleenhedSpinner2, måleenhedSpinner3;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_opskrift, container, false);
        filepath_name = (TextView) view.findViewById((R.id.billede_path));

        ingrediens_layout = (LinearLayout) view.findViewById(R.id.ingrediens_layout);

        måleenhedSpinner1 = (Spinner) view.findViewById(R.id.spinner1);
        måleenhedSpinner2 = (Spinner) view.findViewById(R.id.spinner2);
        måleenhedSpinner3 = (Spinner) view.findViewById(R.id.spinner3);

        ArrayAdapter<CharSequence> måleenhedAdapter = ArrayAdapter.createFromResource(getContext(), R.array.måleenheder, android.R.layout.simple_spinner_item);
        måleenhedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        måleenhedSpinner1.setAdapter(måleenhedAdapter);
        måleenhedSpinner2.setAdapter(måleenhedAdapter);
        måleenhedSpinner3.setAdapter(måleenhedAdapter);



        uploadImg = (Button) view.findViewById(R.id.billede_button);

        t_varighed = (EditText) view.findViewById(R.id.opskrift_varighed);
        t_navn = (EditText) view.findViewById(R.id.opskrift_navn);
        t_genre = (Spinner) view.findViewById(R.id.opskrift_genre);
        t_kategori = (Spinner) view.findViewById(R.id.opskrift_kategori);

        filepath_name = (TextView) view.findViewById((R.id.billede_path));

        opret_knap = (Button) view.findViewById(R.id.opret_knap);
        opret_knap.setOnClickListener(this);

        soeg_billede = (Button) view.findViewById(R.id.billede_button);
        soeg_billede.setOnClickListener(this);

        tilfoejing = (Button) view.findViewById(R.id.btn_tilføj_ingrediens);
        tilfoejing.setOnClickListener(this);

        AutoCompleteTextView et_ingredienser1 = view.findViewById(R.id.et_ingredienser1);
        ArrayAdapter<String> adapter_ingredienser = new ArrayAdapter<String>(getContext(),
                R.layout.custom_autotextview, R.id.text_view_list_item, INGREDIENSER);
        et_ingredienser1.setAdapter(adapter_ingredienser);

        return view;
    }

    private void Tilfoejopskrift() {
        ArrayList<Opskrifter> opskrifters = MainActivity.lokale_opskrifters;
/*
        opskrifters.add(new Opskrifter()
        [
                navn =
                ]);


*/

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGES && data != null) {
            Uri selectedImage = data.getData();
            filepath_name.setText(selectedImage.getPath());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.billede_button:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGES);
                break;

            case R.id.opret_knap:
                Tilfoejopskrift();
                break;


            case R.id.btn_tilføj_ingrediens:

                CreateIngrediens();
                break;


            default:
                break;

        }
    }

    private void CreateIngrediens() {

        ny_layout = new LinearLayout(getContext());
        ny_layout.setOrientation(LinearLayout.HORIZONTAL);
        EditText ed_ingrediens = new EditText(getContext());

        EditText ed_mængde = new EditText(getContext());
        Spinner spinner_maal = new Spinner(getContext());

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        spinner_maal.setLayoutParams(param);
        ed_ingrediens.setLayoutParams(param);
        ed_mængde.setLayoutParams(param);

        ny_layout.addView(spinner_maal);
        ny_layout.addView(ed_ingrediens);
        ny_layout.addView(ed_mængde);

ingrediens_layout.addView(ny_layout);

    }
}






