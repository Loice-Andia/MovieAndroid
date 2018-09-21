package com.andia.loice.movies.dagger.module;

import android.arch.lifecycle.ViewModel;

import com.andia.loice.movies.dagger.ViewModelKey;
import com.andia.loice.movies.viewmodel.MovieListViewModel;
import com.andia.loice.movies.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    abstract MovieListViewModel bindHomeViewModel(MovieListViewModel homeViewModel);

    @Binds
    abstract ViewModelFactory bindViewModelFactory(ViewModelFactory viewModelFactory);
}
