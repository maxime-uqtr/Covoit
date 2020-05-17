package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.AnimationBouton;
import com.exemple.Covoit.controleur.TelechargerImage;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.CovoiturageService;
import com.exemple.Covoit.retrofit.UtilisateurService;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutionException;

public class AccueilActivite extends AppCompatActivity {

    private boolean isRotate = false;

    public static CovoiturageService apiInterface;
    private ImageView pp;
    private TextView tvNoms;
    private TextView tvRole;
    private FloatingActionButton FABrechercheCovoiturage;
    private FloatingActionButton FABproposeCovoiturage;
    private FloatingActionButton FABouvrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(UtilisateurActuel.getUtilisateur().isConducteur() && UtilisateurActuel.getUtilisateur().isConducteur()) {
            setContentView(R.layout.activity_accueil_cp);
        }
        else if(UtilisateurActuel.getUtilisateur().isConducteur()){
            setContentView(R.layout.activity_accueil_c);
        }
        else if(UtilisateurActuel.getUtilisateur().isPassager()){
            setContentView(R.layout.activity_accueil_p);
        }
        Stetho.initializeWithDefaults(this); //Ajout de stetho à l'activité

        pp = findViewById(R.id.accueil_pp);
        tvNoms = findViewById(R.id.accueil_prenomNom);
        tvRole = findViewById(R.id.accueil_conducteurPassager);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_trajets, R.id.navigation_propositions, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Utilisateur user = UtilisateurActuel.getUtilisateur();
        String pNom = user.getPrenom() + " " + user.getNom();
        tvNoms.setText(pNom);
        if(user.isConducteur() && user.isPassager())
                    tvRole.setText("Conducteur/Passager");
        else if(user.isConducteur()){
            tvRole.setText("Conducteur");
        }
        else{
            tvRole.setText("Passager");
        }

        String urlLogo = getApplicationContext().getString(R.string.urlLogo);
        try {
            pp.setImageBitmap(new TelechargerImage().execute(urlLogo).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addListeners();
    }

    private void addListeners() {
        if(UtilisateurActuel.getUtilisateur().isConducteur() && UtilisateurActuel.getUtilisateur().isPassager()) {
            FABouvrir = findViewById(R.id.accueil_FAB_ouvrir);
            FABouvrir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isRotate = AnimationBouton.rotateFab(v, !isRotate);
                    if (isRotate) {
                        AnimationBouton.show(FABproposeCovoiturage);
                        AnimationBouton.show(FABrechercheCovoiturage);
                    } else {
                        AnimationBouton.hide(FABproposeCovoiturage);
                        AnimationBouton.hide(FABrechercheCovoiturage);
                    }
                }
            });
        }

        FABproposeCovoiturage = findViewById(R.id.accueil_FAB_propose);
        AnimationBouton.hide(FABproposeCovoiturage);
        FABproposeCovoiturage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent propositionIntent = new Intent(getApplicationContext(), PropositionActivite.class);
                startActivity(propositionIntent);
            }
        });

        FABrechercheCovoiturage = findViewById(R.id.accueil_FAB_recherche);
        AnimationBouton.hide(FABrechercheCovoiturage);
        FABrechercheCovoiturage.setOnClickListener(v -> {
            Intent rechercheIntent = new Intent(this, RechercheActivite.class);
            startActivity(rechercheIntent);
        });
    }
}
