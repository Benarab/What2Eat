package com.example.youssef.what2eat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.youssef.what2eat.Models.Opskrifter;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class AddOpskrift extends Fragment {

private static  final int RESULT_LOAD_IMAGES = 1;
private TextView filepath_name;
private  EditText varighed, navn;
private Spinner genre, kategori;
    Button loginbutton, recordbutton, viewbutton, projectsbutton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        Button uploadImg = (Button) view.findViewById(R.id.billede_button);
        Button opret_knap = (Button) view.findViewById(R.id.addOpskrift);
         varighed = (EditText) view.findViewById(R.id.opskrift_varighed);
        navn = (EditText) view.findViewById(R.id.opskrift_navn);
        genre = (Spinner) view.findViewById(R.id.opskrift_genre);
        kategori = (Spinner) view.findViewById(R.id.opskrift_kategori);




        filepath_name = (TextView) view.findViewById(R.id.billede_path) ;
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
                Tilføjopskrift();
            }
        });
        return view;
    }

    private void Tilføjopskrift() {
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
        }}}


