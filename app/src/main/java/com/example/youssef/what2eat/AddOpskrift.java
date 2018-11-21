package com.example.youssef.what2eat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class AddOpskrift extends DialogFragment  {

            private static  final int RESULT_LOAD_IMAGES = 1;
            TextView filepath_name;

            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_add_opskrift, container, false);

                filepath_name = (TextView) view.findViewById((R.id.billede_path)) ;


                Button soeg_billede = (Button) view.findViewById(R.id.billede_button);
        soeg_billede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGES);


            }

        });

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGES && data != null) {
            Uri selectedImage = data.getData();
            filepath_name.setText(selectedImage.getPath());
        }}
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


