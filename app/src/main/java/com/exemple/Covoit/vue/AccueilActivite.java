package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.AnimationBouton;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Trajet;
import com.exemple.Covoit.models.Utilisateur;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccueilActivite extends AppCompatActivity {

    private boolean isRotate = false;

    private CovoiturageBd bd;
    private ImageView pp;
    private FloatingActionButton FABrechercheCovoiturage;
    private FloatingActionButton FABproposeCovoiturage;
    private FloatingActionButton FABouvrir;
    private RecyclerView rv;
    private PopUpCovoiturage popupCovoiturage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Stetho.initializeWithDefaults(this); //Ajout de stetho à l'activité

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_trajets, R.id.navigation_propositions, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        FABproposeCovoiturage = findViewById(R.id.accueil_FAB_propose);
        AnimationBouton.hide(FABproposeCovoiturage);
        FABrechercheCovoiturage = findViewById(R.id.accueil_FAB_recherche);
        AnimationBouton.hide(FABrechercheCovoiturage);

        FABouvrir = findViewById(R.id.accueil_FAB_ouvrir);
        FABouvrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = AnimationBouton.rotateFab(v, !isRotate);
                if(isRotate){
                    AnimationBouton.show(FABproposeCovoiturage);
                    AnimationBouton.show(FABrechercheCovoiturage);
                }else{
                    AnimationBouton.hide(FABproposeCovoiturage);
                    AnimationBouton.hide(FABrechercheCovoiturage);
                }
            }
        });

        FABrechercheCovoiturage.setOnClickListener(v -> {
            Intent rechercheIntent = new Intent(this, RechercheActivite.class);
            startActivity(rechercheIntent);
        });

        FABproposeCovoiturage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent propositionIntent = new Intent(getApplicationContext(), PropositionActivite.class);
                startActivity(propositionIntent);
            }
        });

    }

    public void initBd(){
        List<Utilisateur> listeU = new ArrayList<>();
        listeU.add(new Utilisateur(3,
                "Dromont",
                "Matt",
                "mail",
                "mdp", "0708099080",
                "url", true, false));
        listeU.add(new Utilisateur(4,
                 "Dion",
                "Angele",
                "mail2",
                "mdp2", "0102087020",
                "url2", false, true));
        bd.getUtilisateurDao().insert(listeU.toArray(new Utilisateur[]{}));

        List<Covoiturage> listeC = new ArrayList<>();
        listeC.add(new Covoiturage(new Date(2020, 4,14),
                "Québec",
                "Louiseville", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,15),
                "Louiseville",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,14),
                "Québec",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,14),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,14),
                "Louiseville",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,20),
                "Montréal",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 6,24),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,4),
                "Louiseville",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 5,30),
                "Trois-Rivières",
                "Montréal", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,4),
                "Montréal",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 7,1),
                "Québec",
                "Trois-Rivières", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        listeC.add(new Covoiturage(new Date(2020, 6,16),
                "Trois-Rivières",
                "Québec", (float) 5, 2,
                bd.getUtilisateurDao().getAll().get(1).getId()));
        for(Covoiturage c : listeC){
            bd.getCovoiturageDao().insert(c);
        }

        ArrayList<Trajet> listeT = new ArrayList<>();
        listeT.add(new Trajet(1,1));
        listeT.add(new Trajet(1, 3));
        listeT.add(new Trajet(2, 2));
        listeT.add(new Trajet(3, 1));
        for(Trajet t : listeT){
            bd.getTrajetDao().insert(t);
        }

    }

    /*@Override
    public void onListClick(Covoiturage c) {
        if(popupCovoiturage == null) { //Première instance du popup
            popupCovoiturage = new PopUpCovoiturage(c);
            popupCovoiturage.show(getSupportFragmentManager(), null);
        }
        else if(!popupCovoiturage.isVisible()){ //Si dialog non visible
            popupCovoiturage.setCovoiturage(c);
            popupCovoiturage.show(getSupportFragmentManager(), null);
        }
        else{
            //Affichage dialog en cours
        }
    }*/
}
