package com.exemple.Covoit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.facebook.stetho.common.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnListClickListener {

    private boolean isRotate = false;

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
                        1,
                        new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg", true, true)),
                new Covoiturage(
                        2, new Date(2020, 07,14),
                        "Depa",
                        "Arri",
                        2,
                        new Utilisateur(1, "nom", "prénom", "utilisateur@mail.com", "mdp", "http://image.jpg", true, true))
                ), this));

        final FloatingActionButton fabRechercher = findViewById(R.id.FAB_recherche);
        BouttonAnimation.init(fabRechercher);
        final FloatingActionButton fabProposer = findViewById(R.id.FAB_propose);
        BouttonAnimation.init(fabProposer);

        FloatingActionButton fabOuvrir = findViewById(R.id.FAB_ouvrir);
        fabOuvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = BouttonAnimation.rotateFab(v, !isRotate);
                if(isRotate){
                    BouttonAnimation.show(fabProposer);
                    BouttonAnimation.show(fabRechercher);
                }else{
                    BouttonAnimation.hide(fabProposer);
                    BouttonAnimation.hide(fabRechercher);
                }
            }
        });
    }

    @Override
    public void onListClick(Covoiturage c) {
        Log.i("TAG", "onListClick: ");
    }


}
