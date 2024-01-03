package com.example.magic.retrofit.movies;

import com.example.magic.Domain.ListFilm;
import com.example.magic.retrofit.functions.ApiResponseScheduleFunctions;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Movie {
    @GET("movies/v1/with_function")
    Call<ListFilm> withFunction();

    @GET("movies/v1/{id}")
    Call<ApiResponseMovieDetail> detail(
            @Path("id") int id
    );
}
