package com.example.moviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviesapp.adapters.MovieRecyclerView;
import com.example.moviesapp.adapters.OnMovieListner;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.request.Servicey;
import com.example.moviesapp.response.MovieSearchResponse;
import com.example.moviesapp.utils.Credentials;
import com.example.moviesapp.utils.MovieApi;
import com.example.moviesapp.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListner {

    //RecyclerView
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerViewAdapter;

    //ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar - if have an error comment next 2 lines
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //search view
        setupSearchView();

        recyclerView = findViewById(R.id.recycler_view);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        configureRecyclerView();
        //calling the observers
        observeAnyChange();

//        //Testing the method
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchMovieApi("fast",1);
//            }
//        });

    }

    //Observing data changes
    private void observeAnyChange() {

        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //Observing for any changing data
                if(movieModels != null){
                    for(MovieModel movieModel:movieModels){
                        //get the data in log
                        movieRecyclerViewAdapter.setmMovies(movieModels);

                    }
                }
            }
        });

    }

//    //calling method in main activity
//    private void searchMovieApi(String query,int pageNumber){
//        movieListViewModel.searchMovieApi(query, pageNumber);
//    }

    // initializing recyclerView and adding data to it
    private void configureRecyclerView(){
        movieRecyclerViewAdapter = new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ده عشان عايز ابين كل النتايج مش اللي ف الصفحة الاولى بس
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    movieListViewModel.searchNextPage();

                }
            }
        });
    }

    private void getRetrofitResponse() {
        MovieApi movieApi = Servicey.getMovieApi();

        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Credentials.API_KEY, "Jack Reacher", 1);

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    Log.v("Tag", "the response" + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for (MovieModel movie : movies) {
                        Log.v("Tag", "The List" + movie.getRelease_date());
                    }
                } else {
                    try {
                        Log.i("Tag", "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });


    }

    private void getRetrofitResponseAccordingToID() {
        MovieApi movieApi = Servicey.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(550, Credentials.API_KEY);

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.code() == 200) {
                    MovieModel movie = response.body();
                    Log.v("Tag", "The Response" + movie.getTitle());

                } else {
                    try {
                        Log.v("Tag", "Error" + response.errorBody().string()
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMovieClick(int position) {

Intent intent = new Intent(this,MovieDetails.class);
intent.putExtra("movie",movieRecyclerViewAdapter.getSelectedMovie(position));
startActivity(intent);

    }

    @Override
    public void onCategoryClick(String category) {

    }

    //search view
    private void setupSearchView(){
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}