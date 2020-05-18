package com.exemple.Covoit.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TrajetService {

    //create a new carpool
    @GET("RideOperations.php?type=create")
    Call<ResponseBody> insert(@Query("covoiturage_id") long covoiturage, @Query("passager_id") long passager);

}
