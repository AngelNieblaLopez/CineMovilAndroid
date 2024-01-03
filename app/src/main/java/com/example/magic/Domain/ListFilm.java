package com.example.magic.Domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFilm {

    @SerializedName("error")
    public String error;
    @SerializedName("data")
    public List<Datum> data;

}
