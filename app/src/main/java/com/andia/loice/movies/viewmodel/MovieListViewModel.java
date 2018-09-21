package com.andia.loice.movies.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.DataSource;

import java.util.List;

import javax.inject.Inject;

public class MovieListViewModel extends ViewModel {
    private DataSource dataSource;

    @Inject
    public MovieListViewModel(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void insert(Movie movie){
        dataSource.insertMovie(movie);
    }

    public void delete(Movie movie){
        dataSource.deleteMovie(movie);
    }

    public LiveData<List<Movie>> getAllMovies(){
        return dataSource.getMovieLiveData();
    }
}
