package com.example.youssef.what2eat.Models;

import android.graphics.Bitmap;

import com.example.youssef.what2eat.MainActivity;

import java.util.ArrayList;

public class Opskrifter {

    public int ID;
    public String navn;
    public int varighed;
    public String tilføjelsesdato;
    public String genre;
    public String kategori;
    public int  rating;
    public Bitmap billede;
    public ArrayList<Ingredienser> ingredienser_list;
    public String beskrivelse;

    public Opskrifter(int ID, String navn) {
        this.ID = ID;
        this.navn = navn;
        this.ingredienser_list = new ArrayList<Ingredienser>();
        for(Ingredienser ingre:MainActivity.lokale_ingredienser)
        {
            if(ingre.foreign_opskrift == ID)
            {
                this.ingredienser_list.add(ingre);
            }
        }
    }

    public int getID() { return ID; }

    public String getNavn() { return navn; }

    public int getVarighed() { return varighed; }

    public String getTilføjelsesdato() { return tilføjelsesdato; }

    public String getGenre() { return genre; }

    public String getKategori() { return kategori; }

    public int getRating() { return rating; }

    public Bitmap getBillede() { return billede; }

    public String getBeskrivelse() { return beskrivelse; }

   public ArrayList<Ingredienser> getIngredienser() {return ingredienser_list; }



    @Override
    public String toString() {
        return getNavn();
    }
}
