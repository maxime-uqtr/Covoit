package com.exemple.Covoit.controleur;

import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.UtilisateurService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControleurConnexion {

    private static UtilisateurService apiInterface;
    private static UtilisateurActuel u;

    public static void connexion(String mail, String mdp) {
        apiInterface = ApiClient.getApiClient().create(UtilisateurService.class);
        Call<List<Utilisateur>> call = apiInterface.getMdp("getMdp", mail, mdp);
        call.enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                List<Utilisateur> user = response.body();
                UtilisateurActuel.setUtilisateur(user.get(0)); //On set l'utilisateur
                //Log.i(  "TAG1", "conn" + String.valueOf(UtilisateurActuel.isInst()));
            }

            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {
                //Log.i("TAG1", call.toString() + t.toString());
            }
        });

    }
}