package com.andia.loice.movies.model.db.api;

import com.andia.loice.movies.model.data.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("discover/movie")
    Observable<MovieResponse> getAllMovies();
}
