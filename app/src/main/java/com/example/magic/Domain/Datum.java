package com.example.magic.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.magic.retrofit.movies.GenresAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("movie_name")
    @Expose
    public String title;
    @SerializedName("movie_image_url")
    @Expose
    public String poster;

    public String year;

    public String country;

    public String imdbRating;
    @SerializedName("category_description")
    @Expose
    public String category;

    public List<String> genres;


    public List<String> images;


    public Datum() {
        this.year = generateRandomNumber(2010, 2020);
        this.imdbRating =  generateRandomNumber(5, 9);
        this.country = "EE.UU";
    }

    public void tranformFinalData() {
        List<String> categoryList = new ArrayList<>();
        categoryList.add(this.category);

        this.genres = categoryList;

        List<String> moviesList = new ArrayList<>();
        moviesList.add(this.poster);

        this.images = moviesList;
    }
    public static  String  generateRandomNumber(int inicio, int fin) {
        Random random = new Random();
        int randomNumber = random.nextInt(fin - inicio + 1) + inicio;
        return String.valueOf(randomNumber);


    }
}


