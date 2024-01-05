package com.example.magic.retrofit.seats;

import com.example.magic.Domain.ListFilm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Seat {

    @GET("seats/v1/{id}")
    Call<ApiResponseSeatsAvailable> availableSeats(
            @Path("id") String id
    );
}
