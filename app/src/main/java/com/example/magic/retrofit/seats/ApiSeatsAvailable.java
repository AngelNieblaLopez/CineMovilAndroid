package com.example.magic.retrofit.seats;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;

public class ApiSeatsAvailable {

    @SerializedName("buyedSeatIds")
    public int[] buyedSeatIds;

    @SerializedName("availableSeatIds")
    public int[] availableSeatIds;

}
