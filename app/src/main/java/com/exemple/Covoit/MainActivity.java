package com.exemple.Covoit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.facebook.stetho.common.Util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListClickListener {

    private boolean isRotate = false;

    private CovoiturageBd bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Stetho.initializeWithDefaults(this); //Ajout de stetho à l'activité
        bd = CovoiturageBd.getInstance(getApplicationContext());
        CovoiturageInfo c = new CovoiturageInfo(1, new Date(2020, 06,13),
                "Dep",
                "Arr",
                1, 1);
        bd.getCovoiturageDao().insertCovoiturage(c);
        LiveData<List<CovoiturageInfo>> covoiturages = bd.getCovoiturageDao().getCovoiturages(1);

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
    }

    @Override
    public void onListClick(Covoiturage c) {
        Log.i("TAG", "onListClick: ");
    }

    public CovoiturageBd getBd(){
        return bd;
    }

}
