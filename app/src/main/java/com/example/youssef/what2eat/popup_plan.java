package com.example.youssef.what2eat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.youssef.what2eat.Models.Opskrifter;
import com.google.gson.Gson;

import java.io.Serializable;

public class popup_plan extends DialogFragment {

    Opskrifter Opskrift_object;
    DatePicker datePicker;
    Button tilføj_button;
    String dato;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.popup_plan, container, false);
        datePicker = (DatePicker) view.findViewById(R.id.datepicker_id);
        tilføj_button = (Button) view.findViewById(R.id.tilføj_datoknap);
        dato = new String();


        tilføj_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (datePicker.getDayOfMonth() != 0) {
                    String day =  String.valueOf(datePicker.getDayOfMonth());
                    String year = String.valueOf(datePicker.getYear());
                    String month = String.valueOf(datePicker.getMonth() + 1);

                    if(month.equals("1")){
                        month = "Januar";
                    } else if (month.equals("2")){
                        month = "Februar";
                    } else if (month.equals("3")){
                        month = "Marts";
                    } else if (month.equals("4")){
                        month = "April";
                    } else if (month.equals("5")){
                        month = "Maj";
                    } else if (month.equals("6")){
                        month = "Juni";
                    } else if (month.equals("7")){
                        month = "Juli";
                    } else if (month.equals("8")){
                        month = "August";
                    } else if (month.equals("9")){
                        month = "September";
                    } else if (month.equals("10")){
                        month = "Oktober";
                    } else if (month.equals("11")){
                        month = "November";
                    } else if (month.equals("12")){
                        month = "December";
                    }

                    dato = String.valueOf(day) + ". " + String.valueOf(month) + " " + String.valueOf(year);

                    Opskrift_object.tilføjelsesdato = dato;

                    MainActivity.lokale_fremtidigeopskrifter.add(Opskrift_object);

                    Toast.makeText(getContext(), "Opskriften er blevet tilføjet til din madplan!", Toast.LENGTH_SHORT).show();

                    SharedPreferences appSharedPrefs = PreferenceManager
                            .getDefaultSharedPreferences(getContext().getApplicationContext());
                    SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(MainActivity.lokale_fremtidigeopskrifter);
                    prefsEditor.putString("fremtidige", json);
                    prefsEditor.commit();

                } else {

                    Toast.makeText(getContext(), "Tilføj dato", Toast.LENGTH_SHORT).show();

                }
                dismiss();

            }
        });
return view;
    }
}




//obj.tilføjelsesdato;


