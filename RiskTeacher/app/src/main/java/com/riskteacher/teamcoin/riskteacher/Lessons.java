package com.riskteacher.teamcoin.riskteacher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Lessons extends Fragment {

    public Lessons() {
        // Required empty public constructor
    }

    Button lesson1, lesson2, lesson3, lesson4, lesson5, lesson6, lesson7, lesson8, lesson9, lesson10, lesson11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_lessons, container, false);

        lesson1 = view.findViewById(R.id.lesson1);
        lesson2 = view.findViewById(R.id.lesson2);
        lesson3 = view.findViewById(R.id.lesson3);
        lesson4 = view.findViewById(R.id.lesson4);
        lesson5 = view.findViewById(R.id.lesson5);
        lesson6 = view.findViewById(R.id.lesson6);
        lesson7 = view.findViewById(R.id.lesson7);
        lesson8 = view.findViewById(R.id.lesson8);
        lesson9 = view.findViewById(R.id.lesson9);
        lesson10 = view.findViewById(R.id.lesson10);
        lesson11 = view.findViewById(R.id.lesson11);

        lesson1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        lesson11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLesson(view);
            }
        });
        return view;
    }

    public void onClickLesson(View view){
        Intent i = new Intent(getActivity(), LessonDisplay.class);
        i.putExtra("lesson", view.getId());

        startActivity(i);
    }
}
