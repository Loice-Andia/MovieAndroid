package com.andia.loice.movies.model.db.api;

import com.andia.loice.movies.dagger.scheduler.SchedulerManager;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.DataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Repository class for the Movie object.
 * Facilitates getting the response from the API and storing them in the DB
 */
public class MovieRepo {

    private ApiService apiService;

    private DataSource dataSource;

    private SchedulerManager schedulerMngrImpl;

    private final CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    public MovieRepo(ApiService apiService, DataSource dataSource, SchedulerManager schedulerMngrImpl) {
        this.apiService = apiService;
        this.dataSource = dataSource;
        this.schedulerMngrImpl = schedulerMngrImpl;
    }

    public Flowable<List<Movie>> getAll() {
        fetchFromAPI();
        return dataSource.getMovieLiveData();
    }

    private void fetchFromAPI() {
        disposableManager.add(
                apiService.getAllMovies()
                        .subscribeOn(schedulerMngrImpl.getIoScheduler())
                        .subscribe(response -> dataSource.insertMovieList(response.getResults())));
    }

}
