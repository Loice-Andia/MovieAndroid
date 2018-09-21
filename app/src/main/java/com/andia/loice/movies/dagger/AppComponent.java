package com.andia.loice.movies.dagger;


import com.andia.loice.movies.BaseApp;
import com.andia.loice.movies.dagger.module.AppModule;
import com.andia.loice.movies.dagger.module.NetworkModule;
import com.andia.loice.movies.dagger.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        NetworkModule.class,
        ViewModelModule.class})
public interface AppComponent extends AndroidInjector<BaseApp> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApp> {
    }
}
