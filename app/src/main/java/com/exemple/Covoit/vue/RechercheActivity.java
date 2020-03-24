package com.exemple.Covoit.vue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.Covoit.ListAdapteur;
import com.exemple.Covoit.OnListClickListener;
import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.Recherche;
import com.exemple.Covoit.models.Covoiturage;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RechercheActivity extends AppCompatActivity implements OnListClickListener {

    private CovoiturageBd bd;

    private EditText rechercheDepart;
    private EditText rechercheDestination;
    private String selectionDepart;
    private String selectionDestination;
    private TextView dateRecherchee;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Date dateSelectionnee;
    private Button btnRechercher;
    private RecyclerView rvCovoiturages;
    private Recherche rechercheControleur;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercheDepart = findViewById(R.id.recherche_depart);
        rechercheDestination = findViewById(R.id.recherche_destination);
        dateRecherchee = findViewById(R.id.recherche_Calendrier);
        btnRechercher = findViewById(R.id.recherche_bouton);

        //Initialisation BD
        bd = CovoiturageBd.getInstance(getApplicationContext());


        rechercheDepart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                selectionDepart = s.toString();
            }
        });

        rechercheDestination.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                selectionDestination = s.toString();
            }
        });

        dateRecherchee.setOnClickListener(v -> {
            Calendar calendrier = Calendar.getInstance();
            int year = calendrier.get(Calendar.YEAR);
            int month = calendrier.get(Calendar.MONTH);
            int day = calendrier.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    RechercheActivity.this,
                    android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                    dateSetListener,
                    year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
            dialog.show();
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1; //January is 0
                dateSelectionnee = new Date(year, month, dayOfMonth);
                String dateTexte = dayOfMonth + "/" + month + "/" + year;
                dateRecherchee.setText(dateTexte);
            }
        };

        rechercheControleur = new Recherche();
        rvCovoiturages = findViewById(R.id.recherche_rv);
        rvCovoiturages.setLayoutManager(new LinearLayoutManager(rvCovoiturages.getContext()));
        /*LiveData<List<Covoiturage>> lCovoit = bd.getCovoiturageDao().getAll();
        Log.i("test", lCovoit.ge);
        Log.i("test", "test2");
        List<Covoiturage> listeCovoiturages = rechercheControleur.rechercher(lCovoit.getValue(), selectionDepart, selectionDestination, dateSelectionnee);
        rvCovoiturages.setAdapter(new ListAdapteur(listeCovoiturages, this));*/

    }

    @Override
    public void onListClick(Covoiturage c) {

    }

}
