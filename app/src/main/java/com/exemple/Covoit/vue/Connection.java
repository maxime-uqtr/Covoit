package com.exemple.Covoit.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.exemple.Covoit.R;
import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.controleur.controleurConnection;

public class Connection extends AppCompatActivity implements controleurConnection {

    //  Boutton : b; EditText : et;
    private Button bConnection, bInscription;
    private EditText etMail, etMdp;

    private CovoiturageBd bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        etMail = findViewById(R.id.editTextMail);
        etMdp = findViewById(R.id.editTextMdp);

        bConnection = findViewById(R.id.buttonConnection);
        bConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connection(etMail.getText().toString(), etMdp.getText().toString())){
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(), PopActivity.class);
                    startActivity(i);
                }
            }
        });

        bInscription = findViewById(R.id.buttonInscription);
        bInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Inscription.class);
                startActivity(i);
            }
        });

        bd = CovoiturageBd.getInstance(getApplicationContext());
    }

    @Override
    public boolean connection(String mail, String mdp) {
        long test = -1;
        test = bd.getUtilisateurDao().getIdConnection(mail, mdp);
        if(test <= 0){
            return false;
        }
        return true;
    }
}
