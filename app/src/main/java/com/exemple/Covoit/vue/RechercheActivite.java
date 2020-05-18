package com.exemple.Covoit.vue;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.ControleurRecherche;
import com.exemple.Covoit.controleur.ListAdapteur;
import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.CovoiturageService;
import com.exemple.Covoit.retrofit.TrajetService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class RechercheActivite extends AppCompatActivity implements OnListClickListener {

    private static CovoiturageService apiInterface;
    private static TrajetService trajetInterface;
    private static CovoiturageBd bd;

    private AutoCompleteTextView rechercheDepart;
    private ImageView liste1;
    private AutoCompleteTextView rechercheDestination;
    private ImageView liste2;
    private ArrayList<String> adresses = new ArrayList<>();
    private TextView dateRecherchee;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Date date;
    private String dateSelectionnee;
    private Button btnRechercher;
    private RecyclerView rvCovoiturages;
    private ListAdapteur rvAdaptateur;
    private PopUpCovoiturage popupCovoiturage;
    private static boolean pop1 = true; //Popup suggestion actv invisible
    private static boolean pop2 = true; //Popup suggestion actv invisible

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        apiInterface = ApiClient.getApiClient().create(CovoiturageService.class);
        trajetInterface = ApiClient.getApiClient().create(TrajetService.class);

        rechercheDepart = findViewById(R.id.recherche_depart);
        liste1 = findViewById(R.id.recherche_img1);
        rechercheDestination = findViewById(R.id.recherche_destination);
        liste2 = findViewById(R.id.recherche_img2);
        dateRecherchee = findViewById(R.id.recherche_calendrier);
        btnRechercher = findViewById(R.id.recherche_bouton);
        //Inflation rv
        rvCovoiturages = findViewById(R.id.recherche_rv);
        rvCovoiturages.setLayoutManager(new LinearLayoutManager(rvCovoiturages.getContext()));
        rvAdaptateur = new ListAdapteur(new ArrayList<>(), this);
        rvCovoiturages.setAdapter(rvAdaptateur);
        rvCovoiturages.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Adaptateurs AutoCompleteTextView
        String[] city = new String[]{"Québec", "Trois-Rivières", "Montréal", "Louiseville", "Laval", "Longueuil", "Saint-Hyacinthe", "Terrebonne", "Drummondvile", "Saint-Jean-sur-Richelieu", "Rimouski", "Mirabel"};
        Collections.addAll(adresses, city);
        ArrayAdapter<String> adressesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adresses);
        rechercheDepart.setAdapter(adressesAdapter);
        rechercheDestination.setAdapter(adressesAdapter);
        rechercheDepart.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    rechercheDestination.requestFocus();
                    return true;
                }
                return false;
            }
        });
        liste1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pop1) { //Si n'apparait pas
                    rechercheDepart.showDropDown();
                }
                else {
                    rechercheDepart.clearFocus();
                }
                pop1 = !pop1;
            }
        });
        liste2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pop2) { //Si n'apparait pas
                    rechercheDestination.showDropDown();
                }
                else {
                    rechercheDestination.clearFocus();
                }
                pop2 = !pop2;
            }
        });
        rechercheDestination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    rechercheDestination.requestFocus();
                    return true;
                }
                return false;
            }
        });

        //Instance bd
        bd = CovoiturageBd.getInstance(getApplicationContext());

        //Bouton sélectionner une date
        dateRecherchee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherCalendrier();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1; //January is 0
                date = new Date(year, month, dayOfMonth);
                String d = String.valueOf(dayOfMonth);
                String m = String.valueOf(month);
                if(dayOfMonth < 10){
                    d = "0" + d;
                }
                if(month < 10){
                    m = "0" + m;
                }
                dateSelectionnee = d + "/" + m + "/" + year;
                dateRecherchee.setText(dateSelectionnee);
            }
        };

        btnRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cacherClavier();
                rechercher();
            }
        });
    }

    private void rechercher() {
        if(rechercheDepart.getText().length() == 0 || rechercheDestination.getText().length() == 0 || dateSelectionnee == null)
            Toast.makeText(getApplicationContext(), "Veuillez renseigner tous les champs.", Toast.LENGTH_LONG).show();
        else if(date.before(Calendar.getInstance().getTime()))
            Toast.makeText(getApplicationContext(), "Date sélectionnée est passée.", Toast.LENGTH_LONG).show();
        else { //Tous les champs renseignés
            String s = rechercheDepart.getText().toString();
            String d = rechercheDestination.getText().toString();
            ControleurRecherche.rechercher(s, d, date, apiInterface, rvAdaptateur, getApplicationContext()); //Recherche les covoiturages correspondant
        }
    }

    public void afficherCalendrier(){
        Calendar calendrier = Calendar.getInstance();
        int year = calendrier.get(Calendar.YEAR);
        int month = calendrier.get(Calendar.MONTH);
        int day = calendrier.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                RechercheActivite.this,
                android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                dateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
        dialog.show();
    }

    //Méthode pour fermer clavier
    public void cacherClavier(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rechercheDepart.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(rechercheDestination.getWindowToken(), 0);
    }

    @Override
    public void onListClick(Covoiturage c) {
        if(popupCovoiturage == null) { //Première instance du popup
            popupCovoiturage = new PopUpCovoiturage(c, trajetInterface);
            popupCovoiturage.show(getSupportFragmentManager(), null);
        }
        else if(!popupCovoiturage.isVisible()){ //Si dialog non visible
            popupCovoiturage.setCovoiturage(c);
            popupCovoiturage.show(getSupportFragmentManager(), null);
        }
        else{
            //Affichage dialog en cours
        }
    }

}
