package com.exemple.Covoit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.facebook.stetho.common.Util;

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
                        1, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        2, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        3, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        4, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        5, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        6, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        7, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        8, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        9, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        10, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        11, new Date(2020, 06,13),
                        "Dep",
                        "Arr",
                        1, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")),
                new Covoiturage(
                        12, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2, new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg")))));
    }

}
