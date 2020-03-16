package com.exemple.Covoit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.Covoit.models.CovoiturageInfo;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new ListAdapteur(Arrays.asList(
                new CovoiturageInfo(
                        1, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        2, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        3, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        4, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        5, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        6, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        7, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        8, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        9, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        10, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2),
                new CovoiturageInfo(
                        11, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, 1),
                new CovoiturageInfo(
                        12, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, 2))));

    }

}
