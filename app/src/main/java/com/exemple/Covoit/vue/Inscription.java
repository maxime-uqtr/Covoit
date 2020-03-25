package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.controleurInscription;
import com.exemple.Covoit.models.Utilisateur;

public class Inscription extends AppCompatActivity implements controleurInscription {

    //  Boutton : b; EditText : et; CheckBox : cb;
    private Button bValider;
    private EditText etNom, etPrenom, etMail, etMdp, etVerifMdp;
    private CheckBox cbPassager, cbConducteur;

    private Utilisateur utilisateur;

    private CovoiturageBd bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        bd = CovoiturageBd.getInstance(getApplicationContext());

        etNom = findViewById(R.id.editTextNom);
        etPrenom = findViewById(R.id.editTextPrenom);
        etMail = findViewById(R.id.editTextMail);
        etMdp = findViewById(R.id.editTextMdp);
        etVerifMdp = findViewById(R.id.editTextVerifMdp);

        cbPassager = findViewById(R.id.checkBoxPassager);
        cbConducteur = findViewById(R.id.checkBoxConducteur);

        bValider = findViewById(R.id.buttonValider);
        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean test = inscription(etNom.getText().toString(), etPrenom.getText().toString(), etMail.getText().toString(), etMdp.getText().toString(), etVerifMdp.getText().toString(), cbPassager.isChecked(), cbConducteur.isChecked());

                if (test){
                    utilisateur = new Utilisateur(Long.valueOf(10), etNom.getText().toString(), etPrenom.getText().toString(), etMail.getText().toString(), etMdp.getText().toString(), "null" ,cbPassager.isChecked(), cbConducteur.isChecked());
                    bd.getUtilisateurDao().insert(utilisateur);
                }else{
                    Intent i = new Intent(getApplicationContext(), PopActivity.class);
                    startActivity(i);
                }
            }
        });
    }


    @Override
    public boolean inscription(String nom, String prenom, String mail, String mdp, String verifMdp, boolean passager, boolean conducteur) {
        if(passager == false && conducteur == false)
            return false;
        if (!mdp.equals(verifMdp) || mdp.isEmpty())
            return false;
        return true;
    }
}