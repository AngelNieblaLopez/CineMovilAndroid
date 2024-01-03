package com.example.magic.retrofit.clients;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Client {
    @GET("clients/v1/login")
    Call<ApiResponseClientLogin> loginClient(
            @Query("password") String password,
            @Query("email") String email
    );



    @POST("clients/v1")
    Call<ApiResponseClientRegister> registerUser( @Body ApiSendClientRegister client);

}
