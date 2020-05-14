package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.ControleurInscription;

public class InscriptionActivite extends AppCompatActivity {

    //  Boutton : b; EditText : et; CheckBox : cb;
    private Button bValider, bRetour;
    private EditText etNom, etPrenom, etMail, etTelephone, etMdp, etVerifMdp;
    private CheckBox cbPassager, cbConducteur;

    private CovoiturageBd bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        bd = CovoiturageBd.getInstance(getApplicationContext());

        etNom = findViewById(R.id.editTextNom);
        etPrenom = findViewById(R.id.editTextPrenom);
        etMail = findViewById(R.id.editTextMail);
        etTelephone = findViewById(R.id.inscription_tel);

        etMdp = findViewById(R.id.editTextMdp);
        etVerifMdp = findViewById(R.id.editTextVerifMdp);

        cbPassager = findViewById(R.id.checkBoxPassager);
        cbConducteur = findViewById(R.id.checkBoxConducteur);

        bValider = findViewById(R.id.buttonValider);
        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNom.getText().toString().isEmpty() || etPrenom.getText().toString().isEmpty() || etMail.getText().toString().isEmpty() || etTelephone.getText().toString().isEmpty() /*|| !etMdp.equals(etVerifMdp)*/ || etMdp.getText().toString().isEmpty() || etVerifMdp.getText().toString().isEmpty() || (cbPassager.isChecked() == false && cbConducteur.isChecked() == false)){
                    Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG);
                }
                else{
                    ControleurInscription.inscription(etNom.getText().toString(), etPrenom.getText().toString(), etMail.getText().toString(), etMdp.getText().toString(), etTelephone.getText().toString(), cbPassager.isChecked(), cbConducteur.isChecked(), getApplicationContext());
                    Intent i = new Intent(getApplicationContext(), ConnexionActivite.class);
                    startActivity(i);
                }
            }
        });

        bRetour = findViewById(R.id.buttonRetour);
        bRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ConnexionActivite.class);
                startActivity(i);
            }
        });
    }

}