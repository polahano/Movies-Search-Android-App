package com.example.moviesapp.response;


import com.example.moviesapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// this class is for single movie request
public class MovieResponse {
    // 1- finding the movie object
    @SerializedName("results")
    @Expose()
    private MovieModel movie;

    public MovieModel getMovie(){
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
