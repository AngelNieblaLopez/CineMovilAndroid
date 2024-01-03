package com.example.magic.retrofit.functions;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseScheduleFunctions {

    @SerializedName("error")
    public String error;

    @SerializedName("data")
    public List<ApiScheduleFunctions>  functions;

}
