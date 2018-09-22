package com.andia.loice.movies.viewmodel;

import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.api.MovieRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MoviesFetchUseCase {
    private final MovieRepo movieRepo;

    @Inject
    public MoviesFetchUseCase(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public Flowable<List<Movie>> execute() {
        return movieRepo.getAll();
    }
}
