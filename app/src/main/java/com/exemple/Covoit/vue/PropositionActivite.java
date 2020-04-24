package com.exemple.Covoit.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.exemple.Covoit.R;

public class PropositionActivite extends AppCompatActivity {

    private TextView date;
    private AutoCompleteTextView depart;
    private AutoCompleteTextView destination;
    private NumberPicker npPrix;
    private int prix;
    private NumberPicker npPlaces;
    private int places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        date = findViewById(R.id.proposition_date);
        depart = findViewById(R.id.proposition_depart);
        destination = findViewById(R.id.proposition_destination);
        npPrix = findViewById(R.id.proposition_prix);
        npPlaces = findViewById(R.id.proposition_places);

    }
}
