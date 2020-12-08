package com.example.moviesapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.request.MovieApiClient;
import com.example.moviesapp.utils.MovieApi;

import java.util.List;

public class MovieRepository {

    private MovieApiClient movieApiClient;
    private String mQuery;
    private int mPageNumber;
    //singelton pattern
    private static MovieRepository instance;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }

    //calling the method in repository
    public void searchMovieApi(String query, int pageNumber) {

        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMovieApi(query, pageNumber);
    }

    public void searchNextPage(){
        searchMovieApi(mQuery,mPageNumber+1);
    }



}
