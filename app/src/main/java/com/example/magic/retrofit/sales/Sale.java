package com.example.magic.retrofit.sales;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Sale {

    @POST("sales/v1")
    Call<ApiResponseSaleRegister> registerSale(@Body ApiSendSaleRegister sale);

}
