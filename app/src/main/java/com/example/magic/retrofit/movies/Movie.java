package com.example.magic.retrofit.movies;

import com.example.magic.Domain.ListFilm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Movie {
    @GET("movies/v1/with_function")
    Call<ListFilm> withFunction();
}
