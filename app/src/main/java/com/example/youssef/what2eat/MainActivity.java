package com.example.youssef.what2eat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.youssef.what2eat.Models.Ingredienser;
import com.example.youssef.what2eat.Models.Opskrifter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView appBarLabel;
    public static ArrayList<Opskrifter> lokale_opskrifters = new ArrayList<>();
    public static ArrayList<Ingredienser> lokale_ingredienser = new ArrayList<>();
    public static ArrayList<String> søge_ingredienser = new ArrayList<>();
    public static ArrayList<Opskrifter> lokale_fremtidigeopskrifter = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Hentopskrifter(this);

        appBarLabel = (TextView) findViewById(R.id.appBarLabel);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "Signatra.ttf");
        appBarLabel.setTypeface(myCustomFont);

        BottomNavigationView navigation = findViewById(R.id.bottomNavView_Bar);
        navigation.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlanFragment()).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_plan:
                    selectedFragment = new PlanFragment();
                    break;
                case R.id.navigation_opskrifter:
                    selectedFragment = new OpskrifterFragment();
                    break;
                case R.id.navigation_what2eat:
                    selectedFragment = new What2EatFragment();
                    break;
                case R.id.navigation_omos:
                    selectedFragment = new OmOsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    public static void Hentopskrifter(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();

        String frJson = appSharedPrefs.getString("fremtidige", "");
        String opJson = appSharedPrefs.getString("user", "");
        String inJson = appSharedPrefs.getString("ingredienser", "");

        ArrayList<Opskrifter> fr;
        ArrayList<Opskrifter> op;
        ArrayList<Ingredienser> in;


        op = gson.fromJson(opJson, new TypeToken<ArrayList<Opskrifter>>() {
        }.getType());
        in = gson.fromJson(inJson, new TypeToken<ArrayList<Ingredienser>>() {
        }.getType());
        fr = gson.fromJson(inJson, new TypeToken<ArrayList<Opskrifter>>() {
        }.getType());

        if (op != null)
            MainActivity.lokale_opskrifters = op;
        if (in != null)
            MainActivity.lokale_ingredienser = in;
        if (fr != null)
            MainActivity.lokale_fremtidigeopskrifter = fr;

    }
}
