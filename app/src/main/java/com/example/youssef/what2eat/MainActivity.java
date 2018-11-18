package com.example.youssef.what2eat;

import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView appBarLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
