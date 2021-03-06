package com.andia.loice.movies.model.db;

import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.dao.MovieDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class DataSource {
    private Flowable<List<Movie>> movieLiveData;
    private MovieDao movieDao;

    @Inject
    DataSource(AppDatabase appDatabase){
        movieDao = appDatabase.movieDao();
        movieLiveData = appDatabase.movieDao().getAllMovies();
    }

    public void insertMovie(Movie movie){
        movieDao.insertMovie(movie);
    }

    public void deleteMovie(Movie movie){
        movieDao.deleteMovie(movie);
    }

    public void insertMovieList(List<Movie> movieList) {
        movieDao.insertMovieList(movieList);
    }

    public Flowable<List<Movie>> getMovieLiveData() {
        return movieLiveData;
    }
}
