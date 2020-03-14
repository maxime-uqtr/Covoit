package com.exemple.Covoit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
                new Covoiturage(
                        new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1),
                new Covoiturage(
                        new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2),
                new Covoiturage(
                        new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1),
                new Covoiturage(
                        new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2),
                new Covoiturage(
                        new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1),
                new Covoiturage(
                        new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2),
                new Covoiturage(
                        new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1),
                new Covoiturage(
                        new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2),
                new Covoiturage(
                        new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1),
                new Covoiturage(
                        new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2),
                new Covoiturage(
                        new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1),
                new Covoiturage(
                        new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2))));
    }

}
