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
import com.exemple.Covoit.controleur.ControleurConnexion;

import java.util.concurrent.ExecutionException;

public class ConnexionActivite extends AppCompatActivity implements ControleurConnexion {

    //  Boutton : b; EditText : et;
    private Button bConnection, bInscription;
    private EditText etMail, etMdp;
    private ImageView logo;

    private CovoiturageBd bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        etMail = findViewById(R.id.editTextMail);
        etMdp = findViewById(R.id.editTextMdp);

        logo = findViewById(R.id.connexion_logo);
        bConnection = findViewById(R.id.connexion_btnConnexion);

        bConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connexion(etMail.getText().toString(), etMdp.getText().toString())){
                    Intent i = new Intent(getApplicationContext(), AccueilActivite.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(), PopUpConnexion.class);
                    startActivity(i);
                }
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

        bd = CovoiturageBd.getInstance(getApplicationContext());
    }

    @Override
    public boolean connexion(String mail, String mdp) {
        long test = -1;
        test = bd.getUtilisateurDao().getIdConnection(mail, mdp);
        return test > 0;
    }
}
