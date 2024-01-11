package com.example.magic.retrofit.sales;

import com.example.magic.retrofit.movies.ApiResponseMovieDetail;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Sale {

    @POST("sales/v1")
    Call<ApiResponseSaleRegister> registerSale(@Body ApiSendSaleRegister sale);

    @GET("sales/v1/client/{id}")
    Call<ApiResponseSaleClientBuyed> buyedClient(
            @Path("id") int id
    );

}
