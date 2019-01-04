package com.example.youssef.what2eat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.youssef.what2eat.Adapter.FremtidigeOpskrifterAdapter;

public class PlanFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_plan, container, false);



        ListView lv_planer = (ListView) view.findViewById(R.id.lv_fremtidsplan);
        lv_planer.setAdapter(new FremtidigeOpskrifterAdapter(getContext(), R.layout.custom_plan_list, MainActivity.lokale_fremtidigeopskrifter));

        return view;
    }
}
