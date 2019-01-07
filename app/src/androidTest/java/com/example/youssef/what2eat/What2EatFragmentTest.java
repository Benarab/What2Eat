package com.example.youssef.what2eat;

import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class What2EatFragmentTest {

    @Test
    public void getSøgeResultater(String newText) {

        ArrayList<String> søgeord = new ArrayList();
        ArrayList<Opskrifter> resultater = new ArrayList();
        ArrayList<Opskrifter> opskrifter = new ArrayList();
        ArrayList<Ingredienser> ingredienser = new ArrayList();


        opskrifter.add(new Opskrifter(10, "Kage"));
        opskrifter.add(new Opskrifter(20, "Chokolade"));
        opskrifter.add(new Opskrifter(30, "Dressing"));
        opskrifter.add(new Opskrifter(40, "Kyllingret"));

        ingredienser.add(new Ingredienser("Drys", 10, "Gram", 10));
        ingredienser.add(new Ingredienser("Mælk", 10, "Dl", 20));
        ingredienser.add(new Ingredienser("Løg", 10, "Gram", 30));
        ingredienser.add(new Ingredienser("Masala", 10, "Gram", 40));
        søgeord.add("Mælk");

        for (String søg_ingre : søgeord) {
            for (Opskrifter opskrift : opskrifter) {
                for (Ingredienser ops_ingre : ingredienser) {
                    if (søg_ingre.equals(ops_ingre.ingrediens_navn)) {
                        resultater.add(opskrift);
                        break;
                    }

                }
            }
        }
        Assert.assertEquals(resultater.size(), 1);

}}