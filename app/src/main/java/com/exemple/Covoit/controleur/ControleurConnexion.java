package com.exemple.Covoit.controleur;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.UtilisateurService;
import com.exemple.Covoit.vue.AccueilActivite;
import com.exemple.Covoit.vue.PopUpConnexion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControleurConnexion {

    public static void connexion(String mail, String mdp, UtilisateurService apiInterface, Context context) {
        Call<List<Utilisateur>> call = apiInterface.getMdp(mail, mdp);
        call.enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                List<Utilisateur> user = response.body();
                CovoiturageBd bd = CovoiturageBd.getInstance(context);
                bd.getUtilisateurDao().insert(user.get(0)); //On insère user BD
                UtilisateurActuel.setUtilisateur(user.get(0)); //On set l'utilisateur
                Intent i = new Intent(context, AccueilActivite.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }

            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {
                Log.i("TAG1", call.toString() + t.toString());
                Intent i = new Intent(context, PopUpConnexion.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }
}