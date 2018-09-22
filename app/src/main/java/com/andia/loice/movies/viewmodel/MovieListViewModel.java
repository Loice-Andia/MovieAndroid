package com.andia.loice.movies.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andia.loice.movies.dagger.scheduler.SchedulerProvider;
import com.andia.loice.movies.model.data.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends ViewModel {
    private MoviesFetchUseCase moviesFetchUseCase;
    private SchedulerProvider schedulerProvider;


    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    MovieListViewModel(MoviesFetchUseCase moviesFetchUseCase, SchedulerProvider schedulerProvider) {
        this.moviesFetchUseCase = moviesFetchUseCase;
        this.schedulerProvider = schedulerProvider;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        if (movies.getValue() == null) {
            disposableManager.add(
                    moviesFetchUseCase.execute()
                            .subscribeOn(schedulerProvider.getIoScheduler())
                            .observeOn(schedulerProvider.getMainThreadScheduler())
                            .subscribe(this.movies::setValue));
        }
        return movies;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposableManager.dispose();
    }
}
