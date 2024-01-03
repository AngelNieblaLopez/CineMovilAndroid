package com.example.magic.retrofit.clients;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponseClientLogin {
    @SerializedName("error")
    public String error;

    @SerializedName("data")
    public ApiClientLogin client;

    public ApiResponseClientLogin(String error, ApiClientLogin client) {
        this.error = error;
        this.client = client;
    }


}
