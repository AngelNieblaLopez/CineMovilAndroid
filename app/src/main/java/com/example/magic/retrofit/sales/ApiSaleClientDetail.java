package com.example.magic.retrofit.sales;

import com.google.gson.annotations.SerializedName;

public class ApiSaleClientDetail {

    @SerializedName("sale")
    public ApiSaleClientDetailFunction apiSaleClientDetailFunction;

    @SerializedName("list_seats_names")
    public String seats_names;

}
