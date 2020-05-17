package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.ControleurConnexion;
import com.exemple.Covoit.controleur.TelechargerImage;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.UtilisateurService;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class ConnexionActivite extends AppCompatActivity {

    //  Boutton : b; EditText : et;
    private Button bConnexion, bInscription;
    private EditText etMail, etMdp;
    private ImageView logo;

    public static UtilisateurService apiInterface;
    private CovoiturageBd bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        apiInterface = ApiClient.getApiClient().create(UtilisateurService .class);

        etMail = findViewById(R.id.connexion_editTextMail);
        etMdp = findViewById(R.id.connexion_editTextMdp);

        logo = findViewById(R.id.connexion_logo);
        bConnexion = findViewById(R.id.connexion_btnConnexion);

        bConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControleurConnexion.connexion(etMail.getText().toString(), etMdp.getText().toString(), apiInterface, getApplicationContext());
            }
        });

        bInscription = findViewById(R.id.connexion_btnInscription);
        bInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InscriptionActivite.class);
                startActivity(i);
            }
        });

        String urlLogo = "https://covoituragebd-7356.restdb.io/media/5e7a8375cf927e3e0001bc30";
        try {
            logo.setImageBitmap(new TelechargerImage().execute(urlLogo).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
