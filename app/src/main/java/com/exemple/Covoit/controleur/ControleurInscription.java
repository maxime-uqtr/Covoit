package com.exemple.Covoit.controleur;

import android.content.Context;

import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.UtilisateurService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControleurInscription {

    public static void inscription(String nom, String prenom, String mail, String mdp, String telephone, boolean passager, boolean conducteur, Context context) {
        Utilisateur utilisateur = new Utilisateur(nom, prenom, mail, mdp, telephone, passager, conducteur);
        UtilisateurService apiInterface = ApiClient.getApiClient().create(UtilisateurService.class);
        int c = 0;
        int p = 0;
        if(conducteur){
            c = 1;
        }
        if(passager){
            p = 1;
        }

        Call<ResponseBody> resp = apiInterface.insert(nom, prenom, mail, mdp, telephone, c, p);
        resp.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });


        CovoiturageBd.getInstance(context).getUtilisateurDao().insert(utilisateur);
    }

}
