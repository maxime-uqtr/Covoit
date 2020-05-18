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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.exemple.Covoit.R;
import com.exemple.Covoit.controleur.ControleurPropositions;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.CovoiturageService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class PropositionActivite extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static CovoiturageService apiInterface;

    private TextView date;
    final Calendar myCalendar = Calendar.getInstance();
    private TextView tvDate;
    private String dateSelectionnee;
    private AutoCompleteTextView depart;
    private AutoCompleteTextView destination;
    private ArrayList<String> adresses = new ArrayList<>();
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

        apiInterface = ApiClient.getApiClient().create(CovoiturageService.class);

        //Adaptateurs AutoCompleteTextView
        String[] city = new String[]{"Québec", "Trois-Rivières", "Montréal", "Louiseville", "Laval", "Longueuil", "Saint-Hyacinthe", "Terrebonne", "Drummondvile", "Saint-Jean-sur-Richelieu", "Rimouski", "Mirabel"};
        Collections.addAll(adresses, city);
        ArrayAdapter<String> adressesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adresses);
        depart = findViewById(R.id.proposition_depart);
        depart.setAdapter(adressesAdapter);
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
        destination.setAdapter(adressesAdapter);
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
                if(!depart.getText().toString().isEmpty() && !destination.getText().toString().isEmpty() && !dateSelectionnee.isEmpty()) { //On vérifie champs remplis
                    if(adresses.contains(depart.getText().toString()) && adresses.contains(destination.getText().toString())) { //Si les villes sont valables
                        Covoiturage c = new Covoiturage(dateSelectionnee, depart.getText().toString(), destination.getText().toString(), npPrix.getValue(), npPlaces.getValue(), UtilisateurActuel.getUtilisateur().getId());
                        ControleurPropositions.proposer(c, apiInterface, getApplicationContext());
                        Intent accueilIntent = new Intent(v.getContext(), AccueilActivite.class);
                        startActivity(accueilIntent);
                    }
                    else{ //Sinon
                        Toast.makeText(getApplicationContext(), "Veuillez sélectionner des villes correspondant à celles proposées.", Toast.LENGTH_LONG).show();
                    }
                }
                else{ //Sinon si champs manquants
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs.", Toast.LENGTH_LONG).show();
                }
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
        tvDate.setVisibility(View.VISIBLE);
        String jour = String.valueOf(dayOfMonth); //On prépare string date
        String mois = String.valueOf(month); //Mois commence à zéro
        if(dayOfMonth < 10){ //Respecter la syntaxe Date
            jour = "0" + jour;
        }
        if(month < 10){
            mois = "0" + mois;
        }
        dateSelectionnee = jour + "/" + mois + "/" + year;
        tvDate.setText(dateSelectionnee);
    }

    //Méthode pour fermer clavier
    public void cacherClavier(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(depart.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(destination.getWindowToken(), 0);
    }
}