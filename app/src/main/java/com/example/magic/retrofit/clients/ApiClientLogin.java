package com.example.magic.retrofit.clients;

import com.google.gson.annotations.SerializedName;

public class ApiClientLogin {
    @SerializedName("id")
    public int id;


    public ApiClientLogin(int id) {
        this.id = id;
    }


}
