package com.example.moviesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MovieModel implements Parcelable {
    //model class for our movies

    private int id;
    private String overview;
    private String poster_path;
    private String release_date;
    private String title;
    private float vote_average;
    private String original_language;

    public MovieModel(int id, String overview, String poster_path, String release_date, String title, float vote_average, String original_language) {
        this.id = id;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
        this.vote_average = vote_average;
        this.original_language = original_language;
    }

    protected MovieModel(Parcel in) {
        id = in.readInt();
        overview = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        title = in.readString();
        vote_average = in.readFloat();
        original_language = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getId() {
        return id;
    }


    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeString(title);
        dest.writeFloat(vote_average);
        dest.writeString(original_language);
    }
}
