package com.example.youssef.what2eat;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;

import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddOpskriftTest {

    @Test
    public void tilfoejopskrift() {

        ArrayList<Opskrifter> js = new ArrayList<>();

        //Adding to SharedPreferences
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(InstrumentationRegistry.getTargetContext().getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(js);
        prefsEditor.putString("test", json);
        prefsEditor.commit();

        String testJson = appSharedPrefs.getString("test", "");

        //Getting from SharedPreferences
        ArrayList<Opskrifter> test_list;

        test_list = gson.fromJson(testJson, new TypeToken<ArrayList<Opskrifter>>() {
        }.getType());

        Assert.assertEquals(js, test_list);


    }



    @Test
    public void addIngInList() {
    }


}