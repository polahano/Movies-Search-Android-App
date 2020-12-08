package com.example.moviesapp.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    TextView title, release_date, original_language;
    ImageView imageView;
    RatingBar ratingBar;

    OnMovieListner onMovieListner;

    public MovieViewHolder(@NonNull View itemView,OnMovieListner onMovieListner) {
        super(itemView);

        this.onMovieListner = onMovieListner;

        title = itemView.findViewById(R.id.movie_title);
        release_date = itemView.findViewById(R.id.movie_release_date);
        original_language= itemView.findViewById(R.id.movie_original_language);
        imageView = itemView.findViewById(R.id.movie_img);
        ratingBar = itemView.findViewById(R.id.rating_bar);

       itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
onMovieListner.onMovieClick(getAdapterPosition());
    }
}
