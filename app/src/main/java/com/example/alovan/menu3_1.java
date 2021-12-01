package com.example.alovan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.Calendar;
import java.util.Objects;


public class menu3_1 extends Fragment {

    final Calendar myCalendar = Calendar.getInstance();


    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu3_1, container, false);
        TextView textView = view.findViewById(R.id.text_view);
        Button sa = view.findViewById(R.id.test);
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(),taodonhang.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        String title = "menu3.1";
        Objects.requireNonNull(getActivity()).setTitle(title);
    }
}