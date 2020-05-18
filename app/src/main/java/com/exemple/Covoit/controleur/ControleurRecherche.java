package com.exemple.Covoit.controleur;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.exemple.Covoit.bd.CovoiturageBd;
import com.exemple.Covoit.models.Covoiturage;
import com.exemple.Covoit.models.Utilisateur;
import com.exemple.Covoit.models.UtilisateurActuel;
import com.exemple.Covoit.retrofit.ApiClient;
import com.exemple.Covoit.retrofit.CovoiturageService;
import com.exemple.Covoit.retrofit.TrajetService;
import com.exemple.Covoit.retrofit.UtilisateurService;
import com.exemple.Covoit.vue.AccueilActivite;
import com.exemple.Covoit.vue.PopUpCovoiturage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ControleurRecherche {


    public static void rechercher(String s, String d, Date dateSelectionnee, CovoiturageService apiInterface, final ListAdapteur adaptateur, Context applicationContext) {
        adaptateur.clear(); //On vide l'adaptateur
        String jour = String.valueOf(dateSelectionnee.getDate()); //On prépare string date
        String mois = String.valueOf(dateSelectionnee.getMonth()); //Mois commence à zéro
        if(dateSelectionnee.getDate() < 10){ //Respecter la syntaxe Date
            jour = "0" + jour;
        }
        if(dateSelectionnee.getMonth() < 10){
            mois = "0" + mois;
        }
        String date = jour + "/" + mois + "/" + dateSelectionnee.getYear();
        long sessionU = UtilisateurActuel.getUtilisateur().getId(); //Id de l'utilisateur
        Call<List<Covoiturage>> call = apiInterface.get(s, d, date, sessionU);
        call.enqueue(new Callback<List<Covoiturage>>() {
            @Override
            public void onResponse(Call<List<Covoiturage>> call, Response<List<Covoiturage>> response) {
                List<Covoiturage> covs = response.body();
                UtilisateurService utilisateurInterface = ApiClient.getApiClient().create(UtilisateurService.class);
                final CovoiturageBd bd = CovoiturageBd.getInstance(applicationContext);
                List<Long> driver = new ArrayList<>();
                for(Covoiturage c : covs){ //On insère les covoiturages
                    long conducteur = c.getConducteurId();
                    if(!driver.contains(conducteur)) {
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
                                driver.add(conducteur); //On a ajoute conducteur
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

    public static void adherer(PopUpCovoiturage popUpCovoiturage, Covoiturage covoiturage, TrajetService apiInterface, Context applicationContext) {
        Call<ResponseBody> call = apiInterface.insert(covoiturage.getId(), UtilisateurActuel.getUtilisateur().getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Intent i = new Intent(applicationContext, AccueilActivite.class);
                applicationContext.startActivity(i);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                popUpCovoiturage.dismiss();
            }
        });
    }
}
