package com.exemple.Covoit.retrofit;

import com.exemple.Covoit.models.Utilisateur;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UtilisateurService {

    //create a new user
    @GET("UserOperations.php")
    Call<ResponseBody> insert(@Query("type") String type, @Query("nom") String nom, @Query("prenom") String prenom, @Query("mail") String mail, @Query("mdp") String mdp, @Query("telephone") String telephone, @Query("conducteur") int conducteur, @Query("passager") int passager);

    //get all users
    @GET("UserGet.php")
    Call<List<Utilisateur>> getAll();

    //get a single user
    @GET("UserOperations.php")
    Call<List<Utilisateur>> getMdp(@Query("type") String type, @Query("mail") String mail, @Query("mdp") String mdp);

}
