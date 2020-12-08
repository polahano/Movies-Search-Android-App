package com.example.moviesapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieRepository.getMovies();
    }

    //calling method in view-model
    public void searchMovieApi(String query,int pageNumber){
        movieRepository.searchMovieApi(query, pageNumber);
    }

public void searchNextPage(){
        movieRepository.searchNextPage();
}

}
