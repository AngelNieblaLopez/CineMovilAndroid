package com.example.magic.retrofit.sales;


import com.example.magic.retrofit.functions.ApiScheduleFunctions;
import com.example.magic.retrofit.movies.ApiMovieDetail;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseSaleClientBuyed {


    @SerializedName("error")
    public String error;

    @SerializedName("data")
    public List<ApiSaleClientDetail> saleClientDetail;
}
