package com.example.moviesapp.utils;

import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.response.MovieSearchResponse;
import com.google.gson.annotations.Expose;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //search for movies
    //https://api.themoviedb.org/3/search/movie?api_key=ee2a56d9695ab0f0f18c1fc03ba1655e&query=Jack+Reacher
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String key,
                                          @Query("query") String query,
                                          @Query("page") int page);

@GET("/3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key);



}
