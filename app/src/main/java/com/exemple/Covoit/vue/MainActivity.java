package com.exemple.Covoit.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.exemple.Covoit.ListAdapteur;
import com.exemple.Covoit.OnListClickListener;
import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Recherche;
import com.exemple.Covoit.models.Utilisateur;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListClickListener {

    private boolean isRotate = false;

    private CovoiturageBd bd;
    private FloatingActionButton FABrechercheCovoiturage;
    private FloatingActionButton FABproposeCovoiturage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this); //Ajout de stetho à l'activité

        FABproposeCovoiturage = findViewById(R.id.main_FAB_propose);
        FABrechercheCovoiturage = findViewById(R.id.main_FAB_recherche);
        FABproposeCovoiturage.setOnClickListener(v -> {
            Intent rechercheIntent = new Intent(this, Recherche.class);
            startActivity(rechercheIntent);
        });


        bd = CovoiturageBd.getInstance(getApplicationContext());
        Covoiturage c = new Covoiturage(1, new Date(2020, 06,13),
                "Dep",
                "Arr", (float) 10, 1, 1);
        bd.getCovoiturageDao().insert(c);
        LiveData<List<Covoiturage>> covoiturages = bd.getCovoiturageDao().getAll();

        RecyclerView rv = findViewById(R.id.main_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new ListAdapteur(Arrays.asList(
                bd.getCovoiturageDao().get(1),
                new Covoiturage(
                        2, new Date(2020, 07,14),
                        "Depa",
                        "Arri", (float) 5, 2,
                        2)),
                        this));
    }

    @Override
    public void onListClick(Covoiturage c) {
        Log.i("TAG", "onListClick: ");
    }

    public CovoiturageBd getBd(){
        return bd;
    }

}