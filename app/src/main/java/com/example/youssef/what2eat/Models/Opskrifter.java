package com.example.youssef.what2eat.Models;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;

public class Opskrifter {

    public int ID;
    public String navn;
    public int varighed;
    public String tilføjelsesdato;
    public String genre;
    public String kategori;
    public int  rating;
    public Image billede;
    public String beskrivelse;

    public int getID() { return ID; }

    public String getNavn() { return navn; }

    public int getVarighed() { return varighed; }

    public String getTilføjelsesdato() { return tilføjelsesdato; }

    public String getGenre() { return genre; }

    public String getKategori() { return kategori; }

    public int getRating() { return rating; }

    public Image getBillede() { return billede; }

    public String getBeskrivelse() { return beskrivelse; }
}
