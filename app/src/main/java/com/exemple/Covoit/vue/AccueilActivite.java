package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.UtilisateurService;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccueilActivite extends AppCompatActivity {

    private boolean isRotate = false;

    private CovoiturageBd bd;
    public static UtilisateurService apiInterface;
    private ImageView pp;
    private TextView tvNoms;
    private TextView tvRole;
    private FloatingActionButton FABrechercheCovoiturage;
    private FloatingActionButton FABproposeCovoiturage;
    private FloatingActionButton FABouvrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Stetho.initializeWithDefaults(this); //Ajout de stetho à l'activité

        pp = findViewById(R.id.accueil_pp);
        tvNoms = findViewById(R.id.accueil_prenomNom);
        tvRole = findViewById(R.id.accueil_conducteurPassager);
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

        bd = CovoiturageBd.getInstance(this);
        Utilisateur user = new Utilisateur();

        apiInterface = ApiClient.getApiClient().create(UtilisateurService.class);
        Call<List<Utilisateur>> call = apiInterface.getMdp("getMdp", "mail", "mdp");
        call.enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                List<Utilisateur> user = response.body();
                Utilisateur u = user.get(0);
                String pNom = u.getPrenom() + " " + u.getNom();
                tvNoms.setText(pNom);
                if(u.isConducteur() && u.isPassager())
                    tvRole.setText("Conducteur/Passager");
                else if(u.isConducteur()){
                    tvRole.setText("Conducteur");
                }
                else{
                    tvRole.setText("Passager");
                }
            }

            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {
                Log.i("TAG1", call.toString() + t.toString());
            }
        });

        String urlLogo = getApplicationContext().getString(R.string.urlLogo);
        try {
            pp.setImageBitmap(new TelechargerImage().execute(urlLogo).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
}
