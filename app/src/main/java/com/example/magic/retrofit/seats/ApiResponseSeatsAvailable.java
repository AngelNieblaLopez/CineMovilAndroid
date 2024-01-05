package com.example.magic.retrofit.seats;

import com.google.gson.annotations.SerializedName;

public class ApiResponseSeatsAvailable {

    @SerializedName("error")
    public String error;

    @SerializedName("data")
    public ApiSeatsAvailable seatsAvailable;

}
