package com.example.youssef.what2eat.Models;

public class Ingredienser {

    public int ingrediens_id;
    public String ingrediens_navn;
    public int ingrediens_antal;
    public String maal;
    public int foreign_opskrift;
    public String maal_navn;

    public Ingredienser(String navn, int antal, String mål, int f_opskrift)
    {
        ingrediens_navn = navn;
        ingrediens_antal = antal;
        maal = mål;
        foreign_opskrift = f_opskrift;

//setIngrediens_navn();


    }


}
