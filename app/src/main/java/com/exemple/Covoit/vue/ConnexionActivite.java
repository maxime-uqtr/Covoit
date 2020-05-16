package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.TelechargerImage;

import java.util.concurrent.ExecutionException;

public class ConnexionActivite extends AppCompatActivity {

    //  Boutton : b; EditText : et;
    private Button bConnection, bInscription;
    private EditText etMail, etMdp;
    private ImageView logo;

    private CovoiturageBd bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        etMail = findViewById(R.id.connexion_editTextMail);
        etMdp = findViewById(R.id.connexion_editTextMail);

        logo = findViewById(R.id.connexion_logo);
        bConnection = findViewById(R.id.connexion_btnConnexion);

        bConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ControleurConnexion.connexion(etMail.getText().toString(), etMdp.getText().toString());
                /*Log.i("TAG1", String.valueOf(UtilisateurActuel.isInst()));
                Timer timer = new Timer();
                final int[] secondes = {0};
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        secondes[0] += 1;
                    }
                }, 0, 1000);
                while(secondes[0] <2){}//On attend fin connexion
                Log.i("TAG1", String.valueOf(UtilisateurActuel.isInst()));
                if(!UtilisateurActuel.isInst()) { //Mauvais mail et mdp
                    Intent i = new Intent(getApplicationContext(), PopUpConnexion.class);
                    startActivity(i);
                }
                else { //On lance l'activité
                    Intent i = new Intent(getApplicationContext(), AccueilActivite.class);
                    startActivity(i);
                }*/
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
