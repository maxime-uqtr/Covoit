package com.exemple.Covoit.vue;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Trajet;

import java.util.Calendar;
import java.util.Date;

public class PropositionActivite extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView date;
    final Calendar myCalendar = Calendar.getInstance();
    private TextView tvDate;
    private Date dateSelectionnee;
    private AutoCompleteTextView depart;
    private AutoCompleteTextView destination;
    private NumberPicker npPrix;
    private int prix;
    private NumberPicker npPlaces;
    private int places;
    private Button btnProposer;

    public PropositionActivite() {
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        depart = findViewById(R.id.proposition_depart);
        depart.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    destination.requestFocus();
                    return true;
                }
                return false;
            }
        });
        destination = findViewById(R.id.proposition_destination);
        destination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    date.callOnClick(); //On sélectionne ensuite la date
                    return true;
                }
                return false;
            }
        });
        date = findViewById(R.id.proposition_date);
        tvDate = findViewById(R.id.proposition_dateSelectionnee);
        
        npPrix = findViewById(R.id.proposition_prix);
        npPrix.setMinValue(5); //On initialise paramètres prix
        npPrix.setMaxValue(50);

        npPlaces = findViewById(R.id.proposition_places);
        npPlaces.setMinValue(1); //On initialise paramètres places
        npPlaces.setMaxValue(4);

        btnProposer = findViewById(R.id.proposition_btn_proposer);
        btnProposer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CovoiturageBd bd = CovoiturageBd.getInstance(getApplicationContext());
                bd.getCovoiturageDao().insert(new Covoiturage(10, dateSelectionnee, depart.getText().toString(), destination.getText().toString(), prix, places, 1));
                bd.getTrajetDao().insert(new Trajet(10, 1));
                Intent accueilIntent = new Intent(v.getContext(), AccueilActivite.class);
                startActivity(accueilIntent);
            }
        });

        DatePickerDialog datePickerDialog = new DatePickerDialog( this, this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cacherClavier();
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        tvDate.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        tvDate.setVisibility(View.VISIBLE);
        dateSelectionnee = new Date(dayOfMonth, month, year);
    }

    //Méthode pour fermer clavier
    public void cacherClavier(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(depart.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(destination.getWindowToken(), 0);
    }
}