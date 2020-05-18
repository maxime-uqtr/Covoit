package com.exemple.Covoit.controleur;

import android.content.Context;
import android.util.Log;

import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.CovoiturageService;
import com.exemple.Covoit.retrofit.UtilisateurService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControleurTrajets {

    public static void updateRides(ListAdapteur adaptateur, CovoiturageService apiInterface, Context context) {
        adaptateur.clear(); //On vide l'adaptateur
        long sessionU = UtilisateurActuel.getUtilisateur().getId(); //Id de l'utilisateur
        Call<List<Covoiturage>> call = apiInterface.getPassager(sessionU);
        call.enqueue(new Callback<List<Covoiturage>>() {
            @Override
            public void onResponse(Call<List<Covoiturage>> call, Response<List<Covoiturage>> response) {
                List<Covoiturage> covs = response.body();
                CovoiturageBd bd = CovoiturageBd.getInstance(context);
                bd.getUtilisateurDao().insert(UtilisateurActuel.getUtilisateur());
                UtilisateurService utilisateurInterface = ApiClient.getApiClient().create(UtilisateurService.class);
                for(Covoiturage c : covs){ //On insère les covoiturages
                    if(bd.getUtilisateurDao().get(c.getConducteurId()) == null) {
                        Call<List<Utilisateur>> appe = utilisateurInterface.get(c.getConducteurId()); //On get utilisateurs FK
                        appe.enqueue(new Callback<List<Utilisateur>>() {
                            @Override
                            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                                List<Utilisateur> user = response.body();
                                for (Utilisateur u : user) { //On insère les conducteurs
                                    bd.getUtilisateurDao().insert(u);
                                }
                                bd.getCovoiturageDao().insert(c); //On insère les covoiturages
                                //On maj l'adaptateur
                                adaptateur.add(bd.getCovoiturageDao().get(c.getId()));
                            }

                            @Override
                            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {

                            }
                        });
                    }else{//Sinon on insère covoiturage
                        bd.getCovoiturageDao().insert(c); //On insère les covoiturages
                        //On maj l'adaptateur
                        adaptateur.add(bd.getCovoiturageDao().get(c.getId()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Covoiturage>> call, Throwable t) {
                Log.i("TAG1", call.toString() + t.toString());
            }
        });
    }
}
