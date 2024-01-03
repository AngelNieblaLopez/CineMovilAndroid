package com.example.magic.retrofit.functions;

import com.example.magic.Domain.ListFilm;
import com.example.magic.retrofit.functions.ApiResponseScheduleFunctions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Function {


    @GET("functions/v1/available_by_movie/{id}")
    Call<ApiResponseScheduleFunctions> availableByMovie(
            @Path("id") String id
    );
}
