package com.exemple.Covoit.retrofit;

import com.exemple.Covoit.models.Utilisateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UtilisateurService {

    //create a new user
    @GET("UserOperations.php")
    void insert(@Field("type") String type, @Field("nom") String nom, @Field("prenom") String prenom, @Field("mail") String mail, @Field("mdp") String mdp, @Field("telephone") String telephone, @Field("conducteur") String conducteur, @Field("passager") String passager, Callback<Response> callback);

    //get all users
    @GET("UserGet.php")
    Call<List<Utilisateur>> getAll();

    //get a single user
    @GET("UserOperations.php")
    Call<List<Utilisateur>> getMdp(@Query("type") String type, @Query("mail") String mail, @Query("mdp") String mdp);

}
