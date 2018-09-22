package com.andia.loice.movies.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andia.loice.movies.dagger.scheduler.SchedulerManager;
import com.andia.loice.movies.model.data.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends ViewModel {
    private MoviesFetchUseCase moviesFetchUseCase;
    private SchedulerManager schedulerMngr;


    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    MovieListViewModel(MoviesFetchUseCase moviesFetchUseCase, SchedulerManager schedulerMngr) {
        this.moviesFetchUseCase = moviesFetchUseCase;
        this.schedulerMngr = schedulerMngr;
    }

    public MutableLiveData<List<Movie>> getMovies() {
        if (movies.getValue() == null) {
            disposableManager.add(
                    moviesFetchUseCase.execute()
                            .subscribeOn(schedulerMngr.getIoScheduler())
                            .observeOn(schedulerMngr.getMainThreadScheduler())
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
