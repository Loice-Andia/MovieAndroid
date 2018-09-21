package com.andia.loice.movies.model.db.ApiDao;

import com.andia.loice.movies.model.data.MovieResponse;

import io.reactivex.Observable;

public interface NetworkDataManager {

    Observable<MovieResponse> fetchMovies();
}
