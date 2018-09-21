package com.andia.loice.movies.model.db;

import com.andia.loice.movies.model.data.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("discover/movie")
    Call<MovieResponse> getAllMovies();
}
