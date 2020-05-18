package com.exemple.Covoit.retrofit;

import com.exemple.Covoit.models.Covoiturage;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovoiturageService {

    //create a new carpool
    @GET("CarpoolOperations.php?type=create")
    Call<ResponseBody> insert(@Query("date") String date, @Query("ville_dep") String depart, @Query("ville_arr") String destination, @Query("prix") float prix, @Query("nb_passager") int nbPassager, @Query("conducteur_id") long conducteurId);

    //get carpool UI research
    @GET("CarpoolOperations.php?type=get")
    Call<List<Covoiturage>> get(@Query("depart") String depart, @Query("destination") String arrivee, @Query("date") String date, @Query("user") long id);

    //get carpool where passager
    @GET("CarpoolOperations.php?type=getPassager")
    Call<List<Covoiturage>> getPassager(@Query("passager") long passager);

    //get carpool where passager
    @GET("CarpoolOperations.php?type=getConducteur")
    Call<List<Covoiturage>> getConducteur(@Query("conducteur") long conducteur);

}
