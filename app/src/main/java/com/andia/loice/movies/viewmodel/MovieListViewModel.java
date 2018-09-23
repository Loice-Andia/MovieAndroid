package com.andia.loice.movies.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.andia.loice.movies.dagger.scheduler.SchedulerManager;
import com.andia.loice.movies.model.data.Movie;
import com.andia.loice.movies.model.db.api.MovieRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListViewModel extends ViewModel {

    private MovieRepo movieRepo;
    private SchedulerManager schedulerMngr;

    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private CompositeDisposable disposableManager = new CompositeDisposable();

    @Inject
    MovieListViewModel(SchedulerManager schedulerMngr, MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
        this.schedulerMngr = schedulerMngr;
    }

    /**
     * Executes call to fetch the movies and set them to the LiveData List
     *
     * @return MutableLiveData<List       <       Movie>>
     */
    public MutableLiveData<List<Movie>> getMovies() {
        if (movies.getValue() == null) {
            disposableManager.add(
                    movieRepo.getAll()
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
