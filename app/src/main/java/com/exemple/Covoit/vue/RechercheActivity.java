package com.exemple.Covoit.vue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exemple.Covoit.ListAdapteur;
import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.models.Covoiturage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RechercheActivity extends AppCompatActivity implements OnListClickListener {

    private CovoiturageBd bd;

    private EditText rechercheDepart;
    private EditText rechercheDestination;
    private TextView dateRecherchee;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Date dateSelectionnee;
    private Button btnRechercher;
    private RecyclerView rvCovoiturages;
    private ListAdapteur rvAdapteur;

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

        //Bouton sélectionner une date
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

        btnRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cacherClavier();
                if(rechercheDepart.getText().length() == 0 || rechercheDestination.getText().length() == 0 || dateSelectionnee == null)
                    Toast.makeText(getApplicationContext(), "Veuillez renseigner tous les champs.", Toast.LENGTH_LONG).show();
                else if(dateSelectionnee.before(Calendar.getInstance().getTime()))
                        Toast.makeText(getApplicationContext(), "Date sélectionée est passée.", Toast.LENGTH_LONG).show();
                else{ //Tous les champs sont renseignés
                    String depart = "%" + rechercheDepart.getText().toString() + "%";
                    String destination = "%" + rechercheDestination.getText().toString() + "%";
                    rvAdapteur = new ListAdapteur(bd.getCovoiturageDao().getLike(depart, destination), RechercheActivity.this::onListClick);
                    rvCovoiturages.setAdapter(rvAdapteur);
                }
            }
        });

        //Inflation rv
        rvCovoiturages = findViewById(R.id.recherche_rv);
        rvCovoiturages.setLayoutManager(new LinearLayoutManager(rvCovoiturages.getContext()));
        rvCovoiturages.setAdapter(null);
        rvCovoiturages.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    //Méthode pour fermer clavier
    public void cacherClavier(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rechercheDepart.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(rechercheDestination.getWindowToken(), 0);
    }

    @Override
    public void onListClick(Covoiturage c) {

    }

}
