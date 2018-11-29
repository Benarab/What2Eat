package com.example.youssef.what2eat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.youssef.what2eat.Adapter.OpskrifterAdapter;
import com.example.youssef.what2eat.Models.Opskrifter;

import java.util.ArrayList;
import java.util.List;

public class OpskrifterFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_opskrifter, container, false);
        FloatingActionButton showDialog = (FloatingActionButton) view.findViewById(R.id.fab_filter);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupFilterFragment dialog = new PopupFilterFragment();
                dialog.show(getFragmentManager(), "PopupFilterFragment");

            }

        });

        ListView lvOpskrifter = (ListView) view.findViewById(R.id.lv_Opskrifter);

        lvOpskrifter.setAdapter(new OpskrifterAdapter(getContext(), MainActivity.lokale_opskrifters));

        FloatingActionButton addOpskrift = (FloatingActionButton) view.findViewById(R.id.addOpskrift);
        addOpskrift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddOpskrift dialog = new AddOpskrift();
                dialog.show(getFragmentManager(), "AddOpskrift");
            }
        });
        return view;
    }
}
