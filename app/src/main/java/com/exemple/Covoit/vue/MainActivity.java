package com.exemple.Covoit.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.exemple.Covoit.BoutonAnimation;
import com.exemple.Covoit.ListAdapteur;
import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.TelechargerImage;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Recherche;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements OnListClickListener {

    private boolean isRotate = false;

    private CovoiturageBd bd;
    private ImageView pp;
    private FloatingActionButton FABrechercheCovoiturage;
    private FloatingActionButton FABproposeCovoiturage;
    private FloatingActionButton FABouvrir;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this); //Ajout de stetho à l'activité

        pp = findViewById(R.id.main_pp);
        FABproposeCovoiturage = findViewById(R.id.main_FAB_propose);
        BoutonAnimation.hide(FABproposeCovoiturage);
        FABrechercheCovoiturage = findViewById(R.id.main_FAB_recherche);
        BoutonAnimation.hide(FABrechercheCovoiturage);

        FABouvrir = findViewById(R.id.main_FAB_ouvrir);
        FABouvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = BoutonAnimation.rotateFab(v, !isRotate);
                if(isRotate){
                    BoutonAnimation.show(FABproposeCovoiturage);
                    BoutonAnimation.show(FABrechercheCovoiturage);
                }else{
                    BoutonAnimation.hide(FABproposeCovoiturage);
                    BoutonAnimation.hide(FABrechercheCovoiturage);
                }
            }
        });

        bd = CovoiturageBd.getInstance(getApplicationContext());
        initBD();

        FABproposeCovoiturage.setOnClickListener(v -> {
            Intent rechercheIntent = new Intent(this, RechercheActivity.class);
            startActivity(rechercheIntent);
        });

        rv = findViewById(R.id.main_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new ListAdapteur(bd.getCovoiturageDao().getAllList(), this));

        String urlLogo = "https://covoituragebd-7356.restdb.io/media/5e7a8375cf927e3e0001bc30";
        try {
            pp.setImageBitmap(new TelechargerImage().execute(urlLogo).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initBD(){

        List<Utilisateur> listeU = new ArrayList<>();
        listeU.add(new Utilisateur(
                1, "nom",
                "prenom",
                "mail",
                "mdp",
                "url", true, false));
        listeU.add(new Utilisateur(
                2,  "nom2",
                "prenom2",
                "mail2",
                "mdp2",
                "url2", false, true));
        bd.getUtilisateurDao().insert(listeU.toArray(new Utilisateur[]{}));

        List<Covoiturage> listeC = new ArrayList<>();
        listeC.add(new Covoiturage(new Date(2020, 04,14),
                "Québec",
                "Louiseville", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 05,15),
                "Louiseville",
                "-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 05,14),
                "Québec",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 05,20),
                "Montréal",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 07,14),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 07,14),
                "Louiseville",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 06,24),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 05,4),
                "Louiseville",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 05,30),
                "-Rivières",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 07,4),
                "Montréal",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 07,1),
                "Québec",
                "-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 06,16),
                "-Rivières",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        for(Covoiturage c : listeC){
            bd.getCovoiturageDao().insert(c);
        }
        Log.i("test", bd.getCovoiturageDao().getAllList().get(0).getVilleDep());

    }

    @Override
    public void onListClick(Covoiturage c) {
        Log.i("TAG", "onListClick: ");
    }

    public CovoiturageBd getBd(){
        return bd;
    }

}
