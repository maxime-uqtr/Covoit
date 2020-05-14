package com.exemple.Covoit.controleur;

import android.content.Context;
import android.util.Log;

import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.UtilisateurService;

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
        Log.i("TAG1", nom + prenom + mail + mdp + telephone + c + p);

        apiInterface = ApiClient.getApiClient().create(UtilisateurService.class);
        apiInterface.insert("create", "Covoit", "Admin", "admin@covoit.com", "admin", "8012", "1", "0", new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                Log.i("TAG1", "inse");
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.i("TAG1", call.toString() + t.toString());
            }
        });
        /*apiInterface.insert("create", nom, prenom, mail, mdp, telephone, c, p, new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                Log.i("TAG1", "inse");
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.i("TAG1", call.toString() + t.toString());
            }
        });*/
        CovoiturageBd.getInstance(context).getUtilisateurDao().insert(utilisateur);
    }

}
