package com.exemple.Covoit.vue;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Covoiturage;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        date = findViewById(R.id.proposition_date);
        tvDate = findViewById(R.id.proposition_dateSelectionnee);
        depart = findViewById(R.id.proposition_depart);
        destination = findViewById(R.id.proposition_destination);
        npPrix = findViewById(R.id.proposition_prix);
        npPrix.setMinValue(5); //On initialise paramètres prix
        npPrix.setMaxValue(100);
        npPrix.setValue(5);
        npPlaces = findViewById(R.id.proposition_places);
        npPlaces.setMinValue(1); //On initialise paramètres places
        npPlaces.setMaxValue(4);
        npPlaces.setValue(3);
        btnProposer = findViewById(R.id.proposition_btn_proposer);
        btnProposer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CovoiturageBd bd = CovoiturageBd.getInstance(getApplicationContext());
                bd.getCovoiturageDao().insert(new Covoiturage(dateSelectionnee, depart.getText().toString(), destination.getText().toString(), prix, places, 2));
            }
        });

        DatePickerDialog datePickerDialog = new DatePickerDialog( this, this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
