package com.example.moviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListner onMovieListner;

    public MovieRecyclerView(OnMovieListner onMovieListner) {
        this.onMovieListner = onMovieListner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view, onMovieListner);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        ((MovieViewHolder) holder).title.setText(mMovies.get(i).getTitle());
        ((MovieViewHolder) holder).release_date.setText(mMovies.get(i).getRelease_date());
        ((MovieViewHolder) holder).original_language.setText(mMovies.get(i).getOriginal_language());
        ((MovieViewHolder) holder).ratingBar.setRating((mMovies.get(i).getVote_average()) / 2);

        //ImageView Using Glide Library
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/"+mMovies.get(i).getPoster_path())
                .into(((MovieViewHolder) holder).imageView);

    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }


public MovieModel getSelectedMovie(int position){
        if(mMovies !=null){
            if(mMovies.size() > 0){
                return mMovies.get(position);
            }
        }
    return null;
}
}
