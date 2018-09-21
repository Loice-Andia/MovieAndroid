package com.andia.loice.movies.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andia.loice.movies.dagger.scheduler.SchedulerProvider;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.data.MovieResponse;
import com.andia.loice.movies.model.db.ApiDao.NetDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends ViewModel {
    private NetDataSource netDataSource;
    private SchedulerProvider schedulerProvider;


    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

    @Inject
    public MovieListViewModel(NetDataSource netDataSource, SchedulerProvider schedulerProvider) {
        this.netDataSource = netDataSource;
        this.schedulerProvider = schedulerProvider;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        if (movies.getValue() == null) {
            fetchMovies().subscribeOn(schedulerProvider.getIoScheduler())
                    .observeOn(schedulerProvider.getMainThreadScheduler())
                    .subscribe();
        }
        return movies;
    }

    public Observable<MovieResponse> fetchMovies() {
        return netDataSource.fetchMovies();
    }
}
