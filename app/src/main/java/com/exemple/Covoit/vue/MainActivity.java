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
import com.exemple.Covoit.OnListClickListener;
import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.TelechargerImage;
import com.exemple.Covoit.models.Covoiturage;
import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.MalformedURLException;
import java.net.URL;
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

    @Override
    public void onListClick(Covoiturage c) {
        Log.i("TAG", "onListClick: ");
    }

    public CovoiturageBd getBd(){
        return bd;
    }

}
