package com.example.fbu_flixter.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String backdropPath;
    String posterPath;
    String title;
    String overview;
    double rating;
    int movie_id;

    public Movie(){}

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");

        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i=0; i< movieJsonArray.length();i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }
    public String  getPosterPath(){
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String  getTitle(){
        return title;
    }
    public double getRating() {
        return rating;
    }
    public int getMovieId() {
        return movie_id;
    }

    public String  getOverview(){
         return overview;
    }
         public String  getBackdropPath(){
         return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
   }

}
