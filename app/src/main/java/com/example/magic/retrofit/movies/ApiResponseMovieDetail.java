package com.example.magic.retrofit.movies;

import com.google.gson.annotations.SerializedName;

public class ApiResponseMovieDetail {

    @SerializedName("error")
    public String error;

    @SerializedName("data")
    public ApiMovieDetail movieDetail;

}
