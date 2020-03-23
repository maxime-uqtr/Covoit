package com.exemple.Covoit.models;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.exemple.Covoit.OnListClickListener;
import com.exemple.Covoit.R;

public class Recherche extends AppCompatActivity implements OnListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        //Obtenir l'intent, vérifie l'action et obtient la requête
        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String requete = intent.getStringExtra(SearchManager.QUERY);
            //Effectuer recherche de query

        }
    }

    @Override
    public void onListClick(Covoiturage c) {

    }

}
