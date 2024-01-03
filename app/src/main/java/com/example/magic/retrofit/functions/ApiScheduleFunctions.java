package com.example.magic.retrofit.functions;

import com.google.gson.annotations.SerializedName;

public class ApiScheduleFunctions {

    @SerializedName("id")
    public String id;

    @SerializedName("start_date")
    public String startDate;

    public ApiScheduleFunctions(String id, String startDate) {
        this.id = id;
        this.startDate = startDate;
    }
}
