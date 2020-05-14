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

    public static UtilisateurService apiInterface;
    public static Utilisateur utilisateur;

    public static void inscription(String nom, String prenom, String mail, String telephone, String mdp, boolean passager, boolean conducteur, Context context) {
        utilisateur = new Utilisateur(nom, prenom, mail, mdp, telephone, passager, conducteur);
        apiInterface = ApiClient.getApiClient().create(UtilisateurService.class);
        int c = 0;
        int p = 0;
        if(conducteur){
            c = 1;
        }
        if(passager){
            p = 1;
        }

        apiInterface = ApiClient.getApiClient().create(UtilisateurService.class);
        Call<ResponseBody> resp = apiInterface.insert("create", nom, prenom, mail, mdp, telephone, c, p);
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
