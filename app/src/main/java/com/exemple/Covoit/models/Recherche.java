package com.exemple.Covoit.models;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;

import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.R;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.Locale;

public class Recherche extends AppCompatActivity implements OnListClickListener {

    public AutocompleteSupportFragment rechercheDepart;
    public AutocompleteSupportFragment rechercheArrivee;
    public String selectionDepart;
    public String selectionArrivee;

    public CalendarView dateRecherchee;
    public Button btnRechercher;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key), Locale.CANADA_FRENCH);
        }

        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        rechercheDepart = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.search_depart);

        // Specify the types of place data to return.
        rechercheDepart.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        rechercheDepart.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            private static final String TAG = "autocomplete";

            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });
/*
        // Initialize the AutocompleteSupportFragment.
        rechercheArrivee = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.search_depart);

        // Specify the types of place data to return.
        rechercheArrivee.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        rechercheArrivee.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            private static final String TAG = "autocomplete";

            @SuppressLint("WrongConstant")
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });*/
    }

    @Override
    public void onListClick(Covoiturage c) {

    }

}
