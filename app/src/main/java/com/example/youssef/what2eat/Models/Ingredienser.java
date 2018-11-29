package com.example.youssef.what2eat.Models;

public class Ingredienser {

    public int ingrediens_id;
    public String ingrediens_navn;
    public int ingrediens_antal;
    public int maal;
    public int foreign_opskrift;
    public String maal_navn;

    public Ingredienser()
    {

setIngrediens_navn();


    }


    public void setIngrediens_navn()
    {
        if(maal == 1)
        {
            maal_navn = "dl";
        }

        else if(maal != 1 )
        {
            maal_navn = null;
        }

        else if(maal == 2)
        {
            maal_navn = "tsk";
        }

        else if (maal == 3)
        {
            maal_navn = "ssk";
        }

    }


}
