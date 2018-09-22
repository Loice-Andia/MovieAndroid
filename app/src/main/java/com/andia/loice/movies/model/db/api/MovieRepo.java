package com.andia.loice.movies.model.db.api;

import com.andia.loice.movies.dagger.scheduler.SchedulerProvider;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.DataSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class MovieRepo {

    @Inject
    ApiService apiService;

    @Inject
    DataSource dataSource;

    @Inject
    SchedulerProvider schedulerProvider;

    private final CompositeDisposable disposableManager = new CompositeDisposable();

    public MovieRepo() {
        apiService = new ApiServiceFactory().providesApiService();
    }

    public Flowable<List<Movie>> getAll() {
        fetchFromAPI();
        return dataSource.getMovieLiveData();
    }

    public void insertMovie(final Movie movie) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> dataSource.insertMovie(movie)));
    }

    public void insertMovieList(final List<Movie> movieList) {
        disposableManager.add(Observable.timer(1, TimeUnit.NANOSECONDS)
                .subscribeOn(schedulerProvider.getIoScheduler())
                .subscribe(time -> dataSource.insertMovieList(movieList)));
    }

    private void fetchFromAPI() {
        disposableManager.add(
                apiService.getAllMovies()
                        .subscribeOn(schedulerProvider.getIoScheduler())
                        .subscribe(response -> dataSource.insertMovieList(response.getResults())));
    }

}
