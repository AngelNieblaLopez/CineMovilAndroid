package com.example.magic.retrofit.movies;

import com.google.gson.annotations.SerializedName;

public class ApiMovieDetail {

    @SerializedName("movie_name")
    public String name;

    @SerializedName("movie_description")
    public String description;

    @SerializedName("category_name")
    public String categoryName;

    @SerializedName("movie_clasification_name")
    public String clasificationName;

    @SerializedName("category_description")
    public String categoryDescription;

    @SerializedName("movie_image_url")
    public String imageUrl;
}
