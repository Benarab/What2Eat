package com.example.youssef.what2eat.Models;

public class Ingredienser {

    public int ingrediens_id;
    public String ingrediens_navn;
    public int ingrediens_antal;
    public int maal;
    public int foreign_opskrift;
    public String maal_navn;

    public Ingredienser(String navn, int antal, int mål, int f_opskrift)
    {
        ingrediens_id = 0;
        ingrediens_navn = navn;
        ingrediens_antal = antal;
        maal = mål;
        foreign_opskrift = f_opskrift;

//setIngrediens_navn();


    }

// Her sættes ingrediens navne vedhjælp af de tal som skal indikere navnene.
    public void setIngrediens_navn()
    {
        if(maal == 1)
        {
            maal_navn = "Saad er en kylling";
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
