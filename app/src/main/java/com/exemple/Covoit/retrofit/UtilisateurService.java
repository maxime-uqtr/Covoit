package com.exemple.Covoit.retrofit;

import com.exemple.Covoit.models.Utilisateur;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UtilisateurService {

    //create a new user
    @GET("UserOperations.php?type=create")
    Call<ResponseBody> insert(@Query("nom") String nom, @Query("prenom") String prenom, @Query("mail") String mail, @Query("mdp") String mdp, @Query("telephone") String telephone, @Query("conducteur") int conducteur, @Query("passager") int passager);

    //get a single user
    @GET("UserOperations.php?type=getMdp")
    Call<List<Utilisateur>> getMdp(@Query("mail") String mail, @Query("mdp") String mdp);

    //get a single user
    @GET("UserOperations.php?type=get")
    Call<List<Utilisateur>> get(@Query("id") long id);

}
