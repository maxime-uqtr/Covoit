package com.exemple.Covoit.vue;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.Covoit.controleur.ListAdapteur;
import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.models.Covoiturage;

import java.util.Calendar;
import java.util.Date;

public class RechercheActivite extends AppCompatActivity implements OnListClickListener {

    private CovoiturageBd bd;

    private AutoCompleteTextView rechercheDepart;
    private AutoCompleteTextView rechercheDestination;
    private String[] adresses = new String[]{"Québec", "Trois-Rivières", "Montréal", "Louiseville"};
    private TextView dateRecherchee;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Date dateSelectionnee;
    private Button btnRechercher;
    private RecyclerView rvCovoiturages;
    private ListAdapteur rvAdapteur;
    private TextView tvErreur;
    private PopUpCovoiturage popupCovoiturage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        rechercheDepart = findViewById(R.id.recherche_depart);
        rechercheDestination = findViewById(R.id.recherche_destination);
        dateRecherchee = findViewById(R.id.recherche_calendrier);
        btnRechercher = findViewById(R.id.recherche_bouton);
        //Inflation rv
        rvCovoiturages = findViewById(R.id.recherche_rv);
        rvCovoiturages.setLayoutManager(new LinearLayoutManager(rvCovoiturages.getContext()));
        rvCovoiturages.setAdapter(null);
        rvCovoiturages.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        tvErreur = findViewById(R.id.recherche_vide);

        //Adapter AutoCompleteTextView
        ArrayAdapter<String> adressesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, adresses);
        rechercheDepart.setAdapter(adressesAdapter);
        rechercheDestination.setAdapter(adressesAdapter);

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
                    String depart = rechercheDepart.getText().toString();
                    String destination = rechercheDestination.getText().toString();
                    Date date = dateSelectionnee;
                    rvAdapteur = new ListAdapteur(bd.getCovoiturageDao().getLike(depart, destination, date), RechercheActivite.this::onListClick);
                    if(rvAdapteur.getItemCount()==0){//Message d'erreur
                        rvCovoiturages.setVisibility(View.GONE);
                        tvErreur.setVisibility(View.VISIBLE);
                    }
                    else{
                        rvCovoiturages.setVisibility(View.VISIBLE);
                        tvErreur.setVisibility(View.GONE);
                    }
                    rvCovoiturages.setAdapter(rvAdapteur);
                }
            }
        });
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
            popupCovoiturage = new PopUpCovoiturage(c);
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

    public void appelerConducteur(String numero){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.fromParts("tel", numero, null));
            startActivity(intent);
        }
    }

}
