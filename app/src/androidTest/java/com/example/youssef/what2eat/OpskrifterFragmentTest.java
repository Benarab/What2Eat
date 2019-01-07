package com.example.youssef.what2eat;

import com.example.youssef.what2eat.Models.Opskrifter;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OpskrifterFragmentTest {

    @Test
    public void addToPlan() {




    }

    @Test
    public void søgEfterNavn() {

       String newText = "K";
        ArrayList<Opskrifter> resultater = new ArrayList();
        ArrayList<Opskrifter> opskrifter = new ArrayList();

        opskrifter.add(new Opskrifter(10, "Kage"));
        opskrifter.add(new Opskrifter(10, "Chokolade"));
        opskrifter.add(new Opskrifter(10, "Dressing"));
        opskrifter.add(new Opskrifter(10, "Kyllingret"));


        if (newText.length() == 0) {
            resultater.addAll(opskrifter); }
        else {
            resultater.clear();
            for (Opskrifter o : opskrifter)
            { if (o.getNavn().contains(newText))
            {
                resultater.add(o);
            }
            }
        }

        Assert.assertEquals(resultater.size(), 2);

    }
    }
