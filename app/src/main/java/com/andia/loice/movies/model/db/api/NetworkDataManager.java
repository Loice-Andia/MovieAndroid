package com.andia.loice.movies.model.db.api;

import com.andia.loice.movies.model.data.MovieResponse;

import io.reactivex.Observable;

public interface NetworkDataManager {

    Observable<MovieResponse> fetchMovies();
}
