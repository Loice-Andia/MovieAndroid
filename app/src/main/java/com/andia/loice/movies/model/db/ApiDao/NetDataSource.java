package com.andia.loice.movies.model.db.ApiDao;

import android.arch.lifecycle.LiveData;

import com.andia.loice.movies.dagger.scheduler.SchedulerProvider;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.data.MovieResponse;
import com.andia.loice.movies.model.db.AppDatabase;
import com.andia.loice.movies.model.db.DataSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class NetDataSource implements NetworkDataManager{

    @Inject
    ApiService apiService;

    @Inject
    DataSource dataSource;

    @Inject
    SchedulerProvider schedulerProvider;

    private final CompositeDisposable disposableManager = new CompositeDisposable();

    public NetDataSource() {
        apiService = new ApiServiceFactory().providesApiService();
    }

    @Override
    public Observable<MovieResponse> fetchMovies() {
        return null;
    }

    public LiveData<List<Movie>> getAll() {
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
